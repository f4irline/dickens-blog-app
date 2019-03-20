import React, { Component } from 'react';

import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import { withStyles } from '@material-ui/core/styles';

import Comment from './Comment/Comment';

const styles = {
  comment: {
    padding: '1vh',
    margin: '1vh',
    boxSizing: 'border-box'
  },
};

class Comments extends Component {
  
  state = {
    comments: []
  }

  componentDidMount() {
    this.setState({comments: this.props.comments});
  }

  render() {

    const { classes } = this.props;

    let comments = this.state.comments.map((comment) => {
      return <Comment key={comment.commentId} comment={comment} />;
    });

    return (
      <Paper elevation={5} classes={{root: classes.comment}}>
        <Typography variant='headline' style={{textDecoration: 'underline'}}>
          Comments
        </Typography>
        <Divider style={{marginTop: '1vh'}}/>
        {comments}
      </Paper>
    );
  }
}

export default withStyles(styles)(Comments);