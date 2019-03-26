import React, { Component } from 'react';

import { withRouter } from 'react-router-dom';

import TextField from '@material-ui/core/TextField';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import Button from '@material-ui/core/Button';
import Divider from '@material-ui/core/Divider';
import Grid from '@material-ui/core/Grid';
import { withStyles } from '@material-ui/core/styles';

import MDEditor from '../../components/MDEditor/MDEditor';

import axios from '../../axios-instance';
import { Typography } from '@material-ui/core';

const styles = (theme) => ({
  editor: {
    marginTop: '3vh',
    marginBottom: '3vh'
  },
  title: {
    margin: '0 2vh 3vh 2vh', 
    height: '100%'
  },
  button: {
    margin: '3vh 3vh'
  }
});

class NewPost extends Component {

  state = {
    body: '',
    title: '',
    category: 'NONE',
    imgUrl: ''
  }

  handleTitleChange(event) {
    this.setState({title: event.target.value});
  }

  handleImageChange(event) {
    this.setState({imgUrl: event.target.value});
  }

  handleCategoryChange(event) {
    this.setState({category: event.target.value});
  }

  handleBodyChange(value) {
    this.setState({body: value});
  }

  handleSend() {
    console.log(this.state);
    axios.post('/posts/add/1001', this.state)
      .then((res) => {
        console.log(res);
        this.props.history.push('/');
      })
      .catch((err) => console.log(err));
  }

  handleCancel() {
    this.props.history.push('/');
  }

  validator () {
    let invalidBody = false;
    let invalidTitle = false;
    let invalidFieldFound = false;

    if (this.state.title.length < 4) {
      invalidTitle = true;
      invalidFieldFound = true;
    }

    if (this.state.body.length < 500) {
      invalidBody = true;
      invalidFieldFound = true;
    }

    return {
      body: invalidBody,
      title: invalidTitle,
      invalidFound: invalidFieldFound
    };
  }

  render() {

    const { classes } = this.props;

    const validator = this.validator();
    let bodyValidatorStyle = {
      color: '#000'
    };

    if (validator.body) {
      bodyValidatorStyle = {
        color: '#FF0000'
      };
    }

    return (
      <Grid item xs={12} container direction='row' justify='center'>
        <Grid item md={3} sm={7} xs={10} classes={{item: classes.title}}>
          <TextField 
            error={validator.title}
            helperText='Min. 4 characters'
            required
            label='Post Title'
            fullWidth
            onChange={this.handleTitleChange.bind(this)}/>
        </Grid>
        <Grid item md={3} sm={7} xs={10} classes={{item: classes.title}}>
          <TextField 
            label='Title image url (optional)'
            fullWidth
            onChange={this.handleImageChange.bind(this)}/>
        </Grid>
        <Grid item md={3} sm={7} xs={10} classes={{item: classes.title}}>
          <FormControl fullWidth>
            <InputLabel htmlFor="category-label">
              Category (optional)
            </InputLabel>
            <Select
              value={this.state.category}
              onChange={this.handleCategoryChange.bind(this)}
              fullWidth
              input={<Input name="category" id="category-label" />}
            >
              <MenuItem value='NONE'>
                <em>None</em>
              </MenuItem>
              <MenuItem value='CULTURE'>Culture</MenuItem>
              <MenuItem value='TECH'>Tech</MenuItem>
              <MenuItem value='POLITICS'>Politics</MenuItem>
              <MenuItem value='STUDIES'>Studies</MenuItem>
              <MenuItem value='HEALTH'>Health</MenuItem>
            </Select>  
          </FormControl>        
        </Grid>
        <Grid item xs={12}>
          <Divider />
        </Grid>
        <Grid item xs={12} classes={{item: classes.editor}}>
          <MDEditor onChange={this.handleBodyChange.bind(this)} />
          <Typography style={bodyValidatorStyle}>Minimum 500 characters</Typography>
        </Grid>
        <Grid item xs={12}>
          <Divider />
        </Grid>
        <Grid container justify='center'>
          <Button onClick={this.handleSend.bind(this)} disabled={validator.invalidFound} size='large' variant='contained' color='primary' classes={{root: classes.button}}>Send</Button>
          <Button onClick={this.handleCancel.bind(this)} size='large' variant='contained' color='secondary' classes={{root: classes.button}}>Cancel</Button>
        </Grid>
      </Grid>
    );
  }
}

export default withRouter(withStyles(styles)(NewPost));