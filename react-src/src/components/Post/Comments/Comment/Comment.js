import React from 'react';

import Divider from '@material-ui/core/Divider';
import Typography from '@material-ui/core/Typography';

const Comment = (props) => {

  const comment = props.comment;

  console.log(comment);

  return (
    <div>
      <Typography variant='caption' style={{fontSize: '2vh', margin: '1vh 0', textAlign: 'start', textTransform: 'uppercase'}}>
        {comment.author.userFirst}
      </Typography>
      <Typography variant='body1'>
        {comment.body}
      </Typography>
      <Typography variant='caption' style={{fontSize: '2vh', margin: '1vh 0', textAlign: 'end'}}>
        {new Date(comment.postDate).toLocaleString('en-GB')}    
      </Typography>
      <Divider />
    </div>
  );
};

export default Comment;