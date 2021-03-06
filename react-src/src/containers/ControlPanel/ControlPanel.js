import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import axios from '../../axios-instance';
import AlertDialog from '../../components/AlertDialog/AlertDialog';
import Grid from '@material-ui/core/Grid';
import Loading from '../../components/Loading/Loading';

import './ControlPanel.css';

class ControlPanel extends Component {

  state = {
    showDialog: false,
    users: [],
    userToDelete: undefined,
    loading: false
  }

  componentDidMount() {
    this.updateUsers();
  }

  updateUsers() {
    const jwt = localStorage.getItem('accessToken');
    const options = {
      credentials: 'include',
      headers: {
        Authorization: `Bearer ${jwt}`
      }
    };

    this.setState({loading:true}, () =>
      axios.get('users/all',options)
        .then((res) => 
          this.setState({users: res.data, loading: false}))
        .catch(err => {
          alert('There was some error with the request.');
          console.log(err);
        })
    );
  }

  handleSelectChange(event, user) {
    const jwt = localStorage.getItem('accessToken');
    const options = {
      credentials: 'include',
      headers: {
        Authorization: `Bearer ${jwt}`
      }
    };

    let isAdmin;

    if(event.target.value === 'Admin') {
      isAdmin = true;
    } else if(event.target.value === 'User') {
      isAdmin = false;
    }

    axios.post(`users/role/${user.userId}/${isAdmin}`, undefined, options)
      .then(() => {
        this.updateUsers();

        if(this.props.user.userId === user.userId) {
          this.props.logout();
        }
      }).catch(err => {
        alert('There was some error with the request.');
        console.log(err);
      });
  }

  handleDeleteOnClick(event, user) {
    this.setState({showDialog: true, userToDelete: user});
  }

  handleDelete(name) {
    const jwt = localStorage.getItem('accessToken');
    const options = {
      credentials: 'include',
      headers: {
        Authorization: `Bearer ${jwt}`
      }
    };

    if(name === 'delete') {
      axios.delete(`users/${this.state.userToDelete.userId}`, options)
        .then(() => {
          this.updateUsers();

          if(this.props.user.userId === this.state.userToDelete.userId) {
            this.props.logout();
          }

        }).catch(err => {
          alert('There was some error with the request.');
          console.log(err);
        });
    }
    this.setState({showDialog: false});
  }

  createData(user) {
    let userId = user.userId;
    let userName = user.userName;
    let wholeName = user.wholeName;
    let role = '';
    if(user.roles.length > 1) {
      role = 'Admin';
    } else {
      role = 'User';
    }
    return {userId, userName, wholeName, role};
  }

  render() {

    const rows = this.state.users.map(data => this.createData(data));

    if (this.state.loading) {
      return (
        <Grid item container justify='center' xs={12}>
          <Loading loading={this.state.loading} />
        </Grid>
      );
    }
    
    return (
      <Paper className='table-wrapper'> 
        {this.state.showDialog ? <AlertDialog title='Delete user' description = {`Are you sure you want to delete ${this.state.userToDelete.userName}?`} handleClose={this.handleDelete.bind(this)} /> : null}
        <Table>
          <TableHead>
            <TableRow>
              <TableCell align='right'>User id</TableCell>
              <TableCell align='right'>Username</TableCell>
              <TableCell align='right'>Name</TableCell>
              <TableCell align='right'>Role</TableCell>
              <TableCell align='right'>Delete</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map(row => (
              <TableRow key={row.userId}>
                <TableCell component='th' scope='row'>
                  {row.userId}
                </TableCell>
                <TableCell align='right'>{row.userName}</TableCell>
                <TableCell align='right'>{row.wholeName}</TableCell>
                <TableCell align='right'>
                  <Select value={row.role} onChange={(event) => {
                    this.handleSelectChange(event, row);
                  }}>
                    <MenuItem value='Admin'>Admin</MenuItem>
                    <MenuItem value='User'>User</MenuItem>
                  </Select>
                </TableCell>
                <TableCell align='right'>
                  <IconButton onClick={(event) => {
                    this.handleDeleteOnClick(event, row);
                  }}>
                    <DeleteIcon/>
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}            
          </TableBody>
        </Table>
      </Paper>
    );
  } 
}

export default withRouter(ControlPanel);

