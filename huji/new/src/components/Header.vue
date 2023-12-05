<template>
  <div style="display: flex; line-height: 60px; ">
    <!--        导航栏收缩-->
    <div style="flex: 1; font-size: 18px">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
      <!--面包屑-->
      <div style="margin-left: 15px; display: inline-block">
                <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/Home' }">主页</el-breadcrumb-item>
          <el-breadcrumb-item><a href="/">{{ currentPathName }}</a></el-breadcrumb-item>
        </el-breadcrumb>
      </div>
    </div>
    <!--        下拉表单-->
    <el-dropdown style="width: 70px; cursor: pointer">
      <span>{{ userName }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="PC">个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="logout">注销</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props:{
    collapseBtnClass:String,
    collapse:Function
  },
  computed: {
    currentPathName(){
      return this.$store.state.currentPathName//监听
    }
  },

  watch:{
    currentPathName (newval, oldval) {
      console.log(newval)
    }
  },
  data(){
    return{
      userName:JSON.parse(localStorage.getItem("users")).data.Username
    }
  },
  methods: {

    logout(){
      // 删除本地存储中的用户信息
      localStorage.removeItem('users');

      // 返回到登录界面
      this.$router.push('/login');
    },

    PC(){
      this.$router.push('/PC');
    }

  }

}
</script>

<style scoped>

</style>