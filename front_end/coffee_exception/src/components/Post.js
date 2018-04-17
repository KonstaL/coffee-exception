import React, { Component } from 'react';
import tempavatar from '../assets/tempavatar.jpg';
import pic1 from '../assets/pic1.jpg';
import pic2 from '../assets/pic2.jpg';
import pic3 from '../assets/pic3.jpg';
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

export class Post extends Component {
  constructor(props) {
    super(props);
    this.state = {
      paragraphs: this.props.body,
      image: ''
    };

    this.getImage = this.getImage.bind(this);
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
    this.getImage();
  }

  getImage() {
    if (this.props.pic === 0) {
      this.setState({
        image: pic1
      });
    } else if (this.props.pic === 1) {
      this.setState({
        image: pic2
      });
    } else if (this.props.pic === 2) {
      this.setState({
        image: pic3
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
        <div className="post-header-container">
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
        </div>
      </div>
    );
  }
}
