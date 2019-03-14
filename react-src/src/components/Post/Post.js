import React, { Component } from 'react';

import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import Divider from '@material-ui/core/Divider';
import { withStyles } from '@material-ui/core/styles';

import * as Showdown from 'showdown';

import headerImg from '../../assets/images/header_placeholder.jpg';

import axios from 'axios';

const styles = {
  post: {
    padding: '1vh',
    margin: '1vh',
    boxSizing: 'border-box'
  }
};

class Post extends Component {

  state = {
    post: {},
    loading: true
  }

  converter = new Showdown.Converter({
    tables: true,
    simplifiedAutoLink: true,
    strikethrough: true,
    tasklists: true
  });

  componentDidMount() {
    const { id } = this.props.match.params;
    axios.get('https://jsonplaceholder.typicode.com/posts/'+id)
      .then((res) => {
        this.setState({post: res.data, loading: false});
      });
  }

  render() {

    const { classes } = this.props;

    if (this.state.loading) {
      return (
        <Grid item xs={11} lg={7}>
          <p style={{textAlign: 'center'}}>Loading...</p>
        </Grid>
      );
    }

    let repeatPost = this.state.post.body.repeat(50);

    return (
      <Grid item xs={11} lg={7}>
        <Grid container>
          <Paper elevation={5} classes={{root: classes.post}}>
            <img src={headerImg} alt='Header' style={{width: '100%'}} />
            <Typography variant='title' style={{fontSize: '3vh', margin: '1vh 0', textAlign: 'center'}}>
              {this.state.post.title}
            </Typography>
            <Divider/>
            <Typography variant='body1'>
              {repeatPost}
            </Typography>
            <Divider/>
            <Typography variant='caption' style={{fontSize: '2vh', marginTop: '1vh', textAlign: 'end'}}>
              Author: Matti - Written: {new Date().toUTCString()}
            </Typography>
          </Paper>
        </Grid>
      </Grid>
    );  
  }
}

export default withStyles(styles)(Post);