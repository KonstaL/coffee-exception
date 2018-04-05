import React, { Component } from 'react';
import { fetchPosts } from "../actions/fetchPosts";
import { connect } from 'react-redux';

class PostsContainer extends Component {
    componentDidMount() {
        this.props.fetchPosts();
    }

    renderListFromData() {
        return this.props.posts.map(post => {
            return (
                <div>
                    <span>{post.title}</span>
                    <span>{post.author.userName}</span>
                </div>
            );
        })
    }

    render() {
        return (
            <div>
                <ul>
                    {this.renderListFromData()}
                </ul>
            </div>
        );
    }
}

function mapStateToProps(state) {
    return { posts: state.posts };
}

export default connect(mapStateToProps, { fetchPosts })(PostsContainer);