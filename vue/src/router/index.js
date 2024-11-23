import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../views/HomePage.vue';
import LoginPage from '../views/LoginPage.vue';
import PublishProduct from '../views/PublishProduct.vue';
import UserProfile from '../views/UserProfile.vue';
import ProductDetail from '../views/ProductDetail.vue';
import Admin from '@/views/AdminPage.vue';
import RegisterPage from '@/views/RegisterPage.vue';
import ChatPage from '@/views/ChatPage.vue';

const routes = [
    { path: '/', name: 'Login', component: LoginPage },
    { path: '/home', name: 'Home', component: HomePage }, // 新增 home 路由
    { path: '/products/:id', name: 'ProductDetail', component: ProductDetail },
    { path: '/publish', name: 'PublishProduct', component: PublishProduct },
    { path: '/profile', name: 'UserProfile', component: UserProfile },
    { path: '/register', name: 'Register', component: RegisterPage},
    { path: '/admin', name: 'Admin', component: Admin },
    { path: '/chat', name: 'Chat', component: ChatPage }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

// 路由守卫：根据角色重定向
router.beforeEach((to, from, next) => {
    const role = localStorage.getItem('userRole'); // 获取角色
    const token = localStorage.getItem('token');

    // 如果没有 token，跳转到登录页面
    if (!token && to.name !== 'Login') {
        return next({ name: 'Login' });
    }

    // 如果用户是普通用户，禁止访问管理员页面
    if (to.path.startsWith('/admin') && role !== 'admin') {
        return next('/home'); // 重定向到用户主页
    }

    // 如果用户是管理员，禁止访问普通用户页面
    if (to.path.startsWith('/home') && role === 'admin') {
        return next('/admin'); // 重定向到管理员主页
    }

    // 页面刷新后，保存当前的路由路径
    if (to.name) {
        localStorage.setItem('currentRoute', to.fullPath);
    }

    next(); // 允许访问
});

// 页面刷新后恢复上次访问的路由
router.isReady().then(() => {
    const savedRoute = localStorage.getItem('currentRoute');
    if (savedRoute) router.push(savedRoute); // 恢复上次的路由
});

export default router;
