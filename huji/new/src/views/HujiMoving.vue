<template>
<div>
  <div style="margin-top: 15px; margin-left: 5px">
    <el-form :inline="true" :model="formInline" ref="refForm" class="demo-form-inline" style="margin-left: 15px; margin-top: 15px">
      <el-form-item label="户籍流动方式">
        <el-select v-model="formInline.movingTypes" placeholder="请选择">
          <el-option label="迁入" value="迁入"></el-option>
          <el-option label="迁出" value="迁出"></el-option>
          <el-option label="注销" value="注销"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>
  </div>

  <!--数据表单-->
  <div>
    <template>
      <el-table
          :data="tableData"
          stripe
          style="width: 100%;"
          header-cell-class-name="headerClass"
      >
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
            prop="locationOld"
            align="center"
            label="旧户籍地址"
            >
        </el-table-column>
        <el-table-column
            prop="locationNew"
            align="center"
            label="现户籍地址"
           >
        </el-table-column>
        <el-table-column
            prop="movingTypes"
            align="center"
            label="户籍变更方式"
            >
        </el-table-column>

      </el-table>
    </template>
  </div>

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
  name: "HujiMoving",
  data() {
    return {
      //主要呈现的数据
      tableData: [],
      //当前页码
      currentPage: 1,
      //页面大小
      pageSize: 18,
      //总记录数
      totalCount:1000,
      //顶头搜索表单数据
      formInline: {
        movingTypes: ''
      }
    }
  },
  created() {
    this.selectByPage();
  },
  methods: {
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
      // window.onload = function () {
      //2. 发送ajax请求
      axios({
        method: "get",
        url: "http://localhost:8090/hujiMoving/byPage?currentPage=" +_this.currentPage+ "&pageSize="+_this.pageSize,
      }).then(function (resp) {
        _this.tableData = resp.data.pageData;
        _this.totalCount=resp.data.totalCount;
      })
      // }
    },
    //表头搜索框执行查询方法
    onSubmit() {
      // console.log(this.formInline);
      var _this = this;
      //2. 发送ajax请求
      axios({
        method: "post",
        url: "http://localhost:8090/hujiMoving/byPageCond?currentPage=1&pageSize=1000",
        data:this.formInline
      }).then(function (resp) {
        _this.tableData = resp.data.pageData;
        _this.totalCount=resp.data.totalCount;
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

    }
  }
}
</script>

<style scoped>
.headerClass{
  background-color: #e8e8e8 !important;
}
</style>