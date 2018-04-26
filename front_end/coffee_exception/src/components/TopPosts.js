import React, { Component } from 'react';
import { Row, Col } from 'reactstrap';
import { Post } from '../components/Post';
export class TopPosts extends Component {
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
    return (
      <Row className="post-row-narrow">
        <Col lg="12" xl="8">
          <Post
            id={this.props.data[0].id}
            height={460}
            trim={true}
            trimVal={550}
            pic={this.props.data[0].bannerUrl}
            author={this.props.data[0].user.username}
            title={this.props.data[0].title}
            date={this.props.data[0].date}
            comments={this.props.data[0].comments}
            likes={this.props.data[0].likes}
            body={this.convertStringToHtml(this.props.data[0].bodyItems)}
          />
        </Col>
        <Col lg="12" xl="4">
          <Post
            id={this.props.data[1].id}
            height={160}
            pic={this.props.data[1].bannerUrl}
            trim={true}
            trimVal={100}
            author={this.props.data[1].user.username}
            title={this.props.data[1].title}
            date={this.props.data[1].date}
            comments={this.props.data[1].comments}
            likes={this.props.data[1].likes}
            body={this.convertStringToHtml(this.props.data[1].bodyItems)}
          />
          <Post
            id={this.props.data[2].id}
            height={160}
            pic={this.props.data[2].bannerUrl}
            trim={true}
            trimVal={100}
            author={this.props.data[2].user.username}
            title={this.props.data[2].title}
            date={this.props.data[2].date}
            comments={this.props.data[2].comments}
            likes={this.props.data[2].likes}
            body={this.convertStringToHtml(this.props.data[2].bodyItems)}
          />
        </Col>
      </Row>
    );
  }
}
