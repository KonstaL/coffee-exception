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

  createPosts(val, maxHeight) {
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
          id={post.id}
          height={maxHeight}
          pic={post.bannerUrl}
          trim={true}
          trimVal={250}
          author={post.user.username}
          title={post.title}
          date={post.date}
          comments={post.comments}
          likes={post.likes}
          body={this.convertStringToHtml(post.bodyItems)}
        />
      </Col>
    ));
  }

  render() {
    if (this.props.index % 5 === 0 || this.props.index % 5 === 1) {
      return (
        <Row className="post-row-narrow">
          {this.createPosts({ md: 12, lg: 6, xl: 6 }, 300)}
        </Row>
      );
    }

    return (
      <Row className="post-row-narrow">
        {this.createPosts({ md: 12, lg: 4, xl: 4 }, 200)}
      </Row>
    );
  }
}
/*

  ROW {
    <COL xl="8">
      <Post/>
    </COL>
    <COL xl="4">
      <Post/>
      <Post/>
    </COL>
  }
     3
     2 5
     3
     2 10
     3
     2 15
     3
     2 20
*/
