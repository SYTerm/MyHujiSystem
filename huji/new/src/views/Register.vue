<template>
<!--  宽度100%，高度一个页面垂直水平居中 : display: flex; align-items: center; justify-content: center; -->
  <div style="height: 100vh; display: flex; align-items: center; justify-content: center; background-color: cornflowerblue">

    <div style="background-color: white; width: 30%; border-radius: 10px; height: 475px; display: flex; align-items: center;justify-content: center">

        <el-form :model="formData" :rules="rules" ref="loginRef">
          <div style="font-size: 20px; font-weight: bold;align-items: center; text-align:center; margin: 20px;">
            户籍信息管理系统<br/>
            欢迎注册
          </div>
<!--用户名-->
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user" size="medium" placeholder="请输入用户名" v-model="formData.username"></el-input>
          </el-form-item>
<!--密码-->
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock"size="medium" show-password placeholder="请输入密码" v-model="formData.password"></el-input>
          </el-form-item>

<!--二次密码-->
          <el-form-item prop="passwordChecked">
            <el-input prefix-icon="el-icon-lock"size="medium" show-password placeholder="请输入确认密码" v-model="formData.passwordChecked"></el-input>
          </el-form-item>

          <el-form-item prop="code">
            <div style="display: flex">
<!--验证码-->
                <el-input prefix-icon="el-icon-check"size="medium" placeholder="输入右侧验证码" v-model="formData.code"></el-input>

              <div style="flex: 1; height: 36px">
                <valid-code @input="getCode" />
              </div>
            </div>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" style="width: 100%" @click="register">
              注册
            </el-button>
          </el-form-item>

          <div>已有账号？请<a href="/login">登录</a> </div>
        </el-form>

    </div>
  </div>
</template>

<script>
import validCode from "@/components/validCode";
import axios from "axios";

export default {
  name: "Register.vue",
  components: {validCode},
  comments:{
    validCode
  },
  data(){

    //校验验证码
    var validCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'));
      } else if (value.toLowerCase() != this.code.toLocaleLowerCase()) {
        callback(new Error('验证码错误'));
      } else
        callback();
    };

    //校验密码
    var checkedPasswd = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入确认密码'));
      } else if (value != this.formData.password) {
        callback(new Error('两次输入密码不一致'));
      } else
        callback();
    };

    return{
      code:'',//图片验证码
      formData:{
        code:'',//输入验证码
        username :'',
        password :'',
        passwordChecked:''//输入二次密码

      },
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur'}
        ],
        passwordChecked: [
          {validator:checkedPasswd, trigger: 'blur'}
        ],
        code: [
          {validator:validCode, trigger: 'blur'}
        ]
      }
    }

  },
  methods: {

    getCode(code){
      this.code=code
    },
    register(){
      var _this = this;
      this.$refs['loginRef'].validate((valid) => {
        if (valid) //验证是否全部输入成功
        {
          axios({
            method: "Post",
            url: "http://localhost:8090/user/register",
            data: _this.formData
          }).then(function (resp) {
            if(resp.data.msg=="请求成功")
            {
              //登陆成功提示并跳转
              _this.$message({
                message: '注册成功，即刻返回登录页面',
                type: 'success'
              });
              localStorage.setItem("users",JSON.stringify(resp.data)); //存储用户数据
              _this.$router.push('/login');
            }
            else {
              _this.$message.error(resp.data.msg);
            }
          })
        }

        else {
          console.log('error submit!!');
          return false;
        }
      });


    }
  },

}
</script>

<style scoped>

</style>