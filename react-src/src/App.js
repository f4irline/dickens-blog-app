import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Divider from '@material-ui/core/Divider';

import Login from './containers/Login/Login';
import Header from './containers/Header/Header';
import Navigation from './containers/Navigation/Navigation';
import Home from './containers/Home/Home';

import './App.css';

class App extends Component {

  state = {
    loggedIn: false
  }

  handleLogin = (name) => {
    this.setState({loggedIn: true});
  }

  render() {
    return (
      <Grid direction='row' justify='center' container className='App'>
        <Grid item xs={8}>
          <Paper square>
            <Header />
            <Divider variant='middle'/>
            <Navigation />
          </Paper>
          <Home />
        </Grid>
      </Grid>
    );
  }
}

export default App;
