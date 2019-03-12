import React from 'react';
import Grid from '@material-ui/core/Grid';
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';

const styles = {
  container: {
    padding: '1vh'
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
        <Typography>
          Sign in
        </Typography>
      </Grid>
    </Grid>
  );
};

export default withStyles(styles)(Header);