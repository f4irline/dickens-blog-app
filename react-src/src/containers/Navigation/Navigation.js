import React from 'react';
import Grid from '@material-ui/core/Grid';
import { withStyles } from '@material-ui/core/styles';
import NavItem from '../../components/NavItem/NavItem';

import Home from '@material-ui/icons/Home';
import Theaters from '@material-ui/icons/Theaters';
import Favorite from '@material-ui/icons/Favorite';
import MusicNote from '@material-ui/icons/MusicNote';
import School from '@material-ui/icons/School';
import Computer from '@material-ui/icons/Computer';

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
      <NavItem url='/' icon={<Home />}>Home</NavItem>
      <NavItem url='/category/tech' icon={<Computer />}>Tech</NavItem>
      <NavItem url='/category/studies' icon={<School />}>Studies</NavItem>
      <NavItem url='/category/movies' icon={<Theaters />}>Movies</NavItem>
      <NavItem url='/category/music' icon={<MusicNote />}>Music</NavItem>
      <NavItem url='/category/health' icon={<Favorite />}>Health</NavItem>
    </Grid>
  );
};

export default withStyles(styles)(Navigation);