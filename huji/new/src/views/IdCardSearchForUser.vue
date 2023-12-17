<template>
<div>
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
        label="您的身份证办理进度"
        align="center">

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
          prop="identityID"
          label="身份证号码"
          align="center"
          width="180">
      </el-table-column>
        <el-table-column
            prop="types"
            label="事务类型"
            align="center"
            width="180">
        </el-table-column>
      <el-table-column
          prop="processingTime"
          align="center"
          label="办理时间">
      </el-table-column>
      <el-table-column
          prop="collectionTime"
          align="center"
          label="领取时间">
      </el-table-column>
      <el-table-column
          prop="status"
          align="center"
          label="状态">
      </el-table-column>

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
        method: "Post",
        url: "http://localhost:8090/idCards/byPageCondUsr?currentPage=" +_this.currentPage+ "&pageSize="+_this.pageSize,
        data:{
          Username: _this.name
        }
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

      name: JSON.parse(localStorage.getItem("users")).data.Username,
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