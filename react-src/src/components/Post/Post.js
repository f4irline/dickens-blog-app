import React, { Component } from 'react';

import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import Divider from '@material-ui/core/Divider';
import { withStyles } from '@material-ui/core/styles';

import * as Showdown from 'showdown';

import headerImg from '../../assets/images/header_placeholder.jpg';

import Comments from './Comments/Comments';
import Markdown from '../../utils/Markdown';
import axios from '../../axios-instance';

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
    loadingComments: true
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
        <Grid container>
          <Paper elevation={5} classes={{root: classes.post}}>
            <img src={headerImg} alt='Header' style={{width: '100%'}} />
            <Typography variant='title' style={{fontSize: '3vh', margin: '1vh 0', textAlign: 'center'}}>
              {this.state.post.title}
            </Typography>
            <Divider/>
            <Markdown>{this.state.post.body}</Markdown>
            <Divider/>
            <Typography variant='caption' style={{fontSize: '2vh', marginTop: '1vh', textAlign: 'end'}}>
            Author: {this.state.post.author.userFirst} {this.state.post.author.userLast} - Written: {new Date(this.state.post.postDate).toLocaleString('en-GB')}
            </Typography>
          </Paper>
        </Grid>
        <Grid container>
          <Comments postId={this.state.post.postId} newComment={this.loadComments.bind(this)} comments={this.state.comments}/>
        </Grid>
      </Grid>
    );  
  }
}

export default withStyles(styles)(Post);