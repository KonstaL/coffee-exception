import React, { Component } from 'react';
import { fetchTrendingPosts } from '../actions/fetchTrendingPosts';
import { connect } from 'react-redux';
import { Card, Row, Col, CardDeck, Container } from 'reactstrap';
import { Post } from '../components/Post';
import { PostRow } from '../components/PostRow';

class TrendingPostsContainer extends Component {
  componentDidMount() {
    this.props.fetchTrendingPosts();
  }

  renderPostsFromData() {
    let arr = [];
    let rows = [];
    let threeInARow = false;
    let index = 0;

    if (this.props.posts != 0) {
      for (let i = 0; i < this.props.posts.length; i++) {
        if (i <= 1 || (i > 1 && !threeInARow)) {
          index = i;
          arr.push(this.props.posts[i], this.props.posts[i + 1]);
          i = i + 1;
        } else if (threeInARow) {
          index = i;
          arr.push(
            this.props.posts[i],
            this.props.posts[i + 1],
            this.props.posts[i + 2]
          );
          i = i + 2;
        }
        threeInARow = !threeInARow;
        rows.push(<PostRow index={i} key={rows.length} posts={arr} />);
        arr = [];
      }
    }

    return rows;
  }

  /**
   *
   * <container>
   *
   * 2-3 posts
   *
   *
   * <container>
   */

  render() {
    return (
      <Container fluid={true}>
        <Row>
          <Container fluid={true}>
            <Row>
              <CardDeck />
            </Row>
          </Container>
        </Row>
        <Row>
          <Container fluid={true}>
            <CardDeck style={{ justifyContent: 'center' }}>
              {this.renderPostsFromData()}
            </CardDeck>
          </Container>
        </Row>
      </Container>
    );
  }
}

function mapStateToProps(state) {
  return { posts: state.posts };
}

export default connect(mapStateToProps, { fetchTrendingPosts })(
  TrendingPostsContainer
);
