import React, {Component} from 'react';

import Divider from '@material-ui/core/Divider';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import DeleteIcon from '@material-ui/icons/Delete';
import IconButton from '@material-ui/core/IconButton';
import Grid from '@material-ui/core/Grid';

import Markdown from '../../../../utils/Markdown';
import AlertDialog from '../../../AlertDialog/AlertDialog';

class Comment extends Component {
  
  state = {
    comment: this.props.comment,
    showDialog: false,
  }

  onClickOpenHandler() {
    this.setState({showDialog: true});
  }

  onClickCloseHandler(name) {
    if (name === 'delete') {
      this.props.handleDelete(this.state.comment.commentId);
    }
    this.setState({showDialog: false});
  }

  render() {
    return (
      <div>
        {this.state.showDialog ? <AlertDialog title="Titteli" description = "Description" handleClose={this.onClickCloseHandler.bind(this)} /> : null}
        <Typography variant='caption' style={{fontSize: '2vh', margin: '1vh 0', textAlign: 'start', textTransform: 'uppercase'}}>
          {this.state.comment.author.userFirst}
        </Typography>
        <Typography variant='body1'>
          {this.state.comment.body}
        </Typography>
  
        <Grid direction='row' justify='center' container className='Home'>
          <Grid item xs={6}>
            <IconButton aria-label="Delete" onClick={this.onClickOpenHandler.bind(this)}>
              <DeleteIcon />
            </IconButton>
          </Grid>
          <Grid item xs={6}>
            <Typography variant='caption' style={{fontSize: '2vh', margin: '1vh 0', textAlign: 'end'}}>
              {new Date(this.state.comment.postDate).toLocaleString('en-GB')}    
            </Typography>
          </Grid>
        </Grid>
        <Divider />
      </div>
    );
  }
}

export default Comment;