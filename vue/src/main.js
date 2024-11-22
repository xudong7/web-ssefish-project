import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import axios from 'axios';

// 在 axios 请求中添加 token
// this.$axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('authToken')}`;

const app = createApp(App);

app.config.globalProperties.$axios = axios;
app.use(router);
app.use(ElementPlus);

app.mount('#app');
