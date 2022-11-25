import axios from "axios";
import store from "@/store";

export function initU() {
    if (store.state.user.length > 0) {
        return;
    }
    axios.get('http://jsonplaceholder.typicode.com/users').then(resp => {
        // console.log(resp.data)
        store.commit('initUser', resp.data)
    })
}

