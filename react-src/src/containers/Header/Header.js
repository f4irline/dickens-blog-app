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
        <Button style={{color: '#555'}}>
          Log in
        </Button>
      </Link>
      <p style={{display: 'inline-block', userSelect: 'none'}}>|</p>
      <Link to='/register' style={{textDecoration: 'none'}}>
        <Button style={{color: '#555'}}>
          Register
        </Button>
      </Link>
    </Grid>
  );

  if (props.loggedIn) {
    buttons = (
      <Grid item xs={12} md={6}>
        <Button onClick={props.logout} style={{color: '#555'}}>
          Logout
        </Button>
        {props.user.roles[1].definition === 'ROLE_ADMIN' ? 
          <React.Fragment>
            <p style={{display: 'inline-block', userSelect: 'none'}}>|</p>
            <Link to='/new' style={{textDecoration: 'none', color: '#444'}}>
              <Button>New Post</Button>
            </Link>
          </React.Fragment> : null}
      </Grid>
    );
  }

  return (
    <Grid direction='row' justify='space-between' alignItems='center' container classes={{container: classes.container}}>
      <Grid item xs={12} md={6}>
        <Typography variant='h3' style={{textTransform: 'uppercase', color: '#444'}}>
          Dickens Blog
        </Typography>
      </Grid>
      {buttons}
    </Grid>
  );
};

export default withStyles(styles)(Header);