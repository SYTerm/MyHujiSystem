<template>
  <div style="height: 100%">
    <el-container style="height: 100%">
      <el-header style="width: 100%; background-color:#545c64;">
        <div style="display: flex; justify-content: center;">
          <el-menu :default-active="activeIndex"
                   class="el-menu-demo"
                   mode="horizontal"
                   @select="handleSelect"
                   background-color="#545c64"
                   text-color="#fff"
                   active-text-color="#ffd04b"
                   router>
            <el-menu-item index="/ForUser/Home">主页</el-menu-item>

            <el-submenu index="2" >
              <template slot="title">户籍</template>
              <el-menu-item index="2-2">我的户籍</el-menu-item>
              <el-menu-item index="/ForUser/hujiMovingAdd">户籍迁移信息注册</el-menu-item>
            </el-submenu>

            <el-submenu index="3">
            <template slot="title">身份证</template>
            <el-menu-item index="/ForUser/IdCardAdd">身份证注册</el-menu-item>
            <el-menu-item index="3-2">身份证注册进度查询</el-menu-item>
            </el-submenu>
            <el-menu-item index="/ForUser/PC">个人中心</el-menu-item>
            <el-menu-item @click.native="logout">登出</el-menu-item>
          </el-menu>
        </div>
      </el-header>
      <el-main style="height: 100%">
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>


<script>
//设置token请求头
let user = localStorage.getItem("users") ? JSON.parse(localStorage.getItem("users")) : null;
import axios from "axios";
axios.defaults.headers.common['token'] = user.data.token;
export default {
  name: "ForUser",
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '1'
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    logout(){
      // 删除本地存储中的用户信息
      localStorage.removeItem('users');

      // 返回到登录界面
      this.$router.push('/login');
    }
  }
}
</script>

<style scoped>

</style>