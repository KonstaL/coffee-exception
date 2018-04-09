import React, { Component } from 'react';
import { Card, Row, Col, Badge, Container } from 'reactstrap';
import { Link } from 'react-router-dom';
import Styled from 'styled-components';
import templogo from '../assets/templogo.PNG';

const HeaderDiv = Styled.div`
  margin-right: -15px;
  margin-left: -15px;
  background: ${props => (props.color ? props.color : 'white')}
`;

const FlexContainer = Styled.div`
    width: 100%;
    height: 100%;
    display: inline-flex;
    justify-content: space-evenly;
    align-items: center;
`;
const LogoImg = Styled.img`
    max-height: 100%;
    max-width: 100%;
    
`;

const LinkContainer = Styled.div`
    float: right;
    width: 300px;
    height: 100%;
`;

class Header extends Component {
  render() {
    return (
      <HeaderDiv color={this.props.color}>
        <Container>
          <Row>
            <Col md="4" xs="12">
              <Link to="/">
                <LogoImg src={templogo} />
              </Link>
            </Col>
            <Col md="8" xs="12">
              <LinkContainer>
                <FlexContainer>
                  <Link to="/about">About us</Link>
                  <Link to="/posts">Posts</Link>
                  <Link to="/login">Login</Link>
                </FlexContainer>
              </LinkContainer>
            </Col>
          </Row>
        </Container>
      </HeaderDiv>
    );
  }
}

export default Header;
