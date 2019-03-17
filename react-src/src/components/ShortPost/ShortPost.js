import React from 'react';

import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import Button from '@material-ui/core/Button';
import { withStyles } from '@material-ui/core/styles';

import './ShortPost.css';

import headerImg from '../../assets/images/header_placeholder.jpg';

const styles = {
  post: {
    padding: '1vh',
    margin: '1vh',
    minHeight: '30vh',
    width: '100%'
  },
  contentWrapper: {
    height: '20vh',
    overflow: 'hidden'
  }
};

const ShortPost = (props) => {

  const { classes } = props;

  let post = props.data.body;
  let repeatPost = post.repeat(40);

  const handleClick = () => {
    props.postOpen(props.data);
  };
  
  return (
    <Paper classes={{root: classes.post}}>
      <Grid container direction='row'>
        <Grid container item xs={12} lg={6} direction='column' justify='space-between'>
          <Grid item>
            <Typography variant='title'>
              {props.data.title.toUpperCase()}
            </Typography>
            <Divider />
            <Typography variant='caption' classes={{root: classes.contentWrapper}}>
              {repeatPost}
            </Typography>
          </Grid>

          <Grid container direction='row' justify='space-between'>
            <Grid item xs={12} lg={6}>
              <Typography variant='body2'>
                Author: Matti
              </Typography>
              <Typography variant='body2'>
                {new Date().toUTCString()}
              </Typography>
            </Grid>
            <Grid item container justify='flex-end' xs={12} lg={6}>
              <Button variant='contained' color='primary' onClick={handleClick}>Read more!</Button>
            </Grid>
          </Grid>
        </Grid>
        <Grid container item xs={12} lg={6} justify='flex-end' style={{height: '30vh'}}> 
          <img src={headerImg} alt='Title' className='image' />
        </Grid>
      </Grid>
    </Paper>
  );
};

export default withStyles(styles)(ShortPost);