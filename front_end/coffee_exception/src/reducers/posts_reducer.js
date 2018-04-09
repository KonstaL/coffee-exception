import { FETCH_POSTS } from '../actions/fetchPosts';
import { FETCH_TRENDING_POSTS } from '../actions/fetchTrendingPosts';

export default function(state = [], action) {
  switch (action.type) {
    case FETCH_POSTS:
      return action.payload;
    case FETCH_TRENDING_POSTS:
      return action.payload;
    default:
      return state;
  }
}
