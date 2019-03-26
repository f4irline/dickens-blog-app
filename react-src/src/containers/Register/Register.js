import React, { Component } from 'react';

import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';

import './Register.css';

const styles = {
  loginItem: {
    marginBottom: '2vh'
  }
};

class Register extends Component {

  state = {
    userName: '',
    password: '',
    userFirst: '',
    userLast: ''
  }

  handleButtonClick() {
    this.props.register(this.state);
  }

  handleInputChange(event) {
    switch (event.target.name) {
    case 'userName':
      this.setState({userName: event.target.value});
      break;
    case 'password':
      this.setState({password: event.target.value});
      break;
    case 'userFirst':
      this.setState({userFirst: event.target.value});
      break;
    case 'userLast':
      this.setState({userLast: event.target.value});
      break;
    default:
      break;
    }
  }

  validator() {
    let invalidUserName = false;
    let invalidPassword = false;
    let invalidFirstName = false;
    let invalidLastName = false;
    let invalidFieldFound = false;

    if (this.state.userName.length < 4) {
      invalidUserName = true;
      invalidFieldFound = true;
    }

    if (this.state.password.length < 4) {
      invalidPassword = true;
      invalidFieldFound = true;
    }

    if (this.state.userFirst.length < 2) {
      invalidFirstName = true;
      invalidFieldFound = true;
    }

    if (this.state.userLast.length < 2) {
      invalidLastName = true;
      invalidFieldFound = true;
    }

    return {
      userName: invalidUserName,
      password: invalidPassword,
      firstName: invalidFirstName,
      lastName: invalidLastName,
      invalidFound: invalidFieldFound
    };
  }

  render() {

    const { classes } = this.props;

    const validator = this.validator();

    return (
      <Grid direction='row' justify='center' alignItems='center' container className='Register'>
        <Grid item xs={11} md={4}>
          <Paper className='register-wrapper' elevation={5}>
            <Typography variant='h4' gutterBottom>
              USER REGISTER
            </Typography>
            <Divider />
            <TextField
              required
              error = {validator.userName}
              helperText='Min. 4 characters'
              className={classes.loginItem}
              label='Name'
              value={this.state.userName}
              onChange={this.handleInputChange.bind(this)}
              name='userName'
            />
            <TextField
              required
              error = {validator.password}
              helperText='Min. 4 characters'            
              className={classes.loginItem}
              label='Password'
              value={this.state.password}
              onChange={this.handleInputChange.bind(this)}
              name='password'
              type='password'
            />
            <TextField
              required
              error = {validator.firstName}
              helperText='Min. 2 characters'
              className={classes.loginItem}
              label='First Name'
              value={this.state.userFirst}
              onChange={this.handleInputChange.bind(this)}
              name='userFirst'
            />
            <TextField
              required
              error = {validator.lastName}
              helperText='Min. 2 characters'
              className={classes.loginItem}
              label='Last Name'
              value={this.state.userLast}
              onChange={this.handleInputChange.bind(this)}
              name='userLast'
            />
            <Button             
              className={classes.loginItem}
              size='large' 
              disabled={validator.invalidFound}
              onClick={this.handleButtonClick.bind(this)} 
              variant='contained'>
              Submit
            </Button>
          </Paper>
        </Grid>
      </Grid>
    );
  }
}

export default withStyles(styles)(Register);
