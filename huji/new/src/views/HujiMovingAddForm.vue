<template>
<div style="display: flex; align-items: center; justify-content: center;margin-top: 30px ">
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" size="large">
    <el-form-item>
      <div style="font-weight: bold; font-size: 25px">户籍迁移注册表</div>
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
    <el-form-item label="旧户籍" prop="locationOld">
      <el-input v-model="ruleForm.locationOld"></el-input>
    </el-form-item>
    <el-form-item label="新户籍" prop="locationNew">
      <el-input v-model="ruleForm.locationNew"></el-input>
    </el-form-item>
    <el-form-item label="户籍流动方式" prop="movingTypes">
      <el-select v-model="ruleForm.movingTypes" placeholder="请选择">
        <el-option label="迁入" value="迁入"></el-option>
        <el-option label="迁出" value="迁出"></el-option>
        <el-option label="注销" value="注销"></el-option>
      </el-select>
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
        Name:'',
        identityID:'',
        Sex:'',
        Age:'',
        PhoneNumber:'',
        locationOld:'',
        locationNew:'',
        movingTypes:''
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
        locationOld: [
          { required: true, message: '请输入旧的户籍', trigger: 'blur' },
        ],
        locationNew: [
          { required: true, message: '请输入新的户籍', trigger: 'blur' },
        ]

      }
    };
  },
  methods: {
    submitForm(formName) {
      var _this=this;
      this.$refs[formName].validate((valid) => {
        if (valid) //验证是否全部输入成功
        {
          axios({
            method: "Post",
            url: "http://localhost:8090/hujiMoving/add",
            data: _this.ruleForm
          }).then(function (resp) {
            if(resp.data.msg=="户籍迁移成功")
            {
              //成功提示并跳转
              _this.$message({
                message: resp.data.msg,
                type: 'success'
              });
              _this.$router.push('/hujiMoving');
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