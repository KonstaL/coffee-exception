import React, { Component } from 'react';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import promise from 'redux-promise';

import reducers from './reducers';
import PostsContainer from './containers/PostsContainer';
import { Container, Row, Col } from 'reactstrap';
import Header from './components/Header';
import Footer from './components/Footer';

const createStoreWithMiddleware = applyMiddleware(promise)(createStore);

class App extends Component {
  render() {
    return (
      <Provider store={createStoreWithMiddleware(reducers)}>
        <BrowserRouter>
          <Container fluid={true}>
            <Row>
              <Col>
                <Header color={'#c3c3c3'} />
              </Col>
            </Row>
            <Row>
              <Col>
                <Switch>
                  <Route exact path="/" component={PostsContainer} />
                  <Route path="/about" render={() => <h1>about</h1>} />
                  <Route path="/login" render={() => <h1>login</h1>} />
                  <Route path="/posts" render={() => <h1>posts</h1>} />
                  <Route path="/" render={() => <h1>404</h1>} />
                </Switch>
              </Col>
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
