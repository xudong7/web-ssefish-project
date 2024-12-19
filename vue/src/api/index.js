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
// ��������id��ȡ��Ʒ
export const getPublishedProductBySellerId = (sellerId) => {
    return axios.get(`/products/profile/${sellerId}`);
}
// ����wantList��ȡ��Ʒ
export const getWantListProduct = (userId) => {
    return axios.get(`/products/wantList/${userId}`);
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
//���۸����Ʒ����͵���
export const getProductsListByPriceFromL = () => {
    return axios.get('/products/Lower');
}
//���۸����Ʒ����ߵ���
export const getProductsListByPriceFromH = () => {
    return axios.get('/products/Higher');
}
//��ʱ��չʾ��Ʒ
export const getProductsListByTime = () => {
    return axios.get('/products/ByTime');
}
export const getProductsListByTimeWeek = () => {
    return axios.get('/products/ByTimeWeek');
}
export const getProductsListByTimeMonth = () => {
    return axios.get('/products/ByTimeMonth');
}
// ������Ʒ
export const updateProduct = (product) => {
    return axios.put(`/products`, product);
};


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
// ����Ʒ���빺�ﳵ
export const toggleProductWantList = (userId, productId) => {
    return axios.put('/users/wantList', null, {
        params: {
            userId: userId,  // Ensure userId is passed correctly
            productId: productId  // Ensure productId is passed correctly
        }
    });
}
// ��ȡwantList
export const getWantList = (userId) => {
    return axios.get(`/users/wantList/${userId}`);
}
// �����û���Ϣ
export const updateUser = (user) => {
    return axios.put('/users', user);
}


// trade api
// ��ȡ���н���
export const getTradeList = () => {
    return axios.get('/trades');
}
// ����productId��ȡ����
export const getTradeByProductId = (productId) => {
    return axios.get(`/trades/${productId}`);
}
// ����productId���½���
export const updateTradeByProductId = (productId) => {
    return axios.put(`/trades/${productId}`);
}

// announcement api
// ��ȡ���й���
export const getAnnouncementList = () => {
    return axios.get('/announcements');
}

// ����id��ȡ����
export const getAnnouncementById = (id) => {
    return axios.get(`/announcements/${id}`);
}

// ��ӹ���
export const addAnnouncement = (announcement) => {
    return axios.post('/announcements', announcement);
}

