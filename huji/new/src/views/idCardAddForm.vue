<template>
<div style="display: flex; align-items: center; justify-content: center;margin-top: 30px ">
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" size="large">
    <el-form-item>
      <div style="font-weight: bold; font-size: 25px">身份证信息注册表</div>
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
    <el-form-item label="办理时间" prop="processingTime">
      <el-col>
        <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="ruleForm.processingTime" style="width: 100%;"  @change="updateCollectionTime"></el-date-picker>
      </el-col>
    </el-form-item>
    <el-form-item label="预计领取时间" prop="collectionTime">
      <el-col>
        <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="ruleForm.collectionTime" style="width: 100%;"></el-date-picker>
      </el-col>
    </el-form-item>
    <el-form-item label="民族" prop="nation">
      <el-input v-model="ruleForm.nation"></el-input>
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
        processingTime:'',
        collectionTime:'',
        status:'正在办理',
        nation:''
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
        processingTime: [
          { required: true, message: '请选择办理日期', trigger: 'blur' },
        ],
        collectionTime: [
          { required: false},
        ],
        nation: [
          { required: true, message: '请输入民族', trigger: 'blur' },
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
            url: "http://localhost:8090/idCards/addidCards",
            data: _this.ruleForm
          }).then(function (resp) {
            if(resp.data.msg=="办理成功")
            {
              //成功提示并跳转
              _this.$message({
                message: resp.data.msg,
                type: 'success'
              });
              _this.$router.push('/IdCardMain');
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
    },
    updateCollectionTime() {
      const processingTime = new Date(this.ruleForm.processingTime); // 转换为 Date 对象
      if(! this.ruleForm.processingTime)
      {
        this.ruleForm.collectionTime = '';
        return
      }
      else
      {
        const collectionTime = new Date(processingTime); // 克隆办理时间
        collectionTime.setMonth(collectionTime.getMonth() + 2); // 添加两个月

        // 更新领取时间
        const year = collectionTime.getFullYear();
        const month = (collectionTime.getMonth() + 1).toString().padStart(2, '0'); // 补零
        const day = collectionTime.getDate().toString().padStart(2, '0'); // 补零
        this.ruleForm.collectionTime = `${year}-${month}-${day}`;
      }
    },
  }
}
</script>

<style scoped>

</style>