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

// ������Ʒ
export const searchProduct = (keyword) => {
  return axios.get(`/api/products/search?keyword=${keyword}`);
}

// ������Ʒ
export const publishProduct = (product) => {
  return axios.post('/api/products', product);
};

// ��ȡ��Ʒ����
export const getProductDetail = (id) => {
  return axios.get(`/api/products/${id}`);
};

// �����û���Ϣ��ʾ����
export const updateUserProfile = (userData) => {
  return axios.put('/api/users/profile', userData);
};

// �ϴ�ͼƬ
export const upload = (image) => {
  return axios.post('/api/upload', image);
}

// ɾ����Ʒ
export const deleteProductById = (id) => {
  return axios.delete(`/api/products/${id}`);
}

// ����û�
export const addUser = (user) => {
  return axios.post('/api/users', user);
}

// ����id��ȡ�û�
export const getUserById = (id) => {
  return axios.get(`/api/users/${id}`);
}

// ����id��ȡ����
export const getSellerById = (id) => {
  return axios.get(`/api/sellers/${id}`);
}

// ����idɾ��user
export const deleteUserById = (id) => {
  return axios.delete(`/api/users/${id}`);
}

// ��ȡ�����û�
export const getUserList = () => {
  return axios.get('/api/users');
}



