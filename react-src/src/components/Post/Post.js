import React, { Component } from 'react';

import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import Divider from '@material-ui/core/Divider';
import { withStyles } from '@material-ui/core/styles';
import DeleteIcon from '@material-ui/icons/Delete';
import IconButton from '@material-ui/core/IconButton';

import * as Showdown from 'showdown';

import headerImg from '../../assets/images/header_placeholder.jpg';

import Comments from './Comments/Comments';
import Markdown from '../../utils/Markdown';
import axios from '../../axios-instance';
import AlertDialog from '../AlertDialog/AlertDialog';

import { withRouter } from 'react-router-dom';

import Loading from '../../components/Loading/Loading';

const styles = {
  post: {
    padding: '1vh',
    margin: '1vh',
    boxSizing: 'border-box'
  },
  body: {
    margin: '1vh 0'
  }
};

class Post extends Component {

  state = {
    post: {},
    comments: [],
    loadingPosts: true,
    loadingComments: true,
    showDialog: false
  }

  converter = new Showdown.Converter({
    tables: true,
    simplifiedAutoLink: true,
    strikethrough: true,
    tasklists: true
  });

  componentDidMount() {
    const { id } = this.props.match.params;
    axios.get('/posts/'+id)
      .then((res) => {
        this.setState({post: res.data});
        this.delayLoadingPostResolve();
      });
    axios.get('comments/'+id)
      .then((res) => {
        this.setState({comments: res.data});
        this.delayLoadingCommentsResolve();
      });
  }

  loadComments() {
    const { id } = this.props.match.params;
    console.log('Loading');
    this.setState({loadingComments: true}, () => {
      axios.get('comments/'+id)
        .then((res) => {
          this.setState({comments: res.data});
          this.delayLoadingCommentsResolve();
        });
    });
  }

  delayLoadingCommentsResolve() {
    setTimeout(() => {
      this.setState({loadingComments: false});
    }, 1500);
  }

  delayLoadingPostResolve() {
    setTimeout(() => {
      this.setState({loadingPosts: false});
    }, 1500);
  }

  deleteComment(commentId) {
    const jwt = localStorage.getItem('accessToken');
    const options = {
      credentials: 'include',
      headers: {
        Authorization: `Bearer ${jwt}`
      }
    };

    axios.delete('/comments/'+commentId, options)
      .then(this.loadComments.bind(this));
  }

  deletePostsComments(postId) {
    const jwt = localStorage.getItem('accessToken');
    const options = {
      credentials: 'include',
      headers: {
        Authorization: `Bearer ${jwt}`
      }
    };

    axios.delete('/comments/all/'+postId, options)
      .then((res) => {
        console.log(res);
        this.deletePost(postId);
      })
      .catch(err => console.log(err));
  }

  deletePost(postId) {
    const jwt = localStorage.getItem('accessToken');
    const options = {
      credentials: 'include',
      headers: {
        Authorization: `Bearer ${jwt}`
      }
    };

    axios.delete('/posts/'+postId, options)
      .then((res) => {
        console.log(res);
        this.props.history.push('/');
      })
      .catch(err => console.log(err));
  }

  onClickOpenHandler() {
    this.setState({showDialog: true});
  }

  onClickCloseHandler(name) {
    if(name === 'delete') {
      const { id } = this.props.match.params;
      this.deletePostsComments(id);
    }
    this.setState({showDialog: false});
  }

  addDefaultSrc(event) {
    event.target.src = headerImg;
  }

  render() {

    const { classes } = this.props;

    if (this.state.loadingPosts || this.state.loadingComments) {
      return (
        <Grid item container justify='center' xs={12}>
          <Loading loading={true}/>
        </Grid>
      );
    }

    return (
      <Grid item xs={11} lg={7}>
        {this.state.showDialog ? <AlertDialog title='Remove whole post?' 
          description = 'The whole post will be removed. Are you sure?' 
          handleClose={this.onClickCloseHandler.bind(this)} /> : null}
        <Grid container>
          <Paper elevation={5} classes={{root: classes.post}}>
            <img onError={this.addDefaultSrc} src={this.state.post.imgUrl} alt='Header' style={{width: '100%'}} />
            <Typography variant='title' style={{fontSize: '3vh', margin: '1vh 0', textAlign: 'center'}}>
              {this.state.post.title}
            </Typography>
            <Divider/>
            <Markdown>{this.state.post.body}</Markdown>
            <Divider/>

            <Grid direction='row' justify='center' container className='Home'>
              <Grid item xs={6}>
                {this.props.user.roles[1] !== undefined && this.props.user.roles[1].definition === 'ROLE_ADMIN' ?
                  <IconButton onClick={this.onClickOpenHandler.bind(this)}>
                    <DeleteIcon color='secondary' />
                  </IconButton>
                  : null}
              </Grid>
              <Grid item xs={6}>
                <Typography variant='caption' style={{fontSize: '2vh', marginTop: '1vh', textAlign: 'end'}}>
                  Author: {this.state.post.author.userFirst} {this.state.post.author.userLast} - Written: {new Date(this.state.post.postDate).toLocaleString('en-GB')}
                </Typography>
              </Grid>
            </Grid>
          </Paper>
        </Grid>
        <Grid container>
          <Comments user={this.props.user} handleDelete={this.deleteComment.bind(this)} postId={this.state.post.postId} newComment={this.loadComments.bind(this)} comments={this.state.comments}/>
        </Grid>
      </Grid>
    );  
  }
}

export default withRouter(withStyles(styles)(Post));