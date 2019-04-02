import React, { Component } from 'react';

import { Route, Switch } from 'react-router-dom';

import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Divider from '@material-ui/core/Divider';
import Header from '../Header/Header';
import Navigation from '../Navigation/Navigation';
import TextField from '@material-ui/core/TextField';

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

  onSearchSubmit(e) {
    this.setState({searchValue: e.target.value})
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
          <Route path='/category/:category' render={() => (
            <Grid item xs={11} md={7}>
              <Posts postOpen={this.handlePostOpen.bind(this)} />
            </Grid>
          )}/>
          <Route path={'/search/'} render={() => (
            <Grid item xs={11} md={7}>
              <TextField
                onKeyPress={(ev) => {
                    if (ev.key === 'Enter') {
                      this.onSearchSubmit(ev);
                      ev.preventDefault();
                    }
                  }}
                id='outlined-full-width'
                label='SEARCH'
                style={{ margin: 8 }}
                placeholder='Find posts by title'
                helperText='Hope it helps!'
                fullWidth
                margin='normal'
                variant='outlined'
                InputLabelProps={{
                  shrink: true,
                }}
              />
              <Posts postOpen={this.handlePostOpen.bind(this)} searchValue={this.state.searchValue} />
            </Grid>
          )} />
        </Switch>
      </Grid>
    );
  }
}

export default Home;