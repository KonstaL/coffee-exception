import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import {
  Form,
  FormGroup,
  FormFeedback,
  Input,
  Label,
  Container,
  Button,
  FormText
} from 'reactstrap';
import Styled from 'styled-components';

const LoginButton = Styled.button`
  border-radius: 0.25rem;
  padding:6px 12px;  
  background-color: rgb(47, 60, 71);
  color:white;
  border:none;
  :focus {
    outline:none;
  }
  :active {
    background-color: rgb(64, 83, 96);
  }
`;

export default class LoginContainer extends Component {
  testing(event) {
    event.preventDefault();
    document.getElementById('login-form').classList.add('was-validated');
    console.log(document.getElementById('username').value);
    console.log(document.getElementById('password').value);
  }

  render() {
    return (
      <Container>
        <Form id="login-form">
          <FormFeedback valid>Error</FormFeedback>
          <FormGroup>
            <Label for="username">Username</Label>
            <Input type="text" id="username" minLength="3" required />
            <FormFeedback invalid>Please insert username</FormFeedback>
          </FormGroup>
          <FormGroup>
            <Label for="password">Password</Label>
            <Input type="password" id="password" minLength="3" required />
            <FormFeedback invalid>Please insert password</FormFeedback>
          </FormGroup>
          <FormGroup>
            <FormText color="muted" style={{ marginBottom: 10 + 'px' }}>
              Don't have an account? <Link to="/">Register</Link>
            </FormText>
            <LoginButton onClick={this.testing} type="submit">
              Login
            </LoginButton>
          </FormGroup>
        </Form>
      </Container>
    );
  }
}
