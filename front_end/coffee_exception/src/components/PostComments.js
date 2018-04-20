import React, { Component } from 'react';
import tempavatar from '../assets/tempavatar.jpg';
import _ from 'lodash';

export class PostComments extends Component {
  renderCommentsFromProps() {
    return this.props.comments.map(comment => {
      return (
        <li className="comment-container">
          <div className="comment">
            <div className="comment-user-avatar-container">
              <div className="profile-image-frame">
                <img src={tempavatar} alt="avatar" />
              </div>
            </div>
            <div className="comment-content">
              <div className="comment-author">Comment Author placeholder</div>
              <p>{comment.text}</p>
            </div>
          </div>
          <div className="comment-footer">
            <div>
              <i className="material-icons">keyboard_arrow_up</i>
              <p>{comment.likes}</p>
            </div>
            <div>
              <i className="material-icons">keyboard_arrow_down</i>
              <p>{comment.likes}</p>
            </div>
          </div>
          <hr />
        </li>
      );
    });
  }

  render() {
    return (
      <div className="comments-container">
        <div className="comments-header">
          <h4>Comments</h4>
          <hr />
        </div>
        <div className="comments">
          <ul>{this.renderCommentsFromProps()}</ul>
        </div>
      </div>
    );
  }
}
