<template>
  <div style="justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;">
    <el-card v-for="(item, index) in tableData" :key="index"
             shadow= 'hover' style="width: 35%;margin-bottom: 10px; border-radius: 20px">

        <div style="height: 100%; display: flex; justify-content: center;">
          <el-descriptions title="消息" :model="tableData" :column="1">
            <el-descriptions-item label="通知用户">{{ tableData[index].username }}</el-descriptions-item>
            <el-descriptions-item label="内容">{{ tableData[index].info }}</el-descriptions-item>
            <el-descriptions-item label="时间">{{ tableData[index].processingTime }}</el-descriptions-item>
          </el-descriptions>
        </div>
    </el-card>


    <!--分页条-->
    <el-pagination
        align="center"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[4, 8, 16]"
        :page-size="100"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalCount">
    </el-pagination>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "notice",
  created() {
    this.selectByPage();
  },
  data(){
    return{

      //通知
      tableData: [],
      formData2: {
        Username: '',
        info: '',
        processingTime: ''
      },
      //当前页码
      currentPage: 1,
      //页面大小
      pageSize: 8,
      //总记录数
      totalCount:16,

    }
  },
  methods:{
    selectByPage(){
      var _this = this;
      //2. 发送ajax请求
      axios({
        method: "Post",
        url: "http://localhost:8090/notice/byPage?currentPage=" +_this.currentPage+ "&pageSize="+_this.pageSize,
        data:{
          Username:JSON.parse(localStorage.getItem("users")).data.Username,
        }
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

    },
  }
}
</script>

<style scoped>

</style>