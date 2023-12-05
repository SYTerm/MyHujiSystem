<template>
    <el-menu :default-openeds="['1','2','3']" style="height: 100%; padding: 0;"
             background-color="rgb(48,65,86)"
             text-color=#fff
             active-text-color=rgb(256,256,0)
             :collapse-transition="false"
             :collapse="isCollapse"
              router>

      <div style="height: 60px; line-height: 60px; text-align: center">
        <i class="el-icon-monitor" style="width: 20px; position: relative; color: white"></i>
        <b style="color: aliceblue" v-show="!isCollapse">
          户籍信息管理系统
        </b>
      </div>

      <el-menu-item index="/Home">
        <template slot="title">
          <i class="el-icon-s-home"></i>
          <span slot="title">主页</span>
        </template>
      </el-menu-item>

      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span slot="title">户籍管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/HujiShow">户籍信息</el-menu-item>
        </el-menu-item-group>
      </el-submenu>

      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span slot="title">身份证管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/IdCardMain">身份证信息</el-menu-item>
          <el-menu-item index="/IdCard">身份证办理</el-menu-item>
          <el-menu-item index="/IdCardAdd">身份证注册</el-menu-item>
        </el-menu-item-group>
      </el-submenu>

      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-setting"></i>
          <span slot="title">户籍迁移管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/hujiMoving">户籍迁移信息</el-menu-item>
          <el-menu-item index="/hujiMovingAdd">户籍迁移信息注册</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props:{
    isCollapse:Boolean,

  },


}
</script>

<style scoped>

</style>