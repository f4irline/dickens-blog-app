import React, { Component } from 'react';

import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';

import './Login.css';

const styles = {
  loginItem: {
    marginBottom: '2vh'
  }
};

class Login extends Component {

  state = {
    name: '',
    password: ''
  }

  handleButtonClick() {
    this.props.login(this.state.name);
  }

  handleInputChange(event) {
    switch (event.target.name) {
    case 'username':
      this.setState({name: event.target.value});
      break;
    case 'password':
      this.setState({password: event.target.value});
      break;
    default:
      break;
    }
  }

  validator() {
    let invalidUserName = false;
    let invalidPassword = false;
    let invalidFieldFound = false;

    if (this.state.name.length < 4) {
      invalidUserName = true;
      invalidFieldFound = true;
    }

    if (this.state.password.length < 4) {
      invalidPassword = true;
      invalidFieldFound = true;
    }

    return {
      userName: invalidUserName,
      password: invalidPassword,
      invalidFound: invalidFieldFound
    };
  }

  render() {

    const { classes } = this.props;

    const validator = this.validator();

    return (
      <Grid direction='row' justify='center' alignItems='center' container className='Login'>
        <Grid item xs={11} md={4}>
          <Paper className='login-wrapper' elevation={5}>
            <Typography variant='h4' gutterBottom>
              USER LOGIN
            </Typography>
            <Divider />
            <TextField
              error={validator.userName}
              helperText='Min. 4 characters'
              required
              className={classes.loginItem}
              label='Name'
              value={this.state.name}
              onChange={this.handleInputChange.bind(this)}
              name='username'
            />
            <TextField
              error={validator.password}
              helperText='Min. 4 characters'
              required
              className={classes.loginItem}
              label='Password'
              value={this.state.password}
              onChange={this.handleInputChange.bind(this)}
              name='password'
              type='password'
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

export default withStyles(styles)(Login);
