import React, { Component } from 'react';
import { Row, Container, Col } from 'reactstrap';
import { Post } from './Post';
import _ from 'lodash';

export class PostRow extends Component {
  convertStringToHtml(items) {
    const parser = new DOMParser();
    const elements = items.map(item => {
      const doc = parser.parseFromString(item, 'text/html');
      return [...doc.body.childNodes];
    });

    return elements.map((elem, index) => (
      <p key={index}>{elem[0].innerHTML}</p>
    ));
  }

  createPosts(val) {
    let defaultColValues = {
      xs: 12,
      sm: 12,
      md: 12,
      lg: 12,
      xl: 12
    };

    let result = _.assign({}, defaultColValues, val);

    return this.props.posts.map((post, index) => (
      <Col
        className="post-column"
        key={post.id}
        xs={result.xs}
        sm={result.sm}
        md={result.md}
        lg={result.lg}
        xl={result.xl}
      >
        <Post
          title={post.title}
          author={post.author.userName}
          date={post.date}
          body={this.convertStringToHtml(post.bodyItems)}
        />
      </Col>
    ));
  }

  render() {
    if (this.props.index < 2) {
      return (
        <Row style={{ justifyContent: 'center' }}>
          {this.createPosts({ lg: 12, xl: 6 })}
        </Row>
      );
    }

    if (this.props.index % 5 === 0 || this.props.index % 5 === 1) {
      return (
        <Row style={{ justifyContent: 'center', maxWidth: 1100 + 'px' }}>
          {this.createPosts({ md: 12, xl: 6 })}
        </Row>
      );
    }

    return (
      <Row style={{ justifyContent: 'center', maxWidth: 1100 + 'px' }}>
        {this.createPosts({ md: 12, xl: 4 })}
      </Row>
    );
  }
}