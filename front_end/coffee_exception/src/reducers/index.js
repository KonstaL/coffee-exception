import { combineReducers } from 'redux';
import PostsReducer from './posts_reducer';
import PostReducer from './post_reducer';
import LoginReducer from './login_reducer';

const rootReducer = combineReducers({
  posts: PostsReducer,
  post: PostReducer,
  login: LoginReducer
});

export default rootReducer;
