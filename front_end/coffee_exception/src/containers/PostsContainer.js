import React, { Component } from 'react';
import { fetchPosts } from '../actions/fetchPosts';
import { connect } from 'react-redux';
import { Container, Row } from 'reactstrap';
import { Post } from '../components/Post';

class PostsContainer extends Component {
  componentDidMount() {
    this.props.fetchPosts();
  }

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

  renderListFromData() {
    if (Object.getOwnPropertyNames(this.props.posts).length > 0) {
      return this.props.posts._embedded.blogposts.map(post => (
        <Row>
          <Post
            key={post.id}
            title={post.title}
            body={this.convertStringToHtml(post.bodyItems)}
          />
        </Row>
      ));
    }
  }

  render() {
    return <Container>{this.renderListFromData()}</Container>;
  }
}

function mapStateToProps(state) {
  return { posts: state.posts };
}

export default connect(mapStateToProps, { fetchPosts })(PostsContainer);
