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
    openPost: {},
    searchValue: undefined
  }

  handlePostOpen(post) {
    this.setState({openPost: post}, () => {
      this.setState({modalOpen: true});
    });
  }

  handleSearch(value) {
    this.setState({searchValue: value});
  }
  
  render() {
    return (
      <Grid direction='row' justify='center' container className='Home'>
        <Grid item xs={11} md={8}>
          <Paper square>
            <Header onSearch={this.handleSearch.bind(this)} logout={this.props.logout} loggedIn={this.props.loggedIn} />
            <Divider variant='middle'/>
            <Navigation />
          </Paper>
        </Grid>
        <Switch>
          <Route path='/new' render={() => (
            <Grid item xs={11} md={7}>
              <NewPost user={this.props.user} />
            </Grid>
          )}/>
          <Route path='/post/:id' render={() => (
            <Post user={this.props.user}/>
          )}/>
          <Route path='/category/:category' render={() => (
            <Grid item xs={11} md={7}>
              <Posts searchValue={this.state.searchValue} postOpen={this.handlePostOpen.bind(this)} />
            </Grid>
          )}/>
          <Route path='/' render={() => (
            <Grid item xs={11} md={7}>
              <Posts searchValue={this.state.searchValue} postOpen={this.handlePostOpen.bind(this)} />
            </Grid>
          )}/>
        </Switch>
      </Grid>
    );
  }
}

export default Home;