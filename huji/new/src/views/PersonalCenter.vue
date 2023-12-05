<template>
  <div>
<!--修改密码对话框-->
    <el-dialog
        :visible.sync="dialogVisibleDel"
        width="30%"
        :show-close=false
        >
      <el-form :model="formData" :rules="rules" ref="loginRef">
        <div style="font-size: 20px; font-weight: bold;align-items: center; text-align:center; margin: 20px;">
          修改密码
        </div>
        <!--用户名-->
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" size="medium" placeholder="请输入用户名" v-model="formData.username"></el-input>
        </el-form-item>
        <!--密码-->
        <el-form-item prop="passwordOld">
          <el-input prefix-icon="el-icon-lock"size="medium" show-password placeholder="请输入旧的密码" v-model="formData.passwordOld"></el-input>
        </el-form-item>

        <!--新的密码-->
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock"size="medium" show-password placeholder="请输入新的密码" v-model="formData.password"></el-input>
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

      </el-form>
    <el-button @click="closeForm({
        formName: 'loginRef',
        visibleProp: 'dialogVisibleDel'
        })">取 消</el-button>
    <el-button type="primary" @click="updateAccount">确 定</el-button>

    </el-dialog>

    <!--户籍关联对话框-->
    <el-dialog
        :visible.sync="dialogVisibleCon"
        width="30%"
        :show-close=false
    >
      <el-form :model="formDataCon" :rules="rules2" ref="conRef">
        <div style="font-size: 20px; font-weight: bold;align-items: center; text-align:center; margin: 20px;">
          绑定户籍
        </div>
        <!--用户名-->
        <el-form-item prop="Username">
          <el-input prefix-icon="el-icon-user" size="medium" placeholder="请输入用户名" v-model="formDataCon.Username"></el-input>
        </el-form-item>
        <!--姓名-->
        <el-form-item prop="name">
          <el-input prefix-icon="el-icon-user-solid" size="medium" placeholder="请输入姓名" v-model="formDataCon.Name"></el-input>
        </el-form-item>

        <!--身份证号码-->
        <el-form-item prop="identityID">
          <el-input prefix-icon="el-icon-lock"size="medium" placeholder="请输入身份证号码" v-model="formDataCon.identityID"></el-input>
        </el-form-item>


      </el-form>
      <el-button @click="closeForm({
        formName: 'conRef',
        visibleProp: 'dialogVisibleCon'
        })">取 消</el-button>
      <el-button type="primary" @click="WithHuji">确 定</el-button>

    </el-dialog>

    <el-row :gutter="20" style="margin: 0; height: 100vh">
      <el-col :span="12">
        <div>
          <!-- 首页user信息 -->
          <el-card shadow= 'hover' style="height: 100vh">
            <div style="height: 100vh; display: flex; justify-content: center;">
                <el-form :model="formData1" :rules="rules" ref="loginRef">
                  <div style="font-size: 20px; font-weight: bold;align-items: center; text-align:center; margin: 20px;">
                    用户信息<br/>
                  </div>
                  <!--用户名-->
                  <el-form-item prop="username" label="用户名">
                    <el-input :disabled=true prefix-icon="el-icon-user"  placeholder="用户名" v-model="formData1.name"></el-input>
                  </el-form-item>
                  <!--密码-->
                  <el-form-item prop="password" label="密码">
                    <el-input :disabled=true prefix-icon="el-icon-lock" :show-password=isMouseOver   placeholder="密码" v-model="formData1.password"></el-input>
                  </el-form-item>

                  <el-form-item>
                    <el-button type="primary" style="width: 100%" @click="changeVisible">
                      {{bian}}密码
                    </el-button>
                  </el-form-item>

                  <el-form-item>
                    <el-button type="primary" style="width: 100%" @click="dialogVisibleDel=true">
                      修改密码
                    </el-button>
                  </el-form-item>

                  <el-form-item>
                    <el-button type="primary" style="width: 100%" @click="dialogVisibleCon=true">
                      绑定户籍
                    </el-button>
                  </el-form-item>

                </el-form>
            </div>

          </el-card>
        </div>
      </el-col>
      <el-col :span="12">
        <el-card shadow= 'hover' style="height: 100vh">
          <div style="height: 100vh; display: flex; justify-content: center;">
          <el-form :model="formData2" :rules="rules2" ref="MineRef">
            <div style="font-size: 20px; font-weight: bold;align-items: center; text-align:center; margin: 20px;">
              用户户籍信息<br/>
            </div>
            <!--用户名-->
            <el-form-item prop="name" label="姓名">
              <el-input :disabled="true"  v-model="formData2.name"></el-input>
            </el-form-item>

            <el-form-item prop="Sex" label="性别">
              <el-input :disabled="true"  v-model="formData2.Sex"></el-input>
            </el-form-item>

            <el-form-item prop="Age" label="年龄">
              <el-input :disabled="true"  v-model="formData2.Age"></el-input>
            </el-form-item>
            <el-form-item prop="Age" label="身份证号码">
              <el-input :disabled="true"  v-model="formData2.identityID"></el-input>
            </el-form-item>
            <el-form-item prop="PhoneNumber" label="电话号码">
              <el-input :disabled="true"  v-model="formData2.PhoneNumber"></el-input>
            </el-form-item>

            <el-form-item prop="Location" label="户籍地">
              <el-input :disabled="true"  v-model="formData2.Location"></el-input>
            </el-form-item>

          </el-form>
          </div>

        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import validCode from "@/components/validCode";
