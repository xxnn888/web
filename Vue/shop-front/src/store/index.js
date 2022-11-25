import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
const store = new Vuex.Store({
    state: {
        showLogin: false,     // 用于控制是否显示登录组件
        showRegister: false,  // 用于控制是否显示注册组件
        carts: [],
        type: 0
    },

    mutations: {
        setCarts(state, carts) {
            state.carts = carts
            console.log(state.carts, "ss")
        },
        setType(state, type) {
            state.type = type
        },
        setShowLogin(state, data) {
            state.showLogin = data;
        },
        setShowRegister(state, data) {
            state.showRegister = data;
        }
    },

})
export default store;