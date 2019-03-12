import React from 'react';
import { NavLink } from 'react-router-dom';
import Grid from '@material-ui/core/Grid';

const NavItem = (props) => {
  return (
    <Grid item>
      <NavLink style={{textTransform: 'uppercase'}} to={props.url}>{props.children}</NavLink>
    </Grid>
  );
};

export default NavItem;