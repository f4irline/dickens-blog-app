import React from 'react';
import Grid from '@material-ui/core/Grid';
import { withStyles } from '@material-ui/core/styles';
import NavItem from '../../components/NavItem/NavItem';

const styles = {
  container: {
    padding: '1vh',
    marginBottom: '1vh',
  }
};

const Navigation = (props) => {

  const { classes } = props;

  return (
    <Grid direction='row' justify='space-around' alignItems='center' container classes={{container: classes.container}}>
      <NavItem url='/'>Home</NavItem>
      <NavItem url='/category/culture'>Culture</NavItem>
      <NavItem url='/category/tech'>Tech</NavItem>
      <NavItem url='/category/politics'>Politics</NavItem>
      <NavItem url='/category/studies'>Studies</NavItem>
      <NavItem url='/category/health'>Health</NavItem>
     {/* <NavItem url='/category-6'>Cat-6</NavItem>
     <NavItem url='/category-7'>Cat-7</NavItem> */}
    </Grid>
  );
};

export default withStyles(styles)(Navigation);