import axios from 'axios';

axios.defaults.xsrfCookieName = 'csrftoken';
axios.defaults.xsrfHeaderName = 'X-CSRFToken';
axios.defaults.headers.post['Access-Control-Allow-Methods'] = 'PATCH, DELETE, POST, GET, OPTIONS, PUT';

let instance = null;

if (!process.env.NODE_ENV || process.env.NODE_ENV === 'development') {
  instance = axios.create({
    baseURL: 'http://localhost:8080/api'
  });
} else {
  const CSRF_TOKEN = document.cookie.match(new RegExp('XSRF-TOKEN=([^;]+)'))[1];

  instance = axios.create({
    withCredentials: 'include',
    baseURL: 'https://dickens-blog-app.herokuapp.com/api',
    headers: { 
      'X-XSRF-TOKEN': CSRF_TOKEN,
      'Access-Control-Allow-Methods': 'PATCH, DELETE, POST, GET, OPTIONS, PUT'
    }
  });
}

// instance = axios.create({
//   baseURL: 'http://localhost:8080/api'
// });


export default instance;