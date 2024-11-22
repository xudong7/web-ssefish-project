<template>
  <div>
    <el-header>
      <el-menu mode="horizontal" :default-active="activeMenu">
        <el-menu-item index="1" @click="goHome">主页</el-menu-item>
        <el-menu-item index="2" @click="goPublish">商品发布</el-menu-item>
        <el-menu-item index="3" @click="goChat">聊天</el-menu-item>
        <el-menu-item index="4" @click="goProfile">个人</el-menu-item>
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
      activeMenu: this.getActiveMenu()
    };
  },
  methods: {
    goHome() {
      this.$router.push('/home');
    },
    goPublish() {
      this.$router.push('/publish');
    },
    goProfile() {
      this.$router.push('/profile');
    },
    goChat() {
      this.$router.push('/chat');
    },
    getActiveMenu() {
      const path = this.$route.path;
      if (path === '/home') return '1';
      if (path === '/publish') return '2';
      if (path === '/chat') return '3';
      if (path === '/profile') return '4';
      return '1';
    }
  },
  watch: {
    $route() {
      this.activeMenu = this.getActiveMenu();
    }
  }
};
</script>
