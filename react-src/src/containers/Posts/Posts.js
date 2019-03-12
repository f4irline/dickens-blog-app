import React, { Component } from 'react';
import Grid from '@material-ui/core/Grid';
import Post from '../../components/Post/Post';

class Posts extends Component {

  state = {
    posts: this.props.posts
  }

  render() {

    let posts = this.state.posts.map((post) => {
      return <Post key={post.id} data={post}/>;
    });

    return (
      <Grid container>
        {posts}
      </Grid>
    );
  }
}

export default Posts;