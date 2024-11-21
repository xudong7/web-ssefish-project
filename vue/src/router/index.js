import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../views/HomePage.vue';
import LoginPage from '../views/LoginPage.vue';
import PublishProduct from '../views/PublishProduct.vue';
import UserProfile from '../views/UserProfile.vue';
import ProductDetail from '../views/ProductDetail.vue';

const routes = [
    { path: '/', name: 'Login', component: LoginPage },
    { path: '/home', name: 'Home', component: HomePage }, // 新增 home 路由
    {path: '/products/:id', name: 'ProductDetail', component: ProductDetail},
    { path: '/publish', name: 'PublishProduct', component: PublishProduct },
    { path: '/profile', name: 'UserProfile', component: UserProfile },

];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

// Store the current route before each navigation
router.beforeEach((to, from, next) => {
    localStorage.setItem('currentRoute', to.fullPath);  // Store the current route path
    next();
});

// Restore the current route from localStorage after page reload
router.isReady().then(() => {
    const savedRoute = localStorage.getItem('currentRoute');
    if (savedRoute) {
        router.push(savedRoute);  // Redirect to the saved route
    }
});

export default router;