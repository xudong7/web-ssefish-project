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
      activeMenu: this.getActiveMenu() // Dynamically set the active menu based on the current route
    };
  },
  methods: {
    // Navigate to different routes
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
      } else if (path === '/login') {
        return '4'; // Login
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
