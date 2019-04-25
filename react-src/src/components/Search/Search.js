import React from 'react';

import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';

const Search = (props) => {

  const onSearchSubmit = (e) => {
    props.onSearch(e.target.value);
    e.target.value = '';
  };

  return (
    <Grid container item md={3}>
      <TextField
        onKeyPress={(ev) => {
          if (ev.key === 'Enter') {
            onSearchSubmit(ev);
            ev.preventDefault();
          }
        }}
        label='SEARCH'
        style={{margin: 8}}
        placeholder='Find posts by title'
        fullWidth
        margin='normal'
        variant='outlined'
        InputLabelProps={{
          shrink: true,
        }}
      />
    </Grid>
  );
};

export default Search;