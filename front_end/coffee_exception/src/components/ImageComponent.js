import React, { Component } from 'react';
import { Card, Row, Col, Badge, Container } from 'reactstrap';
import { Link } from 'react-router-dom';
import Styled from 'styled-components';

const ImageDiv = Styled.div`
  overflow:hidden;
  height:${props => (props.height ? props.height : +'500px')};
  width:${props => (props.width ? props.width : '500px')};
  background-image: url("${props =>
    props.img ? props.img : 'http://via.placeholder.com/500x500'}");
  background-size:cover;
  background-position: center;
  border: ${props => (props.border ? '1px solid rgb(230,230,230)' : 'none')};
  text-align: center
`;

class ImageComponent extends Component {
  render() {
    if (this.props.linkTo != undefined) {
      return (
        <Link to={this.props.linkTo}>
          <ImageDiv
            height={this.props.height}
            width={this.props.width}
            img={this.props.image}
          >
            {this.props.children}
          </ImageDiv>
        </Link>
      );
    } else {
      return (
        <ImageDiv
          height={this.props.height}
          width={this.props.width}
          img={this.props.image}
          border={this.props.border}
        >
          {this.props.children}
        </ImageDiv>
      );
    }
  }
}

export default ImageComponent;
