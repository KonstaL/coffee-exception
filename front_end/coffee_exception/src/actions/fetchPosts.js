export const FETCH_POSTS = 'fetch_posts';

const ROOT_URL = 'http://localhost:8080/api/posts/?projection=inline-user';

export function fetchPosts() {
  const request = fetch(ROOT_URL, {
    method: 'GET',
    headers: new Headers({
      'Content-Type': 'application/json'
    })
  }).then(res => {
    return res.json();
  });

  return {
    type: FETCH_POSTS,
    payload: request
  };
}
