import React from 'react';

import MemberEvents from './MemberEvents';

var style = {

  body: {
    position: 'relative',
    backgroundColor: 'lightgray',
    width: '100%',
    height: '100vh'
  }
}

class BookedEvents extends React.Component {
  render() {
    return (
      <MemberEvents>
        <div style={style.body}>
          <h3>Booked Events page</h3>
        </div>
      </MemberEvents>
    );
  }
}

export default BookedEvents;