//设置token请求头
let user = localStorage.getItem("users") ? JSON.parse(localStorage.getItem("users")) : null;
import axios from "axios";
axios.defaults.headers.common['token'] = user.data.token;

export default {
  name: "PersonalCenter",
  components: {validCode},
  data(){


    //校验用户名
    var checkedUsername = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'));
      } else if (value != this.formData1.name) {
        callback(new Error('输入用户名错误'));
      } else
        callback();
    };
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
      } else if (value != this.formData1.password) {
        callback(new Error('输入密码错误'));
      } else
        callback();
    };
    return{

      code:'',//图片验证码
      formData:{
        code:'',//输入验证码
        username :'',
        passwordOld :'',
        password:''//输入新的密码

      },
      rules: {
        username: [
          {validator:checkedUsername, trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur'}
        ],
        passwordOld: [
          {validator:checkedPasswd, trigger: 'blur'}
        ],
        code: [
          {validator:validCode, trigger: 'blur'}
        ]
      },
      rules2:{
        Username: [
          {validator:checkedUsername, trigger: 'blur'}
        ],
      },

      formData1:{
        name:JSON.parse(localStorage.getItem("users")).data.Username,
        password:null
      },
      formData2:{
        Name:'',
        identityID:'',
        Sex:'',
        Age:'',
        PhoneNumber:'',
        Location:'',

      },
      formDataCon:{
        Username:'',
        Name:'',
        identityID:''
      },

      circleUrl:'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      // 修改密码可见
      dialogVisibleDel:false,
      // 绑定户籍可见
      dialogVisibleCon:false,
      // 密码可见
      isMouseOver:true,
      bian:'查看'


    }
  },

  created() {
   this.showHuji();
    this.getPasswordFromBackend();
  },


  methods:{
    getCode(code){
      this.code=code
    },
    updateAccount(){
      var _this = this;
      this.$refs['loginRef'].validate((valid) => {
        if (valid) //验证是否全部输入成功
        {
          axios({
            method: "Post",
            url: "http://localhost:8090/user/update",
            data: _this.formData
          }).then(function (resp) {
            if(resp.data.msg=="请求成功")
            {
              //登陆成功提示并跳转
              _this.$message({
                message: '修改成功，即刻重新登录',
                type: 'success'
              });
              localStorage.removeItem('users'); //删除用户数据
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

    },

    //关联户籍
    WithHuji(){

      var _this = this;
      this.$refs['conRef'].validate((valid) => {
        if (valid) //验证是否全部输入成功
        {
          axios({
            method: "Post",
            url: "http://localhost:8090/user/ConnectHuji",
            data: _this.formDataCon
          }).then(function (resp) {
            if(resp.data.msg=="请求成功")
            {
              //登陆成功提示并跳转
              _this.$message({
                message: '关联成功',
                type: 'success'
              });
              location.reload();
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

    },

    //取消方法
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },

    //重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

    //关闭表单方法
    closeForm(formInfo) {
      this.resetForm(formInfo.formName); // 调用重置表单的方法
      this[formInfo.visibleProp] = false; // 根据传入的可见性属性名来控制表单的显示与隐藏
    },
    // 显示密码
    changeVisible(){
      this.isMouseOver = !this.isMouseOver;
      if(this.bian =='查看')
        this.bian='关闭';
      else
        this.bian='查看';
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
            _this.formData2 = resp.data.data
          })

    },
    getPasswordFromBackend(){
      var _this = this;
      axios({
        method: "Post",
        url: "http://localhost:8090/user/showPS",
        data: {
          Username:_this.formData1.name,
        }

      }).then(function (resp) {
        _this.formData1.password= resp.data.password
      })
    }
  }
}
</script>

<style scoped>

</style>