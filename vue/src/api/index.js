import axios from 'axios';

// backend API ��ַ
axios.defaults.baseURL = 'http://localhost:8080';

// ����������������Զ����� token
axios.interceptors.request.use(config => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
}, (error) => {
  return Promise.reject(error);
});

// ��¼
export const login = (loginData) => {
  return axios.post('/login', loginData);
};

// �ϴ�ͼƬ
export const upload = (image) => {
  return axios.post('/upload', image);
}


// ��ƷAPI
// ��ȡ������Ʒ
export const getProductList = () => {
  return axios.get('/products');
}
export const getPublishedProductBySellerId = (sellerId) => {
    return axios.get(`/products/profile/${sellerId}`);
}
// ������Ʒ
export const searchProduct = (keyword) => {
  return axios.get(`/products/search?keyword=${keyword}`);
}
// ������Ʒ
export const publishProduct = (product) => {
  return axios.post('/products', product);
};
// ��ȡ��Ʒ����
export const getProductDetail = (id) => {
  return axios.get(`/products/${id}`);
};
// ɾ����Ʒ
export const deleteProductById = (id) => {
  return axios.delete(`/products/${id}`);
}


// �û�API
// ����û�
export const addUser = (user) => {
  return axios.post('/users', user);
}
// ����id��ȡ�û�
export const getUserById = (id) => {
  return axios.get(`/users/${id}`);
}
// ����id��ȡ����
export const getSellerById = (id) => {
  return axios.get(`/sellers/${id}`);
}
// ����idɾ��user
export const deleteUserById = (id) => {
  return axios.delete(`/users/${id}`);
}
// ��ȡ�����û�
export const getUserList = () => {
  return axios.get('/users');
}



