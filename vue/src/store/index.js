import {createStore} from "vuex";

export default createStore({
    state: {
        token: localStorage.getItem("token"),
        user: JSON.parse(localStorage.getItem("user")),
        userRole: localStorage.getItem("userRole")
    },
    mutations: {
        // set token
        setToken(state, token) {
            state.token = token;
            localStorage.setItem("token", token);
        },
        // set user
        setUser(state, user) {
            state.user = user;
            localStorage.setItem("user", JSON.stringify(user));
        },
        // remove all data
        removeData(state) {
            state.token = '';
            state.user = {};
            localStorage.setItem("token", '');
            localStorage.setItem("user", JSON.stringify({}));
        }
    },
    getters: {
        // get token
        getToken(state) {
            return state.token;
        },
        // get user
        getUser(state) {
            return state.user;
        },
        // check if user is authenticated
        isAuthenticated(state) {
            return state.token && state.user;
        }
    },
    actions: {

    },
    modules: {}
});
