import React, { Component } from 'react';
import { Route, Switch, withRouter } from 'react-router-dom';

import Grid from '@material-ui/core/Grid';

import Login from './containers/Login/Login';
import Register from './containers/Register/Register';
import Home from './containers/Home/Home';

import axios from './axios-instance';

import './App.css';

class App extends Component {

  state = {
    loggedIn: false,
    name: ''
  }

  handleLogin = (user) => {
    this.setState({loggedIn: true, name: user.username});
    this.props.history.push('/');
  }

  handleLogout = () => {
    this.setState({loggedIn: false, name: ''});
    this.props.history.push('/');
  }

  handleRegister = (user) => {
    console.log(user);
    axios.post('/users/add', user)
      .then((res) => {
        console.log(res);
        this.props.history.push('/');
      }).catch(err => console.log(err));
  }

  render() {
    return (
      <Grid direction='row' justify='center' container className='App'>
        <Grid container justify='center'>
          <Switch>
            <Route path='/login' render={() => <Login login={this.handleLogin.bind(this)} />} />
            <Route path='/register' render={() => <Register register={this.handleRegister.bind(this)} />} />
            <Route path='/' render={() => <Home user={this.state.name} loggedIn={this.state.loggedIn} logout={this.handleLogout.bind(this)}/>} />
          </Switch>
        </Grid>
      </Grid>
    );
  }
}

export default withRouter(App);
