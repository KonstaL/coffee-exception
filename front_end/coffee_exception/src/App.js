import React, { Component } from 'react';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import { BrowserRouter, Route, Switch } from "react-router-dom";
import promise from 'redux-promise';

import reducers from './reducers';
import PostsContainer from './containers/PostsContainer';
import { Container, Row, Col } from 'reactstrap';

const createStoreWithMiddleware = applyMiddleware(promise)(createStore);

class App extends Component {
  render() {
    return (
        <Provider store={createStoreWithMiddleware(reducers)}>
            <BrowserRouter>
                <Container>
                    <Row>
                        <Col></Col>
                    </Row>
                    <Row>
                        <Col>
                            <Switch>
                                <Route path="/" component={PostsContainer} />
                            </Switch>
                        </Col>
                    </Row>
                    <Row>
                        <Col></Col>
                    </Row>

                </Container>
            </BrowserRouter>
        </Provider>
    );
  }
}

export default App;
