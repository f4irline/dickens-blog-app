import React from 'react';
import { NavLink } from 'react-router-dom';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';

import './NavItem.css';

const NavItem = (props) => {
  return (
    <Grid className='NavItem' item>
      <NavLink className='link-wrapper' to={props.url} exact>{props.icon}<Typography variant='subtitle1' classes={{root: 'item'}}>{props.children}</Typography></NavLink>
    </Grid>
  );
};

export default NavItem;