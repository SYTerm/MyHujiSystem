<template>
<div>

  <!--搜索栏表单-->
  <div style="margin-top: 15px; margin-left: 5px">
    <el-form :inline="true" :model="formInline" class="demo-form-inline" style="margin-left: 15px; margin-top: 15px">
      <el-form-item >
        <el-input v-model="formInline.Username" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit" icon="el-icon-search" size="small">查询</el-button>
      </el-form-item>
    </el-form>
  </div>
  <!--新建 删除按钮-->
  <el-row style="margin-left: 20px; margin-bottom: 20px">
    <el-button type="primary" @click="dialogVisible = true" icon="el-icon-plus" size="small">新建</el-button>
    <el-tooltip content="单击左侧以选择" placement="top" effect="light">
      <el-button type="danger" @click="deleteLimited" icon="el-icon-minus" size="small">批量删除</el-button>
    </el-tooltip>
  </el-row>

  <!--新建 对话框-->
  <el-dialog
      title="添加用户"
      :visible.sync="dialogVisible"
      :show-close=false
      width="30%">
    <el-form ref="formAdd" :model="form" label-width="100px" >
      <el-form-item label="用户名" prop="Username">
        <el-input v-model="form.Username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="Password">
        <el-input show-password v-model="form.Password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addUser" >立即创建</el-button>
        <el-button @click="closeForm({
        formName: 'formAdd',
        visibleProp: 'dialogVisible'
        })">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <!--显示密码 对话框-->
  <el-dialog
      title="用户信息"
      :visible.sync="dialogVisiblePasswd"
      :show-close=false
      width="25%">
    <el-form ref="formAdd" :model="formPasswd" label-width="100px" >
      <!--<el-form-item label="用户名" prop="Username">
        <el-input v-model="formPasswd.Username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="Password">
        <el-input v-model="formPasswd.Password"></el-input>
      </el-form-item>-->
      <el-form-item>
        <el-descriptions :column="1">
          <el-descriptions-item label="用户名">{{formPasswd.Username}}</el-descriptions-item>
          <el-descriptions-item label="密码">{{formPasswd.Password}}</el-descriptions-item>
        </el-descriptions>
      </el-form-item>
      <el-form-item>
        <el-button @click="closeForm({
        formName: 'formAdd',
        visibleProp: 'dialogVisiblePasswd'
        })">确定</el-button>
      </el-form-item>
    </el-form>

  </el-dialog>

  <!--显示户籍 对话框-->
  <el-dialog
      title="用户信息"
      :visible.sync="dialogVisibleShowHuji"
      :show-close=false
      width="50%">
    <el-form ref="formShowHuji" label-width="100px" >
      <el-form-item>
        <div style="height: 100%; display: flex; justify-content: center;">
          <el-descriptions title="用户信息" :model="formData2" :column="1">
            <el-descriptions-item label="姓名">{{ formData2.name }}</el-descriptions-item>
            <el-descriptions-item label="性别">{{ formData2.Sex }}</el-descriptions-item>
            <el-descriptions-item label="年龄">{{ formData2.Age }}</el-descriptions-item>
            <el-descriptions-item label="身份证号码">{{ formData2.identityID }}</el-descriptions-item>
            <el-descriptions-item label="电话号码">{{ formData2.PhoneNumber }}</el-descriptions-item>
            <el-descriptions-item label="户籍地">{{ formData2.Location }}</el-descriptions-item>
          </el-descriptions>
        </div>
      </el-form-item>
      <el-form-item>
        <el-button @click="closeForShowHuji({
        formName: 'formShowHuji',
        visibleProp: 'dialogVisibleShowHuji'
        })">确定</el-button>
      </el-form-item>
    </el-form>

  </el-dialog>

  <!--户籍关联对话框-->
  <el-dialog
      :visible.sync="dialogVisibleCon"
      width="30%"
      :show-close=false
      title="绑定户籍"
  >
    <el-form :model="formDataCon" :rules="rules2" ref="conRef">
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


  <!--删除 对话框-->
  <el-dialog
      title="提示"
      :visible.sync="dialogVisibleDel"
      width="30%"
      :show-close=false>
    <span>确定要删除吗</span>
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisibleDel = false">取 消</el-button>
    <el-button type="primary" @click="deleteBySelectedIds">确 定</el-button>
  </span>
  </el-dialog>


  <!--更新 对话框-->
  <el-dialog
      title="修改密码"
      :visible.sync="dialogVisibleUpd"
      :show-close=false
      width="30%">
    <el-form ref="formUpd" :rules="rulesUpd" :model="formUpd" label-width="100px" >
      <!--密码-->
      <el-form-item label="旧密码" prop="passwordOld">
        <el-input prefix-icon="el-icon-lock"size="medium" show-password placeholder="请输入旧的密码" v-model="formUpd.passwordOld"></el-input>
      </el-form-item>

      <!--新的密码-->
      <el-form-item label="新密码" prop="password">
        <el-input prefix-icon="el-icon-lock"size="medium" show-password placeholder="请输入新的密码" v-model="formUpd.password"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="updateUser" >更新</el-button>
        <el-button @click="closeForm({
        formName: 'formUpd',
        visibleProp: 'dialogVisibleUpd'
        })">取消</el-button>

      </el-form-item>
    </el-form>

  </el-dialog>

  <!--数据表单-->
  <template>
    <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        @selection-change="handleSelectionChange"
        header-cell-class-name="headerClass"
    >

      <el-table-column
          type="selection"
          align="center"
          width="60">
      </el-table-column>
      <el-table-column
          label="序号"
          align="center"
          type="index"
          width="120">
      </el-table-column>
      <el-table-column
          prop="Username"
          label="用户名"
          align="center"
          width="240">
      </el-table-column>
      <el-table-column
          prop="caozuo"
          align="center"
          label="操作">
        <el-row>
          <el-tooltip content="单击左侧以选择" placement="top" effect="light">
            <el-button type="text" @click="updateLimited">修改密码</el-button>
          </el-tooltip>
          <el-tooltip content="单击左侧以选择" placement="top" effect="light">
            <el-button type="text" @click="showPasslimited">显示密码</el-button>
          </el-tooltip>
          <el-tooltip content="单击左侧以选择" placement="top" effect="light">
            <el-button type="text" @click="showHujilimited">显示户籍</el-button>
          </el-tooltip>
          <el-tooltip content="单击左侧以选择" placement="top" effect="light">
            <el-button type="text" @click="WithHujiLimited">绑定户籍</el-button>
          </el-tooltip>
          <el-tooltip content="单击左侧以选择" placement="top" effect="light">
            <el-button type="text" @click="deleteLimited">删除</el-button>
          </el-tooltip>
        </el-row>
      </el-table-column>

    </el-table>
  </template>

  <!--分页条-->
  <el-pagination
      align="center"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[8, 12, 16, 24]"
      :page-size="100"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalCount">
  </el-pagination>
