import React, { Component } from 'react';
import Grid from '@material-ui/core/Grid';
import ShortPost from '../../components/ShortPost/ShortPost';
import Loading from '../../components/Loading/Loading';

import { withRouter } from 'react-router-dom';
import axios from '../../axios-instance';

class Posts extends Component {

  state = {
    loading: true,
    posts: {}
  }

  componentDidMount() {
    const { category } = this.props.match.params;
    if(category === undefined) {
      axios.get('/posts/all')
        .then((res) => {
          this.setState({posts: res.data});
          this.delayLoadingPostsResolve();
        });
    } else {
      axios.get(`/posts/category/${category}`)
        .then((res) => {
          this.setState({posts: res.data});
          this.delayLoadingPostsResolve();
        });
    }
  }

  delayLoadingPostsResolve() {
    setTimeout(() => {
      this.setState({loading: false});
    }, 1500);
  }

  render() {

    if (this.state.loading) {
      return (
        <Grid item container justify='center' xs={12}>
          <Loading loading={this.state.loading} />
        </Grid>
      );
    }

    let posts = this.state.posts.map((post) => {
      console.log(post);
      return <ShortPost postOpen={this.props.postOpen} key={post.postId} data={post}/>;
    });

    return (
      <Grid container>
        {posts}
      </Grid>
    );
  }
}

export default withRouter(Posts);