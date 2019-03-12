import React, { Component } from 'react';

import Grid from '@material-ui/core/Grid';
import Posts from '../Posts/Posts';
import axios from 'axios';

class Home extends Component {

  state = {
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
      <Grid direction='row' justify='center' container className='Home'>
        <Grid item xs={10}>
          <Posts posts={this.state.posts} />
        </Grid>
      </Grid>
    );
  }
}

export default Home;