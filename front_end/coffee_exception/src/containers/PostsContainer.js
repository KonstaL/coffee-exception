import React, { Component } from 'react';
import { fetchPosts } from "../actions/fetchPosts";
import { connect } from 'react-redux';
import { Card, Row, Col } from 'reactstrap';

class PostsContainer extends Component {
    componentDidMount() {
        this.props.fetchPosts();
    }

    convertStringToHtml(items) {
        let parser = new DOMParser();
        let elements = items.map(item => {
            let doc = parser.parseFromString(item, "text/html");
            return [...doc.body.childNodes]
        })

        return elements.map((elem, index) => {
            return <p key={index}>{elem[0].innerHTML}</p>
        })
    }

    renderListFromData() {
        return this.props.posts.map(post => {
            return (
                <div>
                    <Post
                        key={post.id}
                        title={post.title}
                        author={post.author.userName}
                        date={post.date}
                        bodyItems={post.bodyItems}
                    />
                    <Card style={{padding:20 + 'px', margin:10 + 'px'}} key={post.id}>
                        <Row style={{marginBottom: 10 + 'px'}}>
                            <Col md="9">
                                <h3>{post.title}</h3>
                            </Col>
                            <Col md="3">
                                <Row>
                                    {post.author.userName}{post.date}
                                </Row>
                            </Col>
                        </Row>
                        <Row>
                            <Col md="12">
                                <div>
                                    {this.convertStringToHtml(post.bodyItems)}
                                </div>
                            </Col>
                        </Row>
                    </Card>
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