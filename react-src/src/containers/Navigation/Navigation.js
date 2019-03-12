import React, { Component } from 'react';
import Grid from '@material-ui/core/Grid';
import NavItem from '../../components/NavItem/NavItem';

class Navigation extends Component {
  render() {
    return (
      <Grid direction='row' justify='space-around' alignItems='center' container className='Navigation'>
        <NavItem url='/'>Home</NavItem>
        <NavItem url='/category-1'>Cat-1</NavItem>
        <NavItem url='/category-2'>Cat-2</NavItem>
        <NavItem url='/category-3'>Cat-3</NavItem>
        <NavItem url='/category-4'>Cat-4</NavItem>
        <NavItem url='/category-5'>Cat-5</NavItem>
        <NavItem url='/category-6'>Cat-6</NavItem>
        <NavItem url='/category-7'>Cat-7</NavItem>
      </Grid>
    );
  }
}

export default Navigation;