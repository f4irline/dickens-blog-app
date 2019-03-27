import React, { Component } from 'react';

import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';

import axios from '../../../../axios-instance';

class CommentWriter extends Component {

  state = {
    body: ''
  }

  handleSubmit() {
    axios.post(`/comments/add/${this.props.postId}/${this.props.user.userId}`, this.state)
      .then((res) => {
        console.log(res);
        this.props.newComment();
      })
      .catch((err) => console.log(err));
  }

  handleChange(event) {
    this.setState({body: event.target.value});
  }

  render() {
    return (
      <Grid item container xs={8} justify='center' style={{marginTop: '1vh'}}>
        <Typography variant='headline' style={{textDecoration: 'underline'}}>
          Post a comment!
        </Typography>
        <TextField
          label='Give your opinion!'
          placeholder='Comment'
          multiline
          margin='normal'
          fullWidth
          onChange={this.handleChange.bind(this)}
        />
        <Button variant='text' color='primary' onClick={this.handleSubmit.bind(this)}>Submit!</Button>
      </Grid>
    );
  }
}

export default CommentWriter;