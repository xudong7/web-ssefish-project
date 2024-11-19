import {createRouter, createWebHistory} from 'vue-router';
import HomePage from '../views/HomePage.vue';
import LoginPage from '../views/LoginPage.vue';
import PublishProduct from '../views/PublishProduct.vue';
import UserProfile from '../views/UserProfile.vue';


const routes = [
    {path: '/', name: 'Home', component: HomePage},
    {path: '/login', name: 'Login', component: LoginPage},
    {path: '/publish', name: 'PublishProduct', component: PublishProduct},
    {path: '/profile', name: 'UserProfile', component: UserProfile},

];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;
