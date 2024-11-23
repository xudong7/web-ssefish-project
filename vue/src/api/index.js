import axios from 'axios';

// backend API 地址
axios.defaults.baseURL = 'http://localhost:8080';

// 添加请求拦截器，自动带上 token
axios.interceptors.request.use(config => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
}, (error) => {
  return Promise.reject(error);
});

// 登录
export const login = (loginData) => {
  return axios.post('/login', loginData);
};

// 上传图片
export const upload = (image) => {
  return axios.post('/upload', image);
}


// 商品API
// 获取所有商品
export const getProductList = () => {
  return axios.get('/products');
}
export const getPublishedProductBySellerId = (sellerId) => {
    return axios.get(`/products/profile/${sellerId}`);
}
// 搜索商品
export const searchProduct = (keyword) => {
  return axios.get(`/products/search?keyword=${keyword}`);
}
// 发布商品
export const publishProduct = (product) => {
  return axios.post('/products', product);
};
// 获取商品详情
export const getProductDetail = (id) => {
  return axios.get(`/products/${id}`);
};
// 删除商品
export const deleteProductById = (id) => {
  return axios.delete(`/products/${id}`);
}


// 用户API
// 添加用户
export const addUser = (user) => {
  return axios.post('/users', user);
}
// 根据id获取用户
export const getUserById = (id) => {
  return axios.get(`/users/${id}`);
}
// 根据id获取卖家
export const getSellerById = (id) => {
  return axios.get(`/sellers/${id}`);
}
// 根据id删除user
export const deleteUserById = (id) => {
  return axios.delete(`/users/${id}`);
}
// 获取所有用户
export const getUserList = () => {
  return axios.get('/users');
}



