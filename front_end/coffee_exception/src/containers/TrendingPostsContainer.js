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
    console.log(this.props.posts);
    if (Object.getOwnPropertyNames(this.props.posts).length > 0) {
      for (let i = 0; i < this.props.posts._embedded.blogposts.length; i++) {
        if (i <= 1 || (i > 1 && !threeInARow)) {
          index = i;
          arr.push(
            this.props.posts._embedded.blogposts[i],
            this.props.posts._embedded.blogposts[i + 1]
          );
          i = i + 1;
        } else if (threeInARow) {
          index = i;
          arr.push(
            this.props.posts._embedded.blogposts[i],
            this.props.posts._embedded.blogposts[i + 1],
            this.props.posts._embedded.blogposts[i + 2]
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

  render() {
    return (
      <div className="content-container">
        <Container fluid={true}>
          <Container fluid={true}>
            <Row>
              <CardDeck style={{ justifyContent: 'center' }}>
                {this.renderPostsFromData()}
              </CardDeck>
            </Row>
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
