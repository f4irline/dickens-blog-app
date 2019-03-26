import React, { Component } from 'react';

import { Route, Switch } from 'react-router-dom';

import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Divider from '@material-ui/core/Divider';
import Header from '../Header/Header';
import Navigation from '../Navigation/Navigation';

import Posts from '../Posts/Posts';
import Post from '../../components/Post/Post';
import NewPost from '../NewPost/NewPost';

import './Home.css';

class Home extends Component {

  state = {
    modalOpen: false,
    openPost: {}
  }

  handlePostOpen(post) {
    this.setState({openPost: post}, () => {
      this.setState({modalOpen: true});
    });
  }
  
  render() {
    return (
      <Grid direction='row' justify='center' container className='Home'>
        <Grid item xs={11} md={8}>
          <Paper square>
            <Header logout={this.props.logout} loggedIn={this.props.loggedIn} />
            <Divider variant='middle'/>
            <Navigation />
          </Paper>
        </Grid>
        <Switch>
          <Route path='/' exact render={() => (
            <Grid item xs={11} md={7}>
              <Posts postOpen={this.handlePostOpen.bind(this)} />
            </Grid>
          )}/>
          <Route path='/new' render={() => (
            <Grid item xs={11} md={7}>
              <NewPost user={this.props.user} />
            </Grid>
          )}/>
          <Route path='/post/:id' component={Post}/>
        </Switch>
      </Grid>
    );
  }
}

export default Home;