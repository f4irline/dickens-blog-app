import React, { Component } from 'react';
import { Route, Switch, withRouter } from 'react-router-dom';

import Grid from '@material-ui/core/Grid';

import Login from './containers/Login/Login';
import Home from './containers/Home/Home';

import './App.css';

class App extends Component {

  state = {
    loggedIn: false,
    name: ''
  }

  handleLogin = (name) => {
    this.setState({loggedIn: true, name: name});
    this.props.history.push('/');
  }

  handleLogout = () => {
    this.setState({loggedIn: false, name: ''});
    this.props.history.push('/');
  }

  render() {
    return (
      <Grid direction='row' justify='center' container className='App'>
        <Grid container justify='center'>
          <Switch>
            <Route path='/login' render={() => <Login login={this.handleLogin.bind(this)} />} />
            <Route path='/' render={() => <Home loggedIn={this.state.loggedIn} logout={this.handleLogout.bind(this)}/>} />
          </Switch>
        </Grid>
      </Grid>
    );
  }
}

export default withRouter(App);
