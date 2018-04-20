export const FETCH_TRENDING_POSTS = 'fetch_trending_posts';

const ROOT_URL = 'http://localhost:8080/api/posts/?projection=inline-user';

export function fetchTrendingPosts() {
  const request = fetch(ROOT_URL, {
    method: 'GET',
    headers: new Headers({
      'Content-Type': 'application/json'
    })
  }).then(res => {
    return res.json();
  });

  return {
    type: FETCH_TRENDING_POSTS,
    payload: request
  };
}
