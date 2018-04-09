import React, { Component } from 'react';
import { Card, Row, Col, Badge } from 'reactstrap';

export class Post extends Component {
  constructor() {
    super();
    this.state = {
      size: 0
    };
  }

  componentWillMount() {
    let size = 0;
    switch (this.props.size) {
      case 'lg':
        size = 48;
        break;
      case 'md':
        size = 45;
        break;
      case 'sm':
        size = 30;
        break;
    }
    this.setState({ size: size });
  }

  render() {
    return (
      <Card
        style={{
          padding: 20 + 'px',
          margin: 10 + 'px',
          flex: '0 1 auto',
          flexBasis: this.state.size + '%',
          flexWrap: 'wrap'
        }}
      >
        <Row style={{ marginBottom: 10 + 'px' }}>
          <Col md="9">
            <h3>{this.props.title}</h3>
          </Col>
          <Col md="3" style={{ overflow: 'hidden' }}>
            <Row>
              <Badge color="primary" style={{ marginRight: 5 + 'px' }}>
                {this.props.author}
              </Badge>
              <Badge color="secondary">{this.props.date}</Badge>
            </Row>
          </Col>
        </Row>
        <Row>
          <Col md="12">
            <div>{this.props.body}</div>
          </Col>
        </Row>
      </Card>
    );
  }
}