</div>
</template>

<script>

//设置token请求头
let user = localStorage.getItem("users") ? JSON.parse(localStorage.getItem("users")) : null;
import axios from "axios";
axios.defaults.headers.common['token'] = user.data.token;

export default {
  name: "Huji",
  // mounted() {
  //   this.selectByPage();
  // },
  created() {
    this.selectByPage();
  },
  methods: {
    handleClick(row) {
      console.log(row);
    },
    // 显示密码
    showPasslimited(){
      if(this.multipleSelection.length==0)
        this.dialogVisiblePasswd=false;
      else
      {
        this.dialogVisiblePasswd=true;
        var _this=this;
        var name= _this.multipleSelection[0].Username
        axios({
          method:"Post",
          url:"http://localhost:8090/user/GetPasswd",
          data: {
            Username: name
          }
        }).then(function (resp) {
          _this.formPasswd = resp.data.data
        })
      }
    },

    //显示户籍
    showHujilimited(){
      if(this.multipleSelection.length==0)
        this.dialogVisibleShowHuji=false;
      else
      {
        this.dialogVisibleShowHuji=true;
        var _this=this;
        var name= _this.multipleSelection[0].Username
        axios({
          method:"Post",
          url:"http://localhost:8090/user/showHuji",
          data: {
            Username: name
          }
        }).then(function (resp) {
          if(resp.data.data !=null)
          _this.formData2 = resp.data.data

        })
      }
    },


    //更新户籍要求选择方法
    WithHujiLimited(){
      if(this.multipleSelection.length==0)
        this.dialogVisibleCon=false;
      else
        this.dialogVisibleCon=true;
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

    // 左侧导航栏收缩函数
    collapse(){
      this.isCollapse = !this.isCollapse
      if(this.isCollapse)//收缩
      {
        this.sideWidth = 64
        this.collapseBtnClass = "el-icon-s-unfold"
      }
      else
        this.collapseBtnClass = "el-icon-s-fold"
    },

    //按页查询
    selectByPage(){
      var _this = this;
      //2. 发送ajax请求
      axios({
        method: "get",
        url: "http://localhost:8090/user/byPage?currentPage=" +_this.currentPage+ "&pageSize="+_this.pageSize,
      }).then(function (resp) {
        _this.tableData = resp.data.pageData;
        _this.totalCount=resp.data.totalCount;
      })
    },
    //删除户籍要求选择方法
    deleteLimited(){
      if(this.multipleSelection.length==0)
        this.dialogVisibleDel=false;
      else
        this.dialogVisibleDel=true;
    },

    //批量删除方法
    deleteBySelectedIds(){
      //    补充selectedIds数组
      for(let i=0;i<this.multipleSelection.length;i++)
      {
        let selectionElement = this.multipleSelection[i];
        this.selectedIds[i]=selectionElement.id;
      }
      var _this=this;
      axios({
        method:"delete",
        url:"http://localhost:8090/user/deletes",
        data:_this.selectedIds
      }).then(function (resp) {
        // 判断响应数据是否为 success
        if(resp.data == "success"){
          //关闭窗口
          _this.dialogVisibleDel=false;
          //搜索数据
          _this.selectByPage();
          //添加户籍成功提示框
          _this.$message({
            message: '删除成功',
            type: 'warning'
          });

        }
      })
    },
    //删除取消方法
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    //复选框选中后执行方法
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection);
    },
    //表头搜索框执行查询方法
    onSubmit() {
      var _this = this;
      axios({
        method: "post",
        url: "http://localhost:8090/user/byPageCond?currentPage=1&pageSize=1000",
        data:this.formInline
      }).then(function (resp) {
        _this.tableData = resp.data.pageData;
        _this.totalCount=resp.data.totalCount;
      })
    },
    //搜索全部户籍方法
    selectAll(){
      var _this = this;
      axios({
        method: "get",
        url: "http://localhost:8090/user/All"
      }).then(function (resp) {
        _this.tableData = resp.data;
      })
    },
    //添加户籍方法
    addUser(){
      var _this=this;
      //2. 发送ajax请求
      axios({
        method:"post",
        url:"http://localhost:8090/user/register",
        data:_this.form
      }).then(function (resp) {
        // 判断响应数据是否为 success
        if(resp.data.msg == "请求成功"){
          //搜索数据
          _this.selectByPage();
          //关闭窗口
          _this.resetForm('formAdd')
          _this.dialogVisible=false;

          //添加户籍成功提示框
          _this.$message({
            message: '用户添加成功',
            type: 'success',
            duration:1000
          });

        }
        else
        {
          _this.$message.error(resp.data.msg);

        }
      })

    },
    //更新户籍要求选择方法
    updateLimited(){
      if(this.multipleSelection.length==0)
        this.dialogVisibleUpd=false;
      else
        this.dialogVisibleUpd=true;
    },
    //更新用户方法
    updateUser(){

      this.$refs['formUpd'].validate((valid) => {
            if (valid) //验证是否全部输入成功
            {
      let selectionElement = this.multipleSelection[0];
      this.formUpd.Username=selectionElement.Username;
      console.log(this.formUpd);

      var _this=this;
      //2. 发送ajax请求
      axios({
        method:"post",
        url:"http://localhost:8090/user/update?PasswdIn="+_this.formUpd.passwordOld,
        data:_this.formUpd
      }).then(function (resp) {
        // 判断响应数据是否为 success
        if(resp.data.msg == "请求成功"){
          //关闭窗口
          _this.resetForm('formUpd');
          _this.dialogVisibleUpd=false;
          //搜索数据
          _this.selectByPage();
          //添加户籍成功提示框
          _this.$message({
            message: '更新成功',
            type: 'success'
          });

        }
        else
        {
          _this.$message.error(resp.data.msg);
        }
      }) }
  })
    },
    //分页条方法
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      var _this = this;
      _this.pageSize=val;
      _this.selectByPage();
    },
    //当前页面变化方法
    handleCurrentChange(val) {
      var _this = this;
      _this.currentPage=val;
      console.log(_this.currentPage);
      _this.selectByPage();

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

    //关闭重置内容2
    closeForShowHuji(){
      Object.keys(this.formData2).forEach(key => {
        this.$set(this.formData2, key, null);
      });
      this.dialogVisibleShowHuji= false; // 根据传入的可见性属性名来控制表单的显示与隐藏
    }

  },
  data(){
    //校验密码
    var checkedPasswd = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入确认密码'));
      } else if (value != this.formUpd.password) {
        callback(new Error('输入密码错误'));
      } else
        callback();
    };
    //校验用户名
    var checkedUsername = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'));
      } else if (value != this.multipleSelection[0].Username) {
        callback(new Error('输入用户名错误'));
      } else
        callback();
    };

    return {
      rulesUpd: {
        password: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
          {min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur'},
          {validator:checkedPasswd, trigger: 'blur'}
        ],
        passwordOld: [
          {required: true, message: '请输入旧密码', trigger: 'blur'},
        ],
      },
      rules2:{
        Username: [
          {validator:checkedUsername, trigger: 'blur'}
        ],
      },
      //表单数据
      tableData: [],

      //动态侧边栏图标
      collapseBtnClass: "el-icon-s-fold",
      //是否折叠侧边栏
      isCollapse: false,
      //动态侧边栏宽度
      sideWidth: 200,
      //动态侧边栏高度
      sideHeight: 200,
      //顶头搜索表单数据
      formInline: {
        Username: '',
      },
      //展示密码 对话框隐藏属性
      dialogVisiblePasswd: false,

      //新建 对话框隐藏属性
      dialogVisible: false,

      //删除 对话框隐藏属性
      dialogVisibleDel: false,

      //更新 对话框隐藏属性
      dialogVisibleUpd: false,

      //显示户籍 对话框隐藏属性
      dialogVisibleShowHuji: false,

      //绑定户籍 对话框隐藏属性
      dialogVisibleCon: false,

      //展示密码 对话框内表单
      formPasswd: {
        Username: '',
        Password: ''
      },

      //新建 对话框内表单
      form: {
        Username: '',
        Password: ''
      },

      //更新 对话框内表单
      formUpd: {
        Username: '',
        passwordOld: '',
        password: ''
      },

      //显示户籍 对话框
      formData2:{
        Name:'',
        identityID:'',
        Sex:'',
        Age:'',
        PhoneNumber:'',
        Location:'',

      },
      //绑定户籍 对话框
      formDataCon:{
        Username:'',
        Name:'',
        identityID:''
      },

      //更新 提示框可见性
      visible: false,

      //复选框选中id集合
      selectedIds:[],

      //复选框选中集合
      multipleSelection: [],

      //当前页码
      currentPage: 1,
      //页面大小
      pageSize: 8,
      //总记录数
      totalCount:1000,
      //表头颜色
      headerClass:'headerClass'

    }

  }
}
</script>

<style>
.headerClass{
  background-color: #e8e8e8 !important;
}
</style>