import React, { Component } from 'react';
import { Card, Row, Col, Badge, Container } from 'reactstrap';
import { Link } from 'react-router-dom';
import Styled from 'styled-components';
import templogo from '../assets/templogo.png';

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

const StyledLink = Styled(Link)`
    color: white;
    font-weight: bold;

    &:after {
      content:'';
      width:0px;
      height:1px;
      display:block;
      background:white;
      transition: all .1s ease-in;
    }

    &:hover {
      text-decoration: none;
      color: white;
    }
    &:hover:after {
      width: 100%;
    }
`;

class Header extends Component {
  render() {
    return (
      <HeaderDiv color={this.props.color}>
        <Container>
          <Row style={{ maxWidth: 1200 + 'px' }}>
            <Col md="4" xs="12">
              <Link to="/">
                <LogoImg src={templogo} />
              </Link>
            </Col>
            <Col md="8" xs="12">
              <LinkContainer>
                <FlexContainer>
                  <StyledLink to="/about">About us</StyledLink>
                  <StyledLink to="/posts">Posts</StyledLink>
                  <StyledLink to="/login">Login</StyledLink>
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
