import React, { Component } from 'react';
import { fetchTrendingPosts } from '../actions/fetchTrendingPosts';
import { connect } from 'react-redux';
import { Row, CardDeck, Container } from 'reactstrap';
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

    if (Object.getOwnPropertyNames(this.props.posts).length > 0) {
      for (let i = 0; i < this.props.posts._embedded.blogposts.length; i++) {
        arr.push(this.props.posts._embedded.blogposts[i]);
        if (i === this.props.posts._embedded.blogposts.length - 1) {
          rows.push(<PostRow index={i} key={rows.length} posts={arr} />);
          arr = [];
        } else if (!threeInARow && arr.length === 2) {
          rows.push(<PostRow index={i} key={rows.length} posts={arr} />);
          arr = [];
          threeInARow = !threeInARow;
        } else if (threeInARow && arr.length === 3) {
          rows.push(<PostRow index={i} key={rows.length} posts={arr} />);
          arr = [];
          threeInARow = !threeInARow;
        }
      }
    }
    return rows;
  }

  render() {
    return (
      <div className="content-container">
        <Container fluid={true}>
          <Container fluid={true}>
            <Row>{this.renderPostsFromData()}</Row>
          </Container>
        </Container>
      </div>
    );
  }
}

function mapStateToProps(state) {
  return { posts: state.posts };
}

export default connect(mapStateToProps, { fetchTrendingPosts })(
  TrendingPostsContainer
);
