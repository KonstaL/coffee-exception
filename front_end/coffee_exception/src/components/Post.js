import React, { Component } from 'react';
import tempavatar from '../assets/tempavatar.jpg';
import pic1 from '../assets/pic1.jpg';
import pic2 from '../assets/pic2.jpg';
import pic3 from '../assets/pic3.jpg';
import Styled from 'styled-components';
import { Link } from 'react-router-dom';

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
    if (
      this.props.trim != null &&
      this.props.trim &&
      this.props.trimVal != null
    ) {
      let trimmedParagraph = this.state.paragraphs[0].props.children;
      if (trimmedParagraph.length > this.props.trimVal) {
        trimmedParagraph =
          trimmedParagraph.substr(0, this.props.trimVal) + '...';
      }
      const para = <p>{trimmedParagraph}</p>;
      this.setState({
        paragraphs: para
      });
    }
    this.getImage();
  }

  getImage() {
    this.setState({
      image: this.props.pic
    });
  }

  createDate() {
    return `${this.props.date.dayOfMonth}/${this.props.date.monthValue}/${
      this.props.date.year
    }`;
  }

  render() {
    return (
      <div className="post-container">
        <Link to={{ pathname: '/posts/' + this.props.id }}>
          <PostImageContainer
            maxHeight={this.props.height}
            img={this.state.image}
          />
        </Link>
        <div className="post-header-container">
          <h3>{this.props.title}</h3>
          <p>{this.createDate()}</p>
        </div>
        <div className="post-paragraphs">{this.state.paragraphs}</div>
        <div className="post-footer-container">
          <div className="post-author-profile">
            <div className="profile-image-frame">
              <img src={tempavatar} alt="avatar" />
            </div>
            <div className="profile-author-container">
              <p>{this.props.author}</p>
              <p className="post-author-subheader">The one and only</p>
            </div>
            <div className="post-some-icons">
              <i className="material-icons">chat_bubble</i>
              <p>{this.props.comments.length}</p>
            </div>
            <div className="post-some-icons">
              <i className="material-icons">local_cafe</i>
              <p>{this.props.likes}</p>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
