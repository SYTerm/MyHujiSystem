<template>
  <div>
  <el-row :gutter="20" style="margin: 15px; height: 100%">
    <el-col :span="12" style="padding-right: 50px">
      <div>
        <!-- 首页user信息 -->
        <el-row style="margin-bottom: 20px;">
        <el-card shadow= 'hover' style="border-radius: 20px">
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
        </el-row>

        <!-- 登录表格 -->
        <el-row :gutter="20">
        <el-card shadow= 'hover' style="border-radius: 20px;height: 100%">
          <div style="font-size: 15px;font-weight: bold">
            <p>历史登录</p>
          </div>
          <el-table :data="loginRecords">
            <el-table-column prop="username" label="用户名"></el-table-column>
            <el-table-column prop="loginTime" label="登录时间"></el-table-column>
          </el-table>
        </el-card>
        </el-row>
      </div>
    </el-col>

    <el-col :span="12" >
      <el-row :gutter="20" style="margin-bottom: 20px;">
        <el-card shadow="hover" style="height: 50%;width: 100%; border-radius: 20px">
          <p>当前时间</p>
          <p>
            {{nowTime}}
          </p>
        </el-card>
      </el-row>


      <el-row :gutter="20" style=" height: 100%">
        <div style="width: 100%; height: 400px;">
          <el-card shadow="hover" style="width: 100%; height: 100%;border-radius: 20px">
                      <el-calendar v-model="value">
                      </el-calendar>
          </el-card>
        </div>
      </el-row>

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

      formData1:{
        name:JSON.parse(localStorage.getItem("users")).data.Username,
        password: '*****'
      },

    }
  },
  created() {
    this.recordLogin();
    // 使用 setInterval 每秒更新一次 currentTime
    this.timer = setInterval(() => {
      this.updateTime();
    }, 1000);
    this.showHuji();
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
    },
    submitalerts(mesg,dir){
      this.$alert(mesg, '注意事项', {
        confirmButtonText: '确定',
        showClose:false,
        callback: action => {
          if (action === 'confirm') {
            this.$router.push(dir);
          }
        }
      });
    },


    showHuji(){
      var _this = this;
      axios({
        method: "Post",
        url: "http://localhost:8090/user/showHuji",
        data: {
          Username:_this.formData1.name,
          Password:_this.formData1.password
        }

      }).then(function (resp) {
        if(resp.data.data != null)
        {
          console.log(resp.data.data);
        }
        else
        {
          console.log("建议先关联户籍");
          if(_this.formData1.name != 'admin')
          _this.submitalerts("未发现您绑定了户籍，现在前往个人中心绑定户籍","/ForUser/PC")
        }
      })

    },

  }
}
</script>

<style scoped>

</style>