import React, { Component } from 'react';
import { Row, Container } from 'reactstrap';
import { Post } from './Post';

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

  getSize(index) {
    if (index < 2) {
      return 'lg';
    } else if (index % 5 == 0 || index % 5 == 1) {
      return 'md';
    } else {
      return 'sm';
    }
  }

  createPosts() {
    return this.props.posts.map((post, index) => (
      <Post
        key={post.id}
        size={this.getSize(this.props.index)}
        title={post.title}
        author={post.author.userName}
        date={post.date}
        body={this.convertStringToHtml(post.bodyItems)}
      />
    ));
  }

  render() {
    if (this.props.index < 2) {
      return <Row>{this.createPosts()}</Row>;
    }

    return (
      <Container>
        <Row>{this.createPosts()}</Row>
      </Container>
    );
  }
}
