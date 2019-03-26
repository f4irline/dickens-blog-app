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

  render() {

    const { classes } = this.props;

    return (
      <Grid direction='row' justify='center' alignItems='center' container className='Register'>
        <Grid item xs={11} md={4}>
          <Paper className='register-wrapper' elevation={5}>
            <Typography variant='h4' gutterBottom>
              USER REGISTER
            </Typography>
            <Divider />
            <TextField
              className={classes.loginItem}
              label='Name'
              value={this.state.userName}
              onChange={this.handleInputChange.bind(this)}
              name='userName'
            />
            <TextField
              className={classes.loginItem}
              label='Password'
              value={this.state.password}
              onChange={this.handleInputChange.bind(this)}
              name='password'
              type='password'
            />
            <TextField
              className={classes.loginItem}
              label='First Name'
              value={this.state.userFirst}
              onChange={this.handleInputChange.bind(this)}
              name='userFirst'
            />
            <TextField
              className={classes.loginItem}
              label='Last Name'
              value={this.state.userLast}
              onChange={this.handleInputChange.bind(this)}
              name='userLast'
            />
            <Button             
              className={classes.loginItem}
              size='large' 
              disabled={false}
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
