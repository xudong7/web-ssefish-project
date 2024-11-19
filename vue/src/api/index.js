import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080'; // backend API ��ַ

// ����������������Զ����� token
axios.interceptors.request.use(config => {
  const token = localStorage.getItem('authToken');
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`;
  }
  return config;
}, error => {
  return Promise.reject(error);
});

// ��¼
export const login = (loginData) => {
  return axios.post('/api/login', loginData);
};

// ��ȡ������Ʒ
export const getProductList = () => {
  return axios.get('/api/products');
}

export const searchProduct = (keyword) => {
  return axios.get(`/api/products/search?keyword=${keyword}`);
}

// ������Ʒ
export const publishProduct = (productData) => {
  return axios.post('/api/products', productData);
};

// ��ȡ��Ʒ����
export const getProductDetail = (id) => {
  return axios.get(`/api/products/${id}`);
};

// �����û���Ϣ��ʾ����
export const updateUserProfile = (userData) => {
  return axios.put('/api/users/profile', userData);
};
