import Vue from 'vue'
import VueRouter from 'vue-router';


Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: () => import('../views/Home.vue')
    },
    {
        path: '/Goods',
        name: 'Goods',
        component: () => import('../views/Goods.vue')
    },
    {
        path: "/Order",
        name: "Order",
        component: () => import ("../views/Order.vue")
    },
    {
        path: "/PreOrder",
        name: "PreOrder",
        component: () => import ("../views/PreOrder.vue")
    },
    {
        path: "/Collection",
        name: "Collection",
        component: () => import ("../views/Collection.vue")
    },
    {
        path: "/ShoppingCart",
        name: "ShoppingCart",
        component: () => import ("../views/ShoppingCart.vue")
    }
]

const router = new VueRouter({
    routes
})

export default router
