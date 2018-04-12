import React, { Component } from 'react';

export class Post extends Component {
  render() {
    return (
      <div className="post-container">
        <h3>{this.props.title}</h3>
        <p>{this.props.date}</p>
        <div className="post-paragraphs">{this.props.body}</div>
        <div className="post-footer-container">
          <div className="post-author-profile">
            <div className="profile-image-frame" />
            <h5>Haloo</h5>
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
