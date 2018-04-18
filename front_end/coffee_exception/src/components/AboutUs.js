import React, { Component } from 'react';
import { Card, Row, Col, Badge, Container } from 'reactstrap';
import { Link } from 'react-router-dom';
import Styled from 'styled-components';

const PostImageContainer = Styled.div`
  overflow:hidden;
  height:${props => props.maxHeight + 'px'};
  width:100%;
  background-image: url("${props => props.img}");
  background-size:cover;
  background-position: center;
  border: 1px solid rgb(230,230,230);
`;

class AboutUs extends Component {
  constructor(props) {
    super(props);
    this.state = {
      paragraphs: this.props.body,
      image: this.props.image
    };
  }

  componentWillMount() {
    if (this.props.trim != null && this.props.trim) {
      let trimmedParagraph = this.state.paragraphs[0].props.children;
      if (trimmedParagraph.length > 250) {
        trimmedParagraph = trimmedParagraph.substr(0, 250) + '...';
      }
      const para = <p>{trimmedParagraph}</p>;
      this.setState({
        paragraphs: para
      });
    }
  }

  render() {
    return (
      <div className="post-container">
        <PostImageContainer
          maxHeight={this.props.height}
          img={this.state.image}
        />
        {/* <div className="post-header-container">
          <h3>{this.props.title}</h3>
          <p>14/04/2018</p>
        </div>
        <div className="post-paragraphs">{this.state.paragraphs}</div>
        <div className="post-footer-container">
          <div className="post-author-profile">
            <div className="profile-image-frame">
              <img src={tempavatar} alt="avatar" />
            </div>
            <div className="profile-author-container">
              <p>Timo McFarlane</p>
              <p className="post-author-subheader">The one and only</p>
            </div>
            <div className="post-some-icons">
              <i className="material-icons">chat_bubble</i>
              <p>50</p>
            </div>
            <div className="post-some-icons">
              <i className="material-icons">local_cafe</i>
              <p>50</p>
            </div>
          </div>
        </div> */}
      </div>
    );
  }
}

export default AboutUs;
