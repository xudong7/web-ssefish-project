<template>
  <div id="app">
    <el-header v-if="!isPrePage"> <!-- 条件渲染，如果不是预页面则显示 -->
      <el-menu mode="horizontal" :default-active="activeMenu">
        <el-menu-item index="1" @click="goHome">Home</el-menu-item>
        <el-menu-item index="2" @click="goPublish">Publish Product</el-menu-item>
        <el-menu-item index="3" @click="goProfile">Profile</el-menu-item>
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
      activeMenu: this.getActiveMenu() // Dynamically set the active menu based on the current route
      // activeMenu: '1'  // Default active menu item is "Home"
    };
  },
  computed: {
    isPrePage() {
      // 检查当前路由名称是否为 PrePage
      return this.$route.name === 'PrePage';
    }
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
    // Determine which menu item should be active based on the current route
    getActiveMenu() {
      const path = this.$route.path;
      if (path === '/') {
        return '1'; // Home
      } else if (path === '/publish') {
        return '2'; // Publish Product
      } else if (path === '/profile') {
        return '3'; // Profile
      }
      return '1'; // Default to Home if no match
    }
  },
  watch: {
    // Watch for route changes and update the active menu
    $route() {
      this.activeMenu = this.getActiveMenu(); // Update active menu based on new route
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