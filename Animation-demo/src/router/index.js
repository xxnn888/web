import Vue from 'vue'
import VueRouter from 'vue-router';


Vue.use(VueRouter)
//解决vue路由重复导航错误
//获取原型对象上的push函数
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
const routes = [
    {
        path: '/',
        name: 'Home',//    name属性简化路由跳转
        component: () => import("../view/Home")
    },
    {
        path: "/Test",
        component: () => import("../view/Test"),
        children: [
            {
                path: "LoginReg",
                name: "LoginReg",
                component: () => import("../view/pages/LoginReg")
            },
            {
                path: "PersonMessage",
                name: "PersonMessage",
                component: () => import("../view/pages/PersonMessage")
            },
            {
                path: "Edit",
                name: "Edit",
                component: () => import("../view/pages/Edit")
            },
            {
                path: "Info",
                component: () => import("../view/pages/Info")
            },
            {
                path: "Info1",
                component: () => import("../view/pages/Info1")
            },
            {
                path: "Info2",
                component: () => import("../view/pages/Info2")
            },
            {
                path: "Info3",
                component: () => import("../view/pages/Info3")
            },
        ]
    }
]

const router = new VueRouter({
    routes
})

export default router
