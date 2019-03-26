import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';

class AlertDialog extends React.Component {
  state = {
    open: true,
    title: this.props.title,
    description: this.props.description
  };

  handleCancel = () => {
    this.props.handleClose('cancel');
  };

  handleDelete = () => {
    this.props.handleClose('delete');
  };

  render() {
    return (
      <div>
        <Dialog
          open={this.state.open}
          onClose={this.handleClose}
        >
          <DialogTitle>{this.state.title}</DialogTitle>
          <DialogContent>
            <DialogContentText>
              {this.state.description}
            </DialogContentText>
          </DialogContent>
          <DialogActions>
            <Button onClick={this.handleCancel} color="secondary" name="cancel">
              Cancel
            </Button>
            <Button onClick={this.handleDelete} color="primary" name="delete">
              Delete
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  }
}

export default AlertDialog;