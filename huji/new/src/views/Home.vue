<template>
  <div>
  <el-row :gutter="20" style="margin: 0">
    <el-col :span="12">
      <div>
        <!-- 首页user信息 -->
        <el-card shadow= 'hover'>
          <div style="display: flex">
            <div>
              <el-avatar :size="150" :src=circleUrl></el-avatar>
            </div>
            <div style="width: auto;padding: 6% 0 0 6%;">
              <div style=" font-weight: 900;font-size: 25px">
                <p>{{name}}</p>
              </div>
              <div style=" font-weight: 450;font-size: 18px">
                <p>登录时间：{{ loginTime }}</p>
              </div>
            </div>
          </div>
        </el-card>

        <!-- 登录表格 -->
        <el-card shadow= 'hover'>
          <div style="font-size: 15px;font-weight: bold">
            <p>历史登录</p>
          </div>
          <el-table :data="loginRecords">
            <el-table-column prop="username" label="用户名"></el-table-column>
            <el-table-column prop="loginTime" label="登录时间"></el-table-column>
          </el-table>
        </el-card>

      </div>
    </el-col>

    <el-col :span="12">
      <el-card shadow="hover">
        <p>当前时间</p>
        <p>
          {{nowTime}}
        </p>

      </el-card>
      <div class="calender">
        <el-card shadow="hover">
          <el-calendar v-model="value">
          </el-calendar>
        </el-card>
      </div>
    </el-col>
  </el-row>
  </div>
</template>

<script>

//设置token请求头
let user = localStorage.getItem("users") ? JSON.parse(localStorage.getItem("users")) : null;
import axios from "axios";
axios.defaults.headers.common['token'] = user.data.token;

export default {
  name: "Home",
  data() {
    return {
      value: new Date(),
      name:JSON.parse(localStorage.getItem("users")).data.Username,
      circleUrl:'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      loginTime:localStorage.getItem("loginTime"),
      loginRecords:[],
      nowTime:'',

    }
  },
  created() {
    this.recordLogin();
    // 使用 setInterval 每秒更新一次 currentTime
    this.timer = setInterval(() => {
      this.updateTime();
    }, 1000);
  },
  methods: {
    recordLogin() {
      var _this=this;
      axios({
        method:"post",
        url:"http://localhost:8090/user/loginRecord",
        data: {
          username:_this.name,
          password:''
        }
      }).then(function (resp) {
        _this.loginRecords = resp.data;
      })
    },
    updateTime() {
      const date = new Date();
      this.nowTime = date;
    }
  }
}
</script>

<style scoped>

</style>