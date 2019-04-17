import React from 'react';

import { Link } from 'react-router-dom';

import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import Button from '@material-ui/core/Button';
import { withStyles } from '@material-ui/core/styles';

import Markdown from '../../utils/Markdown';

import headerImg from '../../assets/images/header_placeholder.jpg';

import './ShortPost.css';

const styles = {
  post: {
    padding: '1vh',
    margin: '1vh 0',
    minHeight: '30vh',
    width: '100%',
  },
  contentWrapper: {
    height: '21vh',
    overflow: 'hidden',
    margin: '2vh',
    wordWrap: 'break-word'
  }
};

const ShortPost = (props) => {

  const { classes } = props;

  const handleClick = () => {
    props.postOpen(props.data);
  };

  function addDefaultSrc(event) {
    event.target.src = headerImg;
  }
  
  return (
    <Paper classes={{root: classes.post}} elevation={5}>
      <Grid container direction='row'>
        <Grid container item xs={12} md={6} direction='column' justify='space-between'>
          <Grid item style={{width: '100%'}}>
            <Typography variant='title'>
              {props.data.title.toUpperCase()}
            </Typography>
            <Divider />
            <div className='body-wrapper'>
              <Markdown>{props.data.body}</Markdown>
            </div>
          </Grid>

          <Grid container direction='row' justify='space-between'>
            <Grid item xs={6} md={6}>
              <Typography variant='body2'>
                Author: {props.data.author.userFirst} {props.data.author.userLast}
              </Typography>
              <Typography variant='body2'>
                {new Date(props.data.postDate).toLocaleString('en-GB')}
              </Typography>
            </Grid>
            <Grid item container justify='flex-end' xs={6} md={6}>
              <Link to={'/post/'+props.data.postId} style={{textDecoration: 'none'}}>
                <Button size='small' variant='contained' color='primary' onClick={handleClick}>Read more!</Button>
              </Link>
            </Grid>
          </Grid>
        </Grid>
        <Grid container item xs={12} md={6} justify='center' alignItems='center' style={{height: '35vh'}}> 
          <img onError={addDefaultSrc} src={props.data.imgUrl} alt='Title' className='image' />
        </Grid>
      </Grid>
    </Paper>
  );
};

export default withStyles(styles)(ShortPost);