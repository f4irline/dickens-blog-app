import axios from 'axios';

let instance = null;

if (!process.env.NODE_ENV || process.env.NODE_ENV === 'development') {
  instance = axios.create({
    withCredentials: true,
    baseURL: '/api'
  });
} else {
  // Remove comments from CSRT_TOKEN variable and the X-XSRF-TOKEN header for production.
  // Also replace the 'baseURL' value with the correct value for the server backend (e.g. Heroku).

  const CSRF_TOKEN = document.cookie.match(new RegExp('XSRF-TOKEN=([^;]+)'))[1];

  instance = axios.create({
    withCredentials: true,
    //baseURL: 'http://localhost:8080/api',
    baseURL: 'https://dickens-blog-app.herokuapp.com/api',
    headers: { 
      'X-XSRF-TOKEN': CSRF_TOKEN,
      'Access-Control-Allow-Methods': 'PATCH, DELETE, POST, GET, OPTIONS, PUT'
    }
  });
}

export default instance;