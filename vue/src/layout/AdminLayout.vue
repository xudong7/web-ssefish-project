<template>
  <div>
    <el-header>
      <el-menu mode="horizontal" :default-active="activeMenu">
        <el-menu-item index="1" @click="goAdmin">Admin Dashboard</el-menu-item>
        <el-menu-item index="2" @click="goUsers">Manage Users</el-menu-item>
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
    goAdmin() {
      this.$router.push('/admin');
    },
    goUsers() {
      this.$router.push('/admin/users');
    },
    getActiveMenu() {
      const path = this.$route.path;
      if (path === '/admin') return '1';
      if (path === '/admin/users') return '2';
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
