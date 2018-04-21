import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import { Row, Col, Container, Button } from 'reactstrap';
import Styled from 'styled-components';
import Markdown from 'markdown-to-jsx';
import hljs from 'highlight.js';
import _ from 'lodash';

const Editor = Styled.textarea`
  outline: none;
  resize: none;
  width: 100%;
  height: ${props => (props.height != undefined ? props.height + 'px' : 400)};
  min-height: 400px;
  overflow: hidden;
  padding: 5px
  border: none;
`;

const Input = Styled.input`
  outline: none;
  width: 100%;
  padding: 5px
  margin: 0px 0px 15px 0px;
  border: none;
  border-bottom: 1px solid gray;
`;

const H1 = Styled.h1`
text-align: center;
padding: 25px;
padding-bottom: 35px;
`;

const H3 = Styled.h3`
color: #030303;
text-align: center;
margin-bottom: 30px;
  &:after {
    background: none repeat scroll 0 0 gray;
    bottom: -2px;
    left: 37%;
    content: "";
    display: block;
    height: 1px;
    position: relative;
    width: 130px;
`;

export default class AddPostContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      title: '',
      previewText: '',
      editorText: '',
      editorHeight: 400
    };

    this.codeBlocks = new Array();
  }

  componentDidUpdate() {
    let blocks = document.querySelectorAll('code');

    //TODO: make something more faster & robust

    //to get more reliable results, remove if
    // However, this adds a class to the <code> block every time the user writes something, making
    // the app really slow after a while
    if (blocks != undefined) {
      blocks.forEach(block => {
        if (_.includes(this.codeBlocks, block)) {
          hljs.highlightBlock(block);
        } else {
          console.log('ignoorataan!');
        }
      });

      this.codeBlocks = blocks;
    }
  }

  handleChange(e) {
    switch (e.target.name) {
      case 'preview-text-input':
        this.setState({ previewText: e.target.value });
        break;
      case 'title-input':
        this.setState({ title: e.target.value });
        break;
      case 'editor-input':
        this.setState({
          editorText: e.target.value,
          editorHeight: +e.target.scrollHeight
        });
        break;
    }
  }

  /*
    Hei Timo, tee tää redux tyylisesti, en ite just nyt jaksa opetella
    t. Lauantain Konsta
  */
  postBlogpost() {
    fetch('http://localhost:8080/posts/', {
      method: 'POST',
      headers: new Headers({ 'content-type': 'application/json' }),
      body: JSON.stringify({
        title: this.state.title,
        previewText: this.state.previewText,
        bodyText: this.state.editorText
      })
    })
      .then(res => res.json())
      .then(res => console.log(res))
      .catch(err => console.log('error: ', err));

    // React router redirect
    //this.props.router.push('/');
  }

  //
  // Sori näistä inline tyyleistä timo
  //

  render() {
    return (
      <Container fluid={true}>
        <Row>
          <Col xl={12}>
            <H1>Add a post </H1>
          </Col>
        </Row>
        <Row style={{ 'padding-bottom': '100px' }}>
          <Col lg={6}>
            <H3>Edit</H3>

            <Input
              type="text"
              name="title-input"
              placeholder="Your Title"
              onChange={this.handleChange.bind(this)}
            />

            <Input
              type="text"
              name="preview-text-input"
              placeholder="preview text"
              onChange={this.handleChange.bind(this)}
            />

            <Editor
              name="editor-input"
              placeholder="type the post here"
              height={this.state.editorHeight}
              onChange={this.handleChange.bind(this)}
            />
          </Col>

          <Col lg={6} style={{ 'border-left': '1px solid lightgray' }}>
            <H3>Preview</H3>

            <h1>{this.state.title}</h1>
            <h3>{this.state.previewText}</h3>

            <Markdown>{this.state.editorText}</Markdown>
          </Col>
        </Row>
        <Row>
          <Col
            xl={12}
            style={{
              'text-align': 'center',
              'padding-top': '50px',
              'border-top': '1px solid lightgray'
            }}
          >
            <Button color="primary" onClick={this.postBlogpost.bind(this)}>
              Post it!
            </Button>
          </Col>
        </Row>
        <Row>
          <Col
            xl={12}
            style={{
              'text-align': 'center',
              padding: '20px'
            }}
          >
            <a href="https://guides.github.com/features/mastering-markdown/">
              How do I format my post?
            </a>
          </Col>
        </Row>
      </Container>
    );
  }
}
