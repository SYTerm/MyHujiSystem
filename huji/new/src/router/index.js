import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: () => import(/* webpackChunkName: "about" */ '../views/Manager.vue'),
    children:[
      {
        path: 'HujiShow',
        name: '户籍信息',
        component: () => import('../views/Huji.vue'),
        meta: { requiresAdmin: true }
      },
      {
        path: 'Home',
        name: '主页',
        component: () => import('../views/Home.vue'),
        meta: { requiresAdmin: true }
      },
      {
        path: 'IdCard',
        name: '身份证办理',
        component: () => import('../views/IdCard.vue'),
        meta: { requiresAdmin: true }
      },
      {
        path: 'IdCardMain',
        name: '身份证信息',
        component: () => import('../views/IdCardMain.vue'),
        meta: { requiresAdmin: true }
      },
      {
        path: 'IdCardAdd',
        name: '身份证注册',
        component: () => import('../views/idCardAddForm.vue'),
        meta: { requiresAdmin: true }
      },
      {
        path: 'hujiMoving',
        name: '户籍迁移信息',
        component: () => import('../views/HujiMoving.vue'),
        meta: { requiresAdmin: true }
      },
      {
        path: 'hujiMovingAdd',
        name: '户籍迁移信息注册',
        component: () => import('../views/HujiMovingAddForm.vue'),
        meta: { requiresAdmin: true }
      },
      {
        path: 'PC',
        name: '个人中心',
        component: () => import('../views/PersonalCenter.vue'),
        meta: { requiresAdmin: true }
      },
      {
        path: 'HujiAdd',
        name: '户籍信息添加',
        component: () => import('../views/HujiAddForm.vue'),
        meta: { requiresAdmin: true }
      },
      {
        path: 'UserManager',
        name: '用户管理',
        component: () => import('../views/UserManager.vue'),
        meta: { requiresAdmin: true }
      },
      {
        path: 'HujiQuery',
        name: '户籍待办事项',
        component: () => import('../views/HujiQuery.vue'),
        meta: { requiresAdmin: true }
      },
      {
        path: 'HujiMovingQuery',
        name: '户籍迁移待办事项',
        component: () => import('../views/HujiMovingQuery.vue'),
        meta: { requiresAdmin: true }
      },
    ]
  },

  {
    path: '/ForUser',
    name: 'User',
    component: () => import('../views/ForUser.vue'),
    children:[
      {
        path: 'Home',
        name: 'User主页',
        component: () => import('../views/Home.vue'),
      },
      {
        path: 'IdCardAdd',
        name: 'User身份证注册',
        component: () => import('../views/idCardAddForm.vue'),
      },
      {
        path: 'hujiMovingAdd',
        name: 'User户籍迁移信息注册',
        component: () => import('../views/HujiMovingAddForm.vue'),
      },
      {
        path: 'PC',
        name: 'User个人中心',
        component: () => import('../views/PersonalCenter.vue'),
      },
      {
        path: 'HujiAdd',
        name: 'User户籍信息添加',
        component: () => import('../views/HujiAddForm.vue'),
      },
      {
        path: 'MyIdCard',
        name: 'User身份证办理进度查询',
        component: () => import('../views/IdCardSearchForUser.vue'),
      },
      {
        path: 'notice',
        name: 'User消息',
        component: () => import('../views/notice.vue'),
      },
      ]
  },


  {
    path: '/Login',
    name: '登录',
    component: () => import('../views/Login.vue'),
  },

  {
    path: '/Register',
    name: '注册',
    component: () => import('../views/Register.vue'),
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//登录验证
router.beforeEach((to, from, next) => {
  // 获取本地存储中的用户信息
  const user = localStorage.getItem('users');


  // 判断是否访问的是登录或注册页面
  if (to.path === '/login' || to.path === '/Register') {
    // 如果是登录或注册页面，直接放行
    next();
  } else {
    // 如果不是登录或注册页面
    if (user) {
      const isAdmin = JSON.parse(localStorage.getItem("users")).data.Username; // 获取管理员信息
      // 如果用户已经登录，放行
      //next();
      if (to.matched.some(record => record.meta.requiresAdmin)) {
        if (isAdmin === 'admin') { // 判断是否为管理员
          next();
        } else {
          next('/ForUser/Home'); // 不是管理员则跳转到普通用户页面
        }
      } else {
        next();
      }
    } else {
      // 如果用户未登录，跳转到登录页面
      next('/login');
    }
  }
});

/*
//权限限制访问
router.beforeEach((to, from, next) => {


  if (to.matched.some(record => record.meta.requiresAdmin)) {
    if (isAdmin === 'admin') { // 判断是否为管理员
      next();
    } else {
      next('/ForUser/Home'); // 不是管理员则跳转到普通用户页面
    }
  } else {
    next();
  }
});
*/


// 路由守卫
router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name)//设置当前的路由名称,为了在Header组件中去使用
  store.commit("setPath")//store数据更新
  next()//放行路由
})
export default router
