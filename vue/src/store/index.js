import { createStore } from "vuex";
import { login, register } from "@/api";

export default createStore({
  state: {
    user: null,
    token: null,
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
    },
    setToken(state, token) {
      state.token = token;
    },
  },
  actions: {
    async login({ commit }, payload) {
      const response = await login(payload);
      commit("setUser", response.data.user);
      commit("setToken", response.data.token);
    },
    async register(_, payload) {
      await register(payload);
    },
  },
});
