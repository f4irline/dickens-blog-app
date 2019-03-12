import React, { Component } from 'react';

import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';

class Post extends Component {
  render() {
    return (
      <Paper elevation={5}>
        <Typography>Post</Typography>
      </Paper>
    );  
  }
}

export default Post;