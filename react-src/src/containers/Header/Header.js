import React from 'react';
import { NavLink } from 'react-router-dom';


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
    <Grid item xs={12} lg={6}>
      <NavLink to='/login'>
        <Button>
          Log in
        </Button>
      </NavLink>
      <p style={{display: 'inline-block', userSelect: 'none'}}>|</p>
      <NavLink to='/register'>
        <Button>
          Register
        </Button>
      </NavLink>
    </Grid>
  );

  if (props.loggedIn) {
    buttons = (
      <Grid item xs={12} lg={6}>
        <Button onClick={props.logout}>
          Logout
        </Button>
        <p style={{display: 'inline-block', userSelect: 'none'}}>|</p>
        <NavLink to='/new'>
          <Button>
            New Post
          </Button>
        </NavLink>
      </Grid>
    );
  }

  return (
    <Grid direction='row' justify='space-between' alignItems='center' container classes={{container: classes.container}}>
      <Grid item xs={12} lg={6}>
        <Typography variant='h3' style={{textTransform: 'uppercase'}}>
          Dickens Blog
        </Typography>
      </Grid>
      {buttons}
    </Grid>
  );
};

export default withStyles(styles)(Header);