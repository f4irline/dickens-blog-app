import React from 'react';

import { RingLoader } from 'react-spinners';

const Loading = (props) => {
  return (
    <div className='sweet-loading'>
      <RingLoader
        sizeUnit={'em'}
        size={15}
        color='#2233FF'
        loading={props.loading}
      />
    </div> 
  );  
};

export default Loading;