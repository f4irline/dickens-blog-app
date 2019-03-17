import React from 'react';

import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';

const Post = (props) => {
  return (
    <Grid item xs={12}>
      <Paper>
        <p>{props.data.title}</p>
        <p>{props.data.body}</p>
      </Paper>
    </Grid>
  );
};

export default Post;