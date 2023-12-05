<template>
<div>
  <!--表头-->

  <!--搜索栏表单-->
  <div style="margin-top: 15px; margin-left: 5px">
    <el-form :inline="true" :model="formInline" ref="refForm" class="demo-form-inline" style="margin-left: 15px; margin-top: 15px">
      <el-form-item>
        <el-input v-model="formInline.Name" placeholder="姓名"></el-input>
      </el-form-item>

      <el-form-item>
        <el-input v-model="formInline.identityID" placeholder="身份证号码">
        </el-input>
      </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit" icon="el-icon-search" size="small">查询</el-button>
        </el-form-item>

    </el-form>
  </div>
  <el-row style="margin-left: 20px; margin-bottom: 20px">
    <el-tooltip content="单击左侧以选择" placement="top" effect="light">
      <el-button type="danger" @click="deleteLimited" icon="el-icon-minus" size="small">批量删除</el-button>
    </el-tooltip>
  </el-row>


<!--  删除 对话框-->
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
          width="55">
      </el-table-column>
      <el-table-column
          label="序号"
          align="center"
          type="index"
          width="50">
      </el-table-column>
      <el-table-column
          prop="name"
          label="姓名"
          align="center"
          width="180">
      </el-table-column>
      <el-table-column
          prop="sex"
          label="性别"
          align="center"
          width="180">
      </el-table-column>
      <el-table-column
          prop="age"
          label="年龄"
          align="center"
          width="180">
      </el-table-column>
      <el-table-column
          prop="nation"
          label="民族"
          align="center"
          width="180">
      </el-table-column>
      <el-table-column
          prop="identityID"
          label="身份证号码"
          align="center"
          width="180">
      </el-table-column>

      <el-table-column
          prop="caozuo"
          align="center"
          label="操作">
        <el-row>
          <el-tooltip content="单击左侧以选择" placement="top" effect="light">
            <el-button type="text" @click="updateLimited">修改</el-button>
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
  name: "idCard",
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
        url: "http://localhost:8090/idCards/byPageAllRight?currentPage=" +_this.currentPage+ "&pageSize="+_this.pageSize,
      }).then(function (resp) {
        _this.tableData = resp.data.pageData;
        _this.totalCount=resp.data.totalCount;
      })
    },
    //删除要求选择方法
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
        url:"http://localhost:8090/idCards/delete",
        data:_this.selectedIds
      }).then(function (resp) {
        // 判断响应数据是否为 success
        if(resp.data == "success"){
          //关闭窗口
          _this.dialogVisibleDel=false;
          //搜索数据
          _this.selectAll();
          // location.reload();
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

        _this.formInline.status='办理完成';
      axios({
        method: "post",
        url: "http://localhost:8090/idCards/byPageCond?currentPage=1&pageSize=1000",
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
        url: "http://localhost:8090/idCards/All"
      }).then(function (resp) {
        _this.tableData = resp.data;
      })
    },
    //添加户籍方法
    addidCard(){
      var _this=this;
      //2. 发送ajax请求
      axios({
        method:"post",
        url:"http://localhost:8090/idCards/add",
        data:_this.form
      }).then(function (resp) {
        // 判断响应数据是否为 success
        if(resp.data == "success"){
          //搜索数据
          _this.selectAll();
          //关闭窗口
          _this.resetForm('formAdd')
          _this.dialogVisible=false;

          //添加户籍成功提示框
          _this.$message({
            message: '数据添加成功',
            type: 'success'
          });

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
    //更新户籍方法
    updateidCard(){

      let selectionElement = this.multipleSelection[0];
      this.formUpd.id=selectionElement.id;
      console.log(this.formUpd);

      var _this=this;
      //2. 发送ajax请求
      axios({
        method:"post",
        url:"http://localhost:8090/idCards/update",
        data:_this.formUpd
      }).then(function (resp) {
        // 判断响应数据是否为 success
        if(resp.data == "success"){
          //关闭窗口
          _this.resetForm('formUpd')
          _this.dialogVisibleUpd=false;
          //搜索数据
          _this.selectByPage();
          //添加户籍成功提示框
          _this.$message({
            message: '更新成功',
            type: 'success'
          });

        }
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
    }

  },
  data(){
    return {
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
        Name: '',
        identityID: '',
        status: ''
      },
      //新建 对话框隐藏属性
      dialogVisible: false,

      //删除 对话框隐藏属性
      dialogVisibleDel: false,

      //更新 对话框隐藏属性
      dialogVisibleUpd: false,

      //新建 对话框内表单
      form: {
        Name: '',//this.multipleSelection.Name,
        identityID: '',//this.multipleSelection.identityID,
        processingTime: '',//this.multipleSelection.Sex,
        collectionTime: '',//this.multipleSelection.Age,
        status: '',//this.multipleSelection.PhoneNumber,
      },

      //更新 对话框内表单
      formUpd: {
        id: '',
        status: ''
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