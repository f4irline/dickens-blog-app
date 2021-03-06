import React, { Component } from 'react';
import { Route, Switch, withRouter } from 'react-router-dom';

import Grid from '@material-ui/core/Grid';

import Login from './containers/Login/Login';
import Register from './containers/Register/Register';
import Home from './containers/Home/Home';

import axios from './axios-instance';

import './App.css';

class App extends Component {

  anonUser = {
    roles: [
      'ANONYMOUS'
    ]
  }

  state = {
    loggedIn: false,
    user: this.anonUser
  }

  componentDidMount() {
    const jwt = localStorage.getItem('accessToken');

    if (jwt) {
      this.initUserDetails();
    }
  }

  initUserDetails() {
    const jwt = localStorage.getItem('accessToken');
    axios.get('/users/details', {
      headers: {
        Authorization: `Bearer ${jwt}`
      }
    })
      .then((res) => {
        this.setState({loggedIn: true, user: res.data.user});
      }).catch(err => {
        alert('There was some error with the request.');
        console.log(err);
      });
  }

  handleLogin = () => {
    const jwt = localStorage.getItem('accessToken');
    axios.get('/users/details', {
      headers: {
        Authorization: `Bearer ${jwt}`
      }
    })
      .then((res) => {
        this.setState({loggedIn: true, user: res.data.user});
        this.props.history.push('/');
      }).catch(err => {
        alert('There was some error with the request.');
        console.log(err);
      });
  }

  handleLogout = () => {
    this.setState({loggedIn: false, user: this.anonUser}, () => {
      localStorage.removeItem('accessToken');
    });
    this.props.history.push('/');
  }

  handleRegister = (user) => {
    axios.post('/auth/register', user)
      .then((res) => {
        this.props.history.push('/');
      }).catch(err => {
        alert('There was some error with the request.');
        console.log(err);
      });
  }

  render() {
    return (
      <Grid direction='row' justify='center' container className='App'>
        <Grid container justify='center'>
          <Switch>
            <Route path='/login' render={() => <Login login={this.handleLogin.bind(this)} />} />
            <Route path='/register' render={() => <Register register={this.handleRegister.bind(this)} />} />
            <Route path='/' render={() => <Home user={this.state.user} loggedIn={this.state.loggedIn} logout={this.handleLogout.bind(this)}/>} />
          </Switch>
        </Grid>
      </Grid>
    );
  }
}

export default withRouter(App);
