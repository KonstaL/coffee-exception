import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import { Row, Col, Container } from 'reactstrap';
import Styled from 'styled-components';

import Markdown from 'markdown-to-jsx';

export default class AddPostContainer extends Component {
  constructor(props) {
    super(props);
    this.state = { editorText: '' };
  }

  handleChange(e) {
    this.setState({ editorText: e.target.value });
  }

  render() {
    return (
      <Container fluid={true}>
        <Row>
          <Col xl={6}>
            <textarea onChange={this.handleChange.bind(this)} />
          </Col>

          <Col xl={6}>
            <Markdown>{this.state.editorText}</Markdown>
          </Col>
        </Row>
      </Container>
    );
  }
}
