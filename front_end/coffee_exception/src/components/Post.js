import React, { Component } from 'react';
import { Card, Row, Col, Badge } from 'reactstrap';

export class Post extends Component {
  render() {
    return (
      <Card style={{ padding: 20 + 'px', margin: 10 + 'px' }}>
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
