export const FETCH_POST = 'fetch_post';

const ROOT_URL = 'http://localhost:8080/api/posts/';

export function fetchPost(id) {
  const request = fetch(ROOT_URL + id, {
    method: 'GET',
    headers: new Headers({
      'Content-Type': 'application/json'
    })
  }).then(res => {
    return res.json();
  });

  return {
    type: FETCH_POST,
    payload: request
  };
}
