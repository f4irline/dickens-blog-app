import React, { Component } from 'react';
import Grid from '@material-ui/core/Grid';
import ShortPost from '../../components/ShortPost/ShortPost';

class Posts extends Component {

  state = {
    posts: this.props.posts
  }

  render() {

    let posts = this.state.posts.map((post) => {
      return <ShortPost postOpen={this.props.postOpen} key={post.id} data={post}/>;
    });

    return (
      <Grid container>
        {posts}
      </Grid>
    );
  }
}

export default Posts;