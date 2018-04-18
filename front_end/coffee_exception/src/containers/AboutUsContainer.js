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
import AboutUs from '../components/AboutUs';

export default class AboutUsContainer extends Component {
  render() {
    return (
      <Container>
        <AboutUs />
      </Container>
    );
  }
}
