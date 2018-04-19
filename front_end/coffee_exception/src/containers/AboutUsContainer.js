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
import ImageOverlay from '../components/ImageOverlay';

export default class AboutUsContainer extends Component {
  render() {
    return (
      <div style={{ width: '100%' }}>
        <ImageComponent width={'100%'} height={'500px'} image={pic4}>
          <h1
            style={{
              color: 'white',
              padding: '10% 20% 5%',
              'text-shadow': '2px 3px 3px rgba(0,0,0,0.7)'
            }}
          >
            "We build our computer (systems) the way we build our cities: over
            time, without a plan, on top of ruins."
          </h1>
          <h1 style={{ color: 'white' }}>- Ellen Ullman</h1>
        </ImageComponent>
        <Container>
          <AboutUs />
        </Container>
      </div>
    );
  }
}
