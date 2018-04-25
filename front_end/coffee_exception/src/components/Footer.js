import React, { Component } from 'react';
import { Card, Row, Col, Badge, Container } from 'reactstrap';
import { Link } from 'react-router-dom';
import Styled from 'styled-components';
import templogo from '../assets/templogo.png';

const FooterDiv = Styled.div`
  margin-right: -15px;
  margin-left: -15px;
  background: ${props => (props.color ? props.color : 'white')}
`;

const FlexContainer = Styled.div`
    width: 100%;
    height: 60px;
    display: inline-flex;
    justify-content: center;
    align-items: center;
`;

const FooterText = Styled.span`
    color: rgba(180,180,180, 0.7);
`;

class Footer extends Component {
  render() {
    return (
      <FooterDiv color={this.props.color}>
        <Container>
          <Row>
            <Col xl="12">
              <FlexContainer>
                <FooterText>For those who want to get ahead</FooterText>
              </FlexContainer>
            </Col>
          </Row>
        </Container>
      </FooterDiv>
    );
  }
}

export default Footer;
