import {
    createRouter,
    createWebHashHistory,
} from "vue-router";

const routes = [{
    path: '/',
    name: 'default',
    component: () =>
        import ('../views/Index.vue')
}, {
    path: '/login',
    component: () =>
        import ('../views/Login.vue')
},  {
    path: '/cart',
    name: 'cart',
    component: () =>
        import ('../views/Cart.vue')
}, {
    path: '/register',
    name: 'register',
    component: () =>
        import ('../views/Register.vue')
},  {
    path: '/bodyBuilding',
    name: 'BodyBuilding',
    component: () =>
        import ('../components/BodyBuilding.vue')
}, {
    path: '/personal',
    name: 'personal',
    component: () =>
        import ('../views/Personal.vue'),
    children: [{
            path: '',
            component: () =>
                import ('../components/MyInfo.vue')
        }, {
            path: 'usualaddress',
            name: 'personal-usualaddress',
            component: () =>
                import ('../components/UsualAddress.vue')
        }, {
            path: 'content',
            name: 'personal-content',
            component: () =>
                import ('../components/Content.vue')
        }
    ]
}, {
    path: '/product',
    name: 'product',
    component: () =>
        import ('@/views/Product.vue')
}, {
    path: '/details/:id',
    name: 'details',
    component: () =>
        import ('@/views/Details.vue'),
    props: true,
    // children: [{
    //         path: '',
    //         component: () =>
    //             import ('@/components/GoodsInfo.vue')
    //     },
    //     // {
    //     //     path: 'goodsinfo',
    //     //     name: 'details-goodsinfo',
    //     //     component: () =>
    //     //         import ('@/components/GoodsInfo.vue')
    //     // },
    //     // {
    //     //     path: 'comment',
    //     //     name: 'details-comment',
    //     //     component: () =>
    //     //         import ('@/components/Comment.vue')
    //     // }
    // ]
}, {
    path: '/submitorder',
    name: 'submitorder',
    component: () =>
        import ('@/views/SubmitOrder.vue')
}];

const router = createRouter({
    //createWebHistory路由模式路径不带#号(生产环境下不能直接访问项目，需要nginx转发)
    //createWebHashHistory路由模式路径带#号
    history: createWebHashHistory(),
    routes,
})

export default router