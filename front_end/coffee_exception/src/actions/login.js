export const LOGIN = 'login';

const ROOT_URL = 'http://localhost:8080/authenticateTheUser/';

export function login(data) {
  const request = fetch(ROOT_URL, {
    method: 'POST',
    headers: new Headers({
      'Content-Type': 'application/json'
    }),
    body: JSON.stringify(data)
  })
    .then(res => {
      return res;
    })
    .catch(err => console.log(err));

  return {
    type: LOGIN,
    payload: request
  };
}
