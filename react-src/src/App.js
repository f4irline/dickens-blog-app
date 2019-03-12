import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import Grid from '@material-ui/core/Grid';
import Login from './containers/Login/Login';
import Paper from '@material-ui/core/Paper';
import Header from './containers/Header/Header';
import Navigation from './containers/Navigation/Navigation';
import Posts from './containers/Posts/Posts';
import axios from 'axios';
import './App.css';

class App extends Component {

  state = {
    loggedIn: false,
    loading: true,
    posts: {}
  }

  componentDidMount() {
    axios.get('https://jsonplaceholder.typicode.com/posts')
      .then((res) => {
        this.setState({posts: res.data, loading: false}, () => {
          console.log(res.data);
        });
      });
  }

  handleLogin = (name) => {
    this.setState({loggedIn: true});
  }

  render() {

    if (this.state.loading) {
      return (
        <Grid direction='row' justify='center' container className='App'>
          <Grid item xs={8}>
            <p>Loading...</p>
          </Grid>
        </Grid>
      );
    }

    return (
      <Grid direction='row' justify='center' container className='App'>
        <Grid item xs={8}>
          <Paper square>
            <Header />
            <Navigation />
          </Paper>
          <Posts posts={this.state.posts} />
        </Grid>
      </Grid>
    );
  }
}

export default App;
