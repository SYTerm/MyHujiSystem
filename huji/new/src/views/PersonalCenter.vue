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

    <el-row :gutter="20" style="margin: 15px; height:100%">
      <el-col :span="12">
        <div>
          <!-- 首页user信息 -->
          <el-card shadow= 'hover' style="height: 100%; border-radius: 20px">
            <div style="height: 75%; display: flex; justify-content: center;">
                <el-form :model="formData1" :rules="rules" ref="loginRef">
                  <div style="font-size: 20px; font-weight: bold;align-items: center; text-align:center; margin: 20px;">
                    用户信息<br/>
                  </div>

                  <el-form-item>
                    <el-descriptions :column="1">
                      <el-descriptions-item label="用户名">{{formData1.name}}</el-descriptions-item>
                      <el-descriptions-item label="密码">{{formData1.password}}</el-descriptions-item>
                    </el-descriptions>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" style="width: 50%" @click="changeVisible">
                      {{bian}}密码
                    </el-button>
                  </el-form-item>

                  <el-form-item>
                    <el-button type="primary" style="width: 50%" @click="dialogVisibleDel=true">
                      修改密码
                    </el-button>
                  </el-form-item>

                  <el-form-item>
                    <el-button type="primary" style="width: 50%" @click="dialogVisibleCon=true">
                      绑定户籍
                    </el-button>
                  </el-form-item>

                </el-form>
            </div>

          </el-card>
        </div>
      </el-col>
      <el-col :span="12">

        <el-row style="margin-bottom: 20px;">
          <el-card shadow= 'hover' style="height: 50%; border-radius: 20px">
            <div style="height: 100%; display: flex; justify-content: center;">
              <el-descriptions title="用户户籍信息" :model="formData2" :column="1">
                <el-descriptions-item label="姓名">{{ formData2.name }}</el-descriptions-item>
                <el-descriptions-item label="性别">{{ formData2.Sex }}</el-descriptions-item>
                <el-descriptions-item label="年龄">{{ formData2.Age }}</el-descriptions-item>
                <el-descriptions-item label="身份证号码">{{ formData2.identityID }}</el-descriptions-item>
                <el-descriptions-item label="电话号码">{{ formData2.PhoneNumber }}</el-descriptions-item>
                <el-descriptions-item label="户籍地">{{ formData2.Location }}</el-descriptions-item>
              </el-descriptions>
            </div>
          </el-card>
        </el-row>

        <el-row>
          <el-card shadow= 'hover' style="height: 50%; border-radius: 20px">
            <div>
              <el-row :gutter="20">
                <el-col :span="24">
                  <div style="text-align: center; font-size: 25px">
                    <p style="margin-top: 0;margin-bottom: 20px">您的情况</p>
                  </div>

                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="8">
                  <div>
                    <el-statistic title="户籍迁入次数"  :model="Details">
                      <template slot="formatter">
                        {{ Details.In }}
                      </template>
                    </el-statistic>
                  </div>
                </el-col>
                <el-col :span="8">
                  <div>
                    <el-statistic title="户籍迁出次数" :model="Details">
                      <template slot="formatter">
                        {{ Details.Out }}
                      </template>
                    </el-statistic>
                  </div>
                </el-col>
                <el-col :span="8">
                  <div>
                    <el-statistic title="上一次的户籍地" :model="Details">
                      <template slot="formatter">
                        {{ Details.LastLocation }}
                      </template>
                    </el-statistic>
                  </div>
                </el-col>
              </el-row>

              <el-row :gutter="20">
                <el-col :span="8">
                  <div>
                    <el-statistic title="身份证状态" :model="Details">
                      <template slot="formatter">
                        {{ Details.Status }}
                      </template>
                    </el-statistic>
                  </div>
                </el-col>
              </el-row>

            </div>
          </el-card>
        </el-row>

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
          {required: true, message: '请输入密码', trigger: 'blur'},
          // {validator:checkedPasswd, trigger: 'blur'}
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
        password: '*****'
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
      Details:{
        In: '',
        Out: '',
        LastLocation: '',
        Status: ''
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
            url: "http://localhost:8090/user/update?PasswdIn="+_this.formData.passwordOld,
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
      {
        this.bian='关闭';
        this.getPasswordFromBackend();
      }
      else
      {
        this.bian='查看';
        this.formData1.password ='*****'
      }
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
              _this.formData2 = resp.data.data
              _this.getDetails(_this.formData2.identityID,_this.formData2.Location)
            }
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
    },

    getDetails(id, lo){
      var _this = this;
      axios({
        method: "Post",
        url: "http://localhost:8090/hujiMoving/details",
        data: {
          identityID:id,
          locationNew:lo
        }

      }).then(function (resp) {
        if(resp.data != null)
        {
          _this.Details.In =resp.data.data.In
          _this.Details.Out = resp.data.data.Out
          _this.Details.Status = resp.data.data.Status
          if(resp.data.data.Last.locationOld != null)
            _this.Details.LastLocation = resp.data.data.Last.locationOld
          else
            _this.Details.LastLocation = _this.formData2.Location
        }

      })
    }
  }
}
</script>

<style scoped>

</style>