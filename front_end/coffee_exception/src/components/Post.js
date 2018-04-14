import React, { Component } from 'react';
import tempavatar from '../assets/tempavatar.jpg';

export class Post extends Component {
  render() {
    return (
      <div className="post-container">
        <div className="post-header-container">
          <h3>{this.props.title}</h3>
          <p>14/04/2018</p>
        </div>
        <div className="post-paragraphs">{this.props.body}</div>
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
              <i className="material-icons">thumb_up</i>
              <p>50</p>
            </div>
            <div className="post-some-icons">
              <i className="material-icons">chat_bubble</i>
              <p>50</p>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
