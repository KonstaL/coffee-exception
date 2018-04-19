import React, { Component } from 'react';
import { Card, Row, Col, Badge, Container } from 'reactstrap';
import { Link } from 'react-router-dom';
import Styled from 'styled-components';
import ImageComponent from './ImageComponent';

class ImageOverlay extends Component {
  render() {
    return (
      <ImageComponent
        height={this.props.height}
        width={this.props.width}
        img={this.props.image}
        linkTo={this.props.linkTo}
      >
        {this.props.children}
      </ImageComponent>
    );
  }
}

export default ImageOverlay;
