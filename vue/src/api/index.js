import axios from 'axios';

// 设置基础 URL
axios.defaults.baseURL = 'http://localhost:8080'; // 替换为实际的 API 地址

// 添加请求拦截器，自动带上 token
axios.interceptors.request.use(config => {
  const token = localStorage.getItem('authToken');
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`;
  }
  return config;
}, error => {
  return Promise.reject(error);
});

// 定义所有 API 方法

// 登录
export const login = (loginData) => {
  return axios.post('/api/login', loginData);
};

// 获取商品列表
export const getProducts = () => {
  return axios.get('/api/products');
};

// 发布商品
export const publishProduct = (productData) => {
  return axios.post('/api/products', productData);
};

// 获取商品详情
export const getProductDetail = (id) => {
  return axios.get(`/api/products/${id}`);
};

// 更新用户信息（示例）
export const updateUserProfile = (userData) => {
  return axios.put('/api/users/profile', userData);
};
