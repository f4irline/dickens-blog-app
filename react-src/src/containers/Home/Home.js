import React, { Component } from 'react';

import Grid from '@material-ui/core/Grid';

import axios from 'axios';

import Posts from '../Posts/Posts';

import './Home.css';

class Home extends Component {

  state = {
    loading: true,
    posts: {},
    modalOpen: false,
    openPost: {}
  }

  componentDidMount() {
    axios.get('https://jsonplaceholder.typicode.com/posts')
      .then((res) => {
        this.setState({posts: res.data, loading: false});
      });
  }

  handlePostOpen(post) {
    this.setState({openPost: post}, () => {
      this.setState({modalOpen: true});
    });
  }
  
  render() {

    if (this.state.loading) {
      return (
        <Grid direction='row' justify='center' container className='Home'>
          <Grid item xs={10}>
            <p style={{textAlign: 'center'}}>Loading...</p>
          </Grid>
        </Grid>
      );
    }

    return (
      <Grid direction='row' justify='center' container className='Home'>
        <Grid item xs={10}>
          <Posts postOpen={this.handlePostOpen.bind(this)} posts={this.state.posts} />
        </Grid>
      </Grid>
    );
  }
}

export default Home;