<template>
<div style="display: flex; align-items: center; justify-content: center;margin-top: 30px ">
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" size="large">
    <el-form-item>
      <div style="font-weight: bold; font-size: 25px">户籍信息注册表</div>
    </el-form-item>
    <el-form-item label="姓名" prop="Name">
      <el-input v-model="ruleForm.Name"></el-input>
    </el-form-item>
    <el-form-item label="身份证号码" prop="identityID">
      <el-input v-model="ruleForm.identityID"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="Sex">
      <el-select v-model="ruleForm.Sex" placeholder="请选择性别">
        <el-option label="男" value="男"></el-option>
        <el-option label="女" value="女"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="年龄" prop="Age">
      <el-input v-model="ruleForm.Age"></el-input>
    </el-form-item>
    <el-form-item label="电话号码" prop="PhoneNumber">
      <el-input v-model="ruleForm.PhoneNumber"></el-input>
    </el-form-item>
    <el-form-item label="户籍" prop="location">
      <el-input type="textarea" v-model="ruleForm.location"></el-input>
    </el-form-item>
    <el-form-item label="办理时间" prop="processingTime">
      <el-col>
        <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="ruleForm.processingTime" style="width: 100%;"  ></el-date-picker>
      </el-col>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</div>
</template>

<script>

//设置token请求头
let user = localStorage.getItem("users") ? JSON.parse(localStorage.getItem("users")) : null;
import axios from "axios";
axios.defaults.headers.common['token'] = user.data.token;

export default {
  name: "HujiMovingAddForm",
  data() {
    return {
      ruleForm: {
        Username:'',
        Name:'',
        identityID:'',
        Sex:'',
        Age:'',
        PhoneNumber:'',
        location:'',
        processingTime: ''
      },
      rules: {
        Name: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
        identityID: [
          { required: true, message: '请输入身份证号码', trigger: 'blur' },
          { min: 18, max: 18, message: '长度为18个个字符', trigger: 'blur' }
        ],
        Age: [
          { required: true, message: '请输入年龄', trigger: 'blur' },
        ],
        PhoneNumber: [
          { required: true, message: '请输入电话号码', trigger: 'blur' },
          { min: 11, max: 11, message: '长度为11个字符', trigger: 'blur' }
        ],
        location: [
          { required: true, message: '请输入户籍', trigger: 'blur' },
        ]


      }
    };
  },
  methods: {
    submitalerts(dir){
      this.$alert('数据更新需要一定时间，请耐心等待', '注意事项', {
        confirmButtonText: '确定',
        callback: action => {
          if (action === 'confirm') {
            this.$router.push(dir);
          }
        }
      });
    },
    submitForm(formName) {
      var _this=this;
      this.$refs[formName].validate((valid) => {
        if (valid) //验证是否全部输入成功
        {
          _this.ruleForm.Username = JSON.parse(localStorage.getItem("users")).data.Username
          axios({
            method: "Post",
            url: "http://localhost:8090/huji/usrUpload",
            data: _this.ruleForm
          }).then(function (resp) {
            if(resp.data.code=="200")
            {
              //成功提示并跳转
              _this.$message({
                message: resp.data.msg,
                type: 'success'
              });
              _this.submitalerts('/HujiQuery');
            }
            else {
              _this.$message.error(resp.data.msg);
            }
          })
        } else {
          _this.$message.error("提交失败");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>

</style>