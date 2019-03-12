import React from 'react';
import { NavLink } from 'react-router-dom';
import Grid from '@material-ui/core/Grid';

const NavItem = (props) => {
  return (
    <Grid item>
      <NavLink to={props.url}>{props.children}</NavLink>
    </Grid>
  );
};

export default NavItem;