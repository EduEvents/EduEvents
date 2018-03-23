import React from 'react';

import Client from '../Client';

var style = {

  form: {
    position: 'relative',
    marginLeft: '250px',
    top: '2%',
    width: '12%'
  },

  button: {
    backgroundColor: '#3baf36',
    color: 'white'
  }
}

class SignInForm extends React.Component {

  constructor() {
    super();
    this.state = {
      username: '',
      password: ''
    }

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onChange(e) {
    this.setState({
      [e.target.name]: e.target.value
    });
  }

  async onSubmit(e) {
    e.preventDefault();
    //Can console log this to see what is passed.
    Client.postLogin(this.state.username, this.state.password);
    //Test login variables sent
    //console.log(Client.testLogin());
  }

  render() {
    return (
      <form onSubmit={this.onSubmit} style={style.form}>

        <div className="form-group">
          <label className="control-label">Username</label>
          <input
            value={this.state.username}
            onChange={this.onChange}
            type="text"
            name="username"
            className="form-control"
          />
        </div>

        <div className="form-group">
          <label className="control-label">Password</label>
          <input
            value={this.state.password}
            onChange={this.onChange}
            type="password"
            name="password"
            className="form-control"
          />
        </div>

        <div className="form-group">
          <button className="btn" style={style.button}>
            Login
          </button>
        </div>
      </form>
    );
  }
}

export default SignInForm;
