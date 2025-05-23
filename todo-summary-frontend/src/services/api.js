import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8086/api/todo', 
});

export default api;
