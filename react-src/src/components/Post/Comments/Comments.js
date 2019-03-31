import React, { Component } from 'react';

import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import Grid from '@material-ui/core/Grid';
import { withStyles } from '@material-ui/core/styles';

import CommentWriter from './CommentWriter/CommentWriter';
import Comment from './Comment/Comment';

const styles = {
  comment: {
    padding: '1vh',
    margin: '1vh',
    boxSizing: 'border-box',
    width: '100%'
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
      return <Comment user={this.props.user} handleDelete={this.props.handleDelete} key={comment.commentId} comment={comment} />;
    });

    return (
      <Paper elevation={5} classes={{root: classes.comment}}>
        <Typography variant='headline' style={{textDecoration: 'underline'}}>
          Comments
        </Typography>
        <Divider style={{marginTop: '1vh'}}/>
        {comments}
        {this.props.user.roles[0] !== undefined && this.props.user.roles[0].definition === 'ROLE_USER' ? 
          <Grid container justify='center'>
            <CommentWriter user={this.props.user} postId={this.props.postId} newComment={this.props.newComment}/>
          </Grid> : null }
      </Paper>
    );
  }
}

export default withStyles(styles)(Comments);