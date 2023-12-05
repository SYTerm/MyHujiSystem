import Vue from 'vue'
import App from './App.vue'
import router from './router'
//需要引入的elementUI部分
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css';
import axios from "axios";

Vue.config.productionTip = false
Vue.use(ElementUI);

import VueRouter from 'vue-router'
import store from "./store";

Vue.use(VueRouter)

// 解决ElementUI导航栏中重复点菜单报错问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
