import React, { Component } from 'react';

import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import Divider from '@material-ui/core/Divider';
import { withStyles } from '@material-ui/core/styles';
import DeleteIcon from '@material-ui/icons/Delete';
import IconButton from '@material-ui/core/IconButton';
import { Redirect } from 'react-router-dom';

import * as Showdown from 'showdown';

import headerImg from '../../assets/images/header_placeholder.jpg';

import Comments from './Comments/Comments';
import Markdown from '../../utils/Markdown';
import axios from '../../axios-instance';
import AlertDialog from '../AlertDialog/AlertDialog';

//import { withRouter } from 'react-router-dom'; selvitä!

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
    showDialog: false,
    redirect: false
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
        this.setState({post: res.data, loadingPosts: false});
      });
    axios.get('comments/'+id)
      .then((res) => {
        this.setState({comments: res.data, loadingComments: false});
      });
  }

  loadComments() {
    const { id } = this.props.match.params;
    this.setState({loadingComments: true}, () => {
      axios.get('comments/'+id)
        .then((res) => {
          this.setState({comments: res.data, loadingComments: false});
        });
    });
  }

  deleteComment(commentId) {
    axios.delete("/comments/"+commentId).then(this.loadComments.bind(this))
  }

  deletePostsComments(postId) {
    axios.delete('/comments/all/'+postId)
    .then(this.deletePost(postId));
  }

  deletePost(postId) {
    console.log("deletepost")
    axios.delete('/posts/'+postId)
    .then(() => {
      this.setState({redirect: true})
    });
    
  }

  onClickOpenHandler() {
    this.setState({showDialog: true})
  }

  onClickCloseHandler(name) {
    if(name === 'delete') {
      const { id } = this.props.match.params;
      this.deletePostsComments(id);
    }
    this.setState({showDialog: false})
  }

  render() {

    if (this.state.redirect) {
      return <Redirect to='/'/>;
    }

    const { classes } = this.props;

    if (this.state.loadingPosts || this.state.loadingComments) {
      return (
        <Grid item xs={11} lg={7}>
          <p style={{textAlign: 'center'}}>Loading...</p>
        </Grid>
      );
    }

    return (
      <Grid item xs={11} lg={7}>
        {this.state.showDialog ? <AlertDialog title="Titteli" description = "Description" handleClose={this.onClickCloseHandler.bind(this)} /> : null}
        <Grid container>
          <Paper elevation={5} classes={{root: classes.post}}>
            <img src={headerImg} alt='Header' style={{width: '100%'}} />
            <Typography variant='title' style={{fontSize: '3vh', margin: '1vh 0', textAlign: 'center'}}>
              {this.state.post.title}
            </Typography>
            <Divider/>
            <Markdown>{this.state.post.body}</Markdown>
            <Divider/>

            <Grid direction='row' justify='center' container className='Home'>
              <Grid item xs={6}>
                <IconButton aria-label="Delete" onClick={this.onClickOpenHandler.bind(this)}>
                  <DeleteIcon />
                </IconButton>
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
          <Comments handleDelete={this.deleteComment.bind(this)} postId={this.state.post.postId} newComment={this.loadComments.bind(this)} comments={this.state.comments}/>
        </Grid>
      </Grid>
    );  
  }
}

export default withStyles(styles)(Post);