import React from 'react';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';

const Header = () => {
  return (
    <Grid direction="row" justify="flex-start" container>
      <Grid item xs={6}>
        <Typography>
          Blog site
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

export default Header;