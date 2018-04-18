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

import pic4 from '../assets/pic4.jpg';
import ImageComponent from '../components/ImageComponent';

export default class AboutUsContainer extends Component {
  render() {
    return (
      <div style={{ width: '100%' }}>
        <ImageComponent width={'100%'} height={'400px'} image={pic4} />
        <Container>
          <AboutUs image={pic4} height={400} />
        </Container>
      </div>
    );
  }
}
