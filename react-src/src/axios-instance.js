import axios from 'axios';

let instance = null;

if (!process.env.NODE_ENV || process.env.NODE_ENV === 'development') {
  instance = axios.create({
    withCredentials: true,
    baseURL: '/api'
  });
} else {
  const CSRF_TOKEN = document.cookie.match(new RegExp('XSRF-TOKEN=([^;]+)'))[1];

  instance = axios.create({
    withCredentials: true,
    baseURL: 'https://dickens-blog-app.herokuapp.com/api',
    headers: { 
      'X-XSRF-TOKEN': CSRF_TOKEN,
      'Access-Control-Allow-Methods': 'PATCH, DELETE, POST, GET, OPTIONS, PUT'
    }
  });
}

export default instance;