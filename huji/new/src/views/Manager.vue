<template>
  <div>
          <!--内部表-->
      <el-container style="height: 100vh">
          <!--侧边栏-->
        <div class="aside">
          <el-aside width="sideWidth + 'px'" style="height: 100%; background-color: rgb(200,200,200);">
            <Aside :isCollapse="isCollapse"/>
          </el-aside>
        </div>

        <el-main style="padding: 0">
          <!--表头-->
          <el-header style="font-size: 12px; border-bottom: 2px solid #ccc; line-height: 60px; display: flex;flex-direction: column-reverse;">
           <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse"/>
          </el-header>

          <!--路由页面-->
          <router-view></router-view>
        </el-main>

      </el-container>

  </div>
</template>

<script>
import axios from "axios";
import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'HomeView',
  components: {Header, Aside},

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
    }
  },

  data(){
    return {
      //动态侧边栏图标
      collapseBtnClass: "el-icon-s-fold",
      //是否折叠侧边栏
      isCollapse: false,
      //是否侧边栏宽度
      sideWidth:200
    }

  }
}
</script>
<style>

</style>
