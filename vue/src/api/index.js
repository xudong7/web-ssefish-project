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

// 搜索商品
export const searchProduct = (keyword) => {
  return axios.get(`/api/products/search?keyword=${keyword}`);
}

// 发布商品
export const publishProduct = (product) => {
  return axios.post('/api/products', product);
};

// 获取商品详情
export const getProductDetail = (id) => {
  return axios.get(`/api/products/${id}`);
};

// 更新用户信息（示例）
export const updateUserProfile = (userData) => {
  return axios.put('/api/users/profile', userData);
};

// 上传图片
export const upload = (image) => {
  return axios.post('/api/upload', image);
}

// 删除商品
export const deleteProductById = (id) => {
  return axios.delete(`/api/products/${id}`);
}

// 添加用户
export const addUser = (user) => {
  return axios.post('/api/users', user);
}

// 根据id获取用户
export const getUserById = (id) => {
  return axios.get(`/api/users/${id}`);
}

// 根据id获取卖家
export const getSellerById = (id) => {
  return axios.get(`/api/sellers/${id}`);
}

// 根据id删除user
export const deleteUserById = (id) => {
  return axios.delete(`/api/users/${id}`);
}

// 获取所有用户
export const getUserList = () => {
  return axios.get('/api/users');
}



