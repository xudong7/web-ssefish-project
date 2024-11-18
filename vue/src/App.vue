<template>
  <div id="app">
    <el-header>
      <el-menu mode="horizontal" :default-active="activeMenu">
        <el-menu-item index="1" @click="goHome">Home</el-menu-item>
        <el-menu-item index="2" @click="goPublish">Publish Product</el-menu-item>
        <el-menu-item index="3" @click="goProfile">Profile</el-menu-item>
        <el-menu-item index="4" @click="goLogin">Login</el-menu-item>
      </el-menu>
    </el-header>
    <el-main>
      <router-view></router-view>
    </el-main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeMenu: '1'  // Default active menu item is "Home"
    };
  },
  methods: {
    goHome() {
      this.$router.push('/');
    },
    goPublish() {
      this.$router.push('/publish');
    },
    goProfile() {
      this.$router.push('/profile');
    },
    goLogin() {
      this.$router.push('/login');
    }
  }
};
</script>

<style>
#app {
  background-color: #f4f4f4;
  min-height: 100vh;
}
</style>
