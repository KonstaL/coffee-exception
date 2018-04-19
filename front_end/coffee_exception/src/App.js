import React, { Component } from 'react';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import promise from 'redux-promise';

import reducers from './reducers';
import PostContainer from './containers/PostContainer';
import PostsContainer from './containers/PostsContainer';
import { Container, Row, Col } from 'reactstrap';
import LoginContainer from './containers/LoginContainer';
import TrendingPostsContainer from './containers/TrendingPostsContainer';
import Header from './components/Header';
import Footer from './components/Footer';
import AboutUsContainer from './containers/AboutUsContainer';

const createStoreWithMiddleware = applyMiddleware(promise)(createStore);

class App extends Component {
  render() {
    return (
      <Provider store={createStoreWithMiddleware(reducers)}>
        <BrowserRouter>
          <Container fluid={true}>
            <Row>
              <Col>
                <Header color={'rgb(45, 66, 82)'} />
              </Col>
            </Row>
            <Row>
              <Switch>
                <Route exact path="/" component={TrendingPostsContainer} />
                <Route path="/about" component={AboutUsContainer} />
                <Route path="/login" component={LoginContainer} />
                <Route
                  path="/posts/:id"
                  render={props => <PostContainer id={props.match.params.id} />}
                />
                <Route path="/posts" component={PostsContainer} />
                <Route path="/" render={() => <h1>404</h1>} />
              </Switch>
            </Row>
            <Row>
              <Col>
                <Footer color={'#f3f3f3'} />
              </Col>
            </Row>
          </Container>
        </BrowserRouter>
      </Provider>
    );
  }
}

export default App;
