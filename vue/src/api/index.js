import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080'; // backend API 地址

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

// 登录
export const login = (loginData) => {
  return axios.post('/api/login', loginData);
};

// 获取所有商品
export const getProductList = () => {
  return axios.get('/api/products');
}

export const searchProduct = (keyword) => {
  return axios.get(`/api/products/search?keyword=${keyword}`);
}

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
