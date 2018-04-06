import React, { Component } from 'react';
import { fetchPosts } from '../actions/fetchPosts';
import { connect } from 'react-redux';

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
    return this.props.posts.map(post => (
      <Post
        key={post.id}
        title={post.title}
        author={post.author.userName}
        date={post.date}
        body={this.convertStringToHtml(post.bodyItems)}
      />
    ));
  }

  render() {
    return (
      <div>
        <ul>{this.renderListFromData()}</ul>
      </div>
    );
  }
}

function mapStateToProps(state) {
  return { posts: state.posts };
}

export default connect(mapStateToProps, { fetchPosts })(PostsContainer);
