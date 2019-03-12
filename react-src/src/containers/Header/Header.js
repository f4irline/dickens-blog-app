import React from 'react';
import Grid from '@material-ui/core/Grid';
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';

const styles = {
  container: {
    padding: '1vh',
    marginTop: '1vh',
    textAlign: 'center'
  }
};

const Header = (props) => {
  
  const { classes } = props;

  return (
    <Grid direction='row' justify='space-between' alignItems='center' container classes={{container: classes.container}}>
      <Grid item xs={6}>
        <Typography variant='h3' style={{textTransform: 'uppercase'}}>
          Dickens Blog
        </Typography>
      </Grid>
      <Grid item xs={6}>
        <Button>
          Log in
        </Button>
        <p style={{display: 'inline-block', userSelect: 'none'}}>|</p>
        <Button>
          Register
        </Button>
      </Grid>
    </Grid>
  );
};

export default withStyles(styles)(Header);