import React, { Component } from 'react';

import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import { withStyles } from '@material-ui/core/styles';

import axios from 'axios';

const styles = {
  post: {
    padding: '1vh',
    margin: '1vh'
  }
};

class Post extends Component {

  state = {
    post: {},
    loading: true
  }

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
            <Typography>
              {repeatPost}
            </Typography>
          </Paper>
        </Grid>
      </Grid>
    );  
  }
}

export default withStyles(styles)(Post);