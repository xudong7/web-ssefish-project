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
// export const upload = async (image) => {
//   try {
//     // ���� FormData ����
//     const formData = new FormData();
//     formData.append('image', image); // 'image' ��Ӧ��˵Ľ��ղ�����
//
//     // ���� POST ����
//     const response = await axios.post('/api/upload', formData, {
//       headers: {
//         'Content-Type': 'multipart/form-data', // ����ָ��Ϊ multipart/form-data
//       },
//     });
//
//     // ���ؽ��
//     return response.data;
//   } catch (error) {
//     console.error('�ļ��ϴ�ʧ��:', error);
//     throw error;
//   }
// }

