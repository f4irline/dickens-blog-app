import React from 'react';
import { Link } from 'react-router-dom';


import Grid from '@material-ui/core/Grid';
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';

const styles = {
  container: {
    padding: '1vh',
    marginTop: '2vh',
    textAlign: 'center'
  }
};

const Header = (props) => {
  
  const { classes } = props;

  let buttons = (
    <Grid item xs={12} md={6}>
      <Link to='/login' style={{textDecoration: 'none'}}>
        <Button>
          Log in
        </Button>
      </Link>
      <p style={{display: 'inline-block', userSelect: 'none'}}>|</p>
      <Link to='/register' style={{textDecoration: 'none'}}>
        <Button>
          Register
        </Button>
      </Link>
    </Grid>
  );

  if (props.loggedIn) {
    buttons = (
      <Grid item xs={12} md={6}>
        <Button onClick={props.logout}>
          Logout
        </Button>
        <p style={{display: 'inline-block', userSelect: 'none'}}>|</p>
        <Link to='/new' style={{textDecoration: 'none'}}>
          <Button>
            New Post
          </Button>
        </Link>
      </Grid>
    );
  }

  return (
    <Grid direction='row' justify='space-between' alignItems='center' container classes={{container: classes.container}}>
      <Grid item xs={12} md={6}>
        <Typography variant='h3' style={{textTransform: 'uppercase'}}>
          Dickens Blog
        </Typography>
      </Grid>
      {buttons}
    </Grid>
  );
};

export default withStyles(styles)(Header);