import React, { Component } from 'react';
import { fetchPost } from '../actions/fetchPost';
import { connect } from 'react-redux';
import { Container, Row } from 'reactstrap';
import { Post } from '../components/Post';
import { PostComments } from '../components/PostComments';
import _ from 'lodash';

class PostContainer extends Component {
  componentDidMount() {
    this.props.fetchPost(this.props.id);
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

  render() {
    if (_.isEmpty(this.props.post)) {
      return <div>true</div>;
    } else {
      return (
        <Container style={{ maxWidth: 800 + 'px' }}>
          <Post
            id={this.props.post.id}
            height={400}
            pic={this.props.post.bannerUrl}
            author={this.props.post.username}
            title={this.props.post.title}
            date={this.props.post.date}
            comments={this.props.post.comments}
            likes={this.props.post.likes}
            body={this.convertStringToHtml(this.props.post.bodyItems)}
          />
          <PostComments comments={this.props.post.comments} />
        </Container>
      );
    }
  }
}

function mapStateToProps(state) {
  return { post: state.post };
}

export default connect(mapStateToProps, { fetchPost })(PostContainer);
