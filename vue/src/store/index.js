import { createStore } from "vuex";

export default createStore({
    state: {
        token: null, // 用户令牌
        user: null,  // 用户信息
        userRole: null, // 用户角色
    },
    mutations: {
        // 设置 token
        setToken(state, token) {
            state.token = token;
        },
        // 设置用户信息
        setUser(state, user) {
            state.user = user;
        },
        // 设置用户角色
        setUserRole(state, role) {
            state.userRole = role;
        },
        // 清除所有用户信息
        clearUserData(state) {
            state.token = null;
            state.user = null;
            state.userRole = null;
        },
    },
    getters: {
        // 获取是否已登录
        isLoggedIn: state => !!state.token,
        // 获取用户信息
        getUser: state => state.user,
        // 获取token
        getToken: state => state.token,
        // 获取用户角色
        getUserRole: state => state.userRole,
    },
    actions: {
        // 模拟登录操作
        async login({ commit }, { token, user }) {
            // 模拟登录成功后的操作
            commit("setToken", token);
            commit("setUser", user);
        },
        // 模拟登出操作
        logout({ commit }) {
            commit("clearUserData");
        },
    },
    modules: {}
});
