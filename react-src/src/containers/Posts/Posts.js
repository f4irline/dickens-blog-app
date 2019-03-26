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
    if (this.props.match.params !== undefined) {
      this.loadCategoryPosts(this.props.match.params);
    } else {
      this.loadPosts();
    }
  }

  componentWillReceiveProps(newProps) {
    if (this.props.match.params !== newProps.match.params) {
      if (newProps.match.params !== undefined) {
        this.loadCategoryPosts(newProps.match.params);
      }
    }
  }

  loadPosts() {
    this.setState({loading: true}, () => {
      axios.get('/posts/all')
        .then((res) => {
          this.setState({posts: res.data});
          this.delayLoadingResolve();
        });
    });
  }

  loadCategoryPosts(cat) {
    const { category } = cat;
    if (category === undefined) {
      this.loadPosts();
    } else {
      this.setState({loading: true}, () => {
        axios.get(`/posts/category/${category}`)
          .then((res) => {
            this.setState({posts: res.data});
            this.delayLoadingResolve();
          });
      });  
    }
  }

  delayLoadingResolve() {
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