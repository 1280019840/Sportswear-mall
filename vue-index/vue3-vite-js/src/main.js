import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router/index'
import axios from 'axios'

import ViewUIPlus from 'view-ui-plus'
import 'view-ui-plus/dist/styles/viewuiplus.css'

// import NProgress from 'nprogress'
// import 'nprogress/nprogress.css'

import ElementPlus from 'element-plus'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'element-plus/dist/index.css'

import MyUI from '@/components'


axios.defaults.baseURL = 'http://localhost:8082/'
    // 设置请求拦截器
axios.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    let user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null
    if (user) {
        config.headers['token'] = user.token; // 设置请求头
    }
    return config
})

import vueAxios from 'vue-axios'


// axios.interceptors.response.use(config => {
//         NProgress.done()
//         return config
//     })
// // axios响应式拦截器
// axios.interceptors.response.use(res => res.data, e => {
//     // 统一错误提示
//     MyUI({ type: 'warn', text: e.response.data.message })
//
//     return Promise.reject(e)
// })

// createApp.prototype.$http = axios

// createApp.config.productionTip = false

// const app = createApp(App)
// app.provide('$axios', axios)
// app.use(router)
// app.use(ViewUIPlus)
// app.use(MyUI)
// app.mount('#app')

//  createApp(App).use(ElementPlus).use(MyUI).use(ViewUIPlus).use(router)
//     .use(vueAxios, axios).mount("#app");


const app = createApp(App)
app.use(ElementPlus)
app.use(MyUI)
app.use(ViewUIPlus)
app.use(router)
app.use(vueAxios, axios)
app.mount("#app")
    app.config.warnHandler = (msg, instance, trace) => {}
    for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
        app.component(key, component)
    }