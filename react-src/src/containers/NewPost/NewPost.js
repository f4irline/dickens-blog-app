import React, { Component } from 'react';

import { withRouter } from 'react-router-dom';

import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Divider from '@material-ui/core/Divider';
import Grid from '@material-ui/core/Grid';
import { withStyles } from '@material-ui/core/styles';

import MDEditor from '../../components/MDEditor/MDEditor';

import axios from '../../axios-instance';

const styles = {
  editor: {
    marginTop: '3vh',
    marginBottom: '3vh'
  },
  title: {
    marginBottom: '3vh'
  },
  button: {
    margin: '3vh 3vh'
  }
};

class NewPost extends Component {

  state = {
    body: '',
    title: ''
  }

  handleTitleChange(event) {
    this.setState({title: event.target.value});
  }

  handleBodyChange(value) {
    this.setState({body: value});
  }

  handleSend() {
    console.log(this.state);
    axios.post('/posts/add/1001', this.state)
      .then((res) => console.log(res));
    this.props.history.push('/');
  }

  handleCancel() {
    this.props.history.push('/');
  }

  render() {

    const { classes } = this.props;

    return (
      <Grid item xs={12} container direction='row' justify='center'>
        <Grid item xs={8} classes={{item: classes.title}}>
          <TextField 
            label='Post Title'
            fullWidth
            onChange={this.handleTitleChange.bind(this)}/>
        </Grid>
        <Grid item xs={12}>
          <Divider />
        </Grid>
        <Grid item xs={12} classes={{item: classes.editor}}>
          <MDEditor onChange={this.handleBodyChange.bind(this)} />
        </Grid>
        <Grid item xs={12}>
          <Divider />
        </Grid>
        <Grid container justify='center'>
          <Button onClick={this.handleSend.bind(this)} size='large' variant='contained' color='primary' classes={{root: classes.button}}>Send</Button>
          <Button onClick={this.handleCancel.bind(this)} size='large' variant='contained' color='secondary' classes={{root: classes.button}}>Cancel</Button>
        </Grid>
      </Grid>
    );
  }
}

export default withRouter(withStyles(styles)(NewPost));