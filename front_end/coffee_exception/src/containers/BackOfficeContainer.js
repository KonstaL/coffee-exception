import React, { Component } from 'react';
import { Container, Row } from 'reactstrap';
import { fetchPosts } from '../actions/fetchPosts';
import { connect } from 'react-redux';
import { Post } from '../components/Post';
import _ from 'lodash';
export class BackOfficeContainer extends Component {
  componentDidMount() {
    this.props.fetchPosts();
  }

  createDate(date) {
    return `${date.dayOfMonth}/${date.monthValue}/${date.year}`;
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

  renderList() {
    if (!_.isEmpty(this.props.posts)) {
      return this.props.posts._embedded.blogposts.map(post => (
        <Row key={post.id} className="post-row-narrower">
          <Post
            id={post.id}
            trim={true}
            trimVal={100}
            author={post.username}
            title={post.title}
            date={post.date}
            comments={post.comments}
            likes={post.likes}
            body={this.convertStringToHtml(post.bodyItems)}
          />
          <div className="office-post-utils-container">
            <div className="office-post-action-container">
              <div className="office-post-action-edit">Edit</div>
              <div className="office-post-action-delete">Delete</div>
            </div>
          </div>
        </Row>
      ));
    }
  }
  render() {
    return (
      <Container style={{ maxWidth: 800 + 'px' }}>
        {this.renderList()}
      </Container>
    );
  }
}
function mapStateToProps(state) {
  return { posts: state.posts };
}

export default connect(mapStateToProps, { fetchPosts })(BackOfficeContainer);
