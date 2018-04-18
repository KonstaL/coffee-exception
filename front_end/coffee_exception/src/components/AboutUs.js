import React, { Component } from 'react';
import { Card, Row, Col, Badge, Container } from 'reactstrap';
import { Link } from 'react-router-dom';
import Styled from 'styled-components';

const FooterText = Styled.span`
    color: rgba(180,180,180, 0.7);
`;

class AboutUs extends Component {
  render() {
    return <div>this is the about us component</div>;
  }
}

export default AboutUs;
