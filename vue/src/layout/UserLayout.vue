<template>
  <div class="user-layout">
    <el-header class="header">
      <el-menu
          mode="horizontal"
          :default-active="activeMenu"
          class="nav-menu"
          background-color="#D6E9FF"
          text-color="#409EFF"
          active-text-color="#1890FF">
        <el-menu-item index="2" @click="goHomeAndActivate"> <!-- 修改点击事件为自定义方法 -->
          <el-icon style="display: flex; align-items: center; margin-right: 10px;">
            <img src="../assets/logo.png" style="width: 30px; height: auto;">
          </el-icon>
          <h2 class="title">软工闲鱼</h2>
        </el-menu-item>
        <el-menu-item index="1" @click="goChat">
          <el-icon>
            <Bell/>
          </el-icon>
          <span>公告</span>
        </el-menu-item>
        <el-menu-item index="2" @click="goHome">
          <el-icon>
            <House/>
          </el-icon>
          <span>主页</span>
        </el-menu-item>
        <el-menu-item index="3" @click="goPublish">
          <el-icon>
            <Plus/>
          </el-icon>
          <span>商品发布</span>
        </el-menu-item>
        <el-menu-item index="4" @click="goProfile">
          <el-icon>
            <User/>
          </el-icon>
          <span>个人中心</span>
        </el-menu-item>
      </el-menu>
    </el-header>
    <el-main class="main-content">
      <router-view></router-view>
    </el-main>
  </div>
</template>

<script>
import {Bell, House, Plus, User} from '@element-plus/icons-vue'

export default {
  components: {
    Bell,
    House,
    Plus,
    User
  },
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
    goHomeAndActivate() {
      this.$router.push('/home');
      this.activeMenu = '2'; // 将激活菜单项设置为主页对应的索引
    },
    getActiveMenu() {
      const path = this.$route.path;
      if (path === '/chat') return '1';
      if (path === '/home') return '2';
      if (path === '/publish') return '3';
      if (path === '/profile') return '4';
      return '2';
    },
    handleScroll() {
      const scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
      const header = document.querySelector('.header');
      if (scrollTop > 100) {
        header.style.opacity = 0;
        header.style.transform = 'translateY(-100%)';
      } else {
        header.style.opacity = 1;
        header.style.transform = 'translateY(0)';
      }
    }
  },
  watch: {
    $route() {
      this.activeMenu = this.getActiveMenu();
    }
  },
  mounted() {
    window.addEventListener('scroll', this.handleScroll);
  },
  beforeUnmount() {
    window.removeEventListener('scroll', this.handleScroll);
  }
};
</script>

<style scoped>
.title {
  color: #409EFF;
  font-size: 24px;
  margin: 0;
}

.user-layout {
  min-height: 100vh;
  background-color: #f5f7fa;
}

.header {
  padding: 0;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12);
  position: fixed;
  width: 100%;
  z-index: 999;
}

.nav-menu {
  display: flex;
}

.nav-menu.el-menu-item {
  font-size: 14px;
  height: 50px;
  line-height: 50px;
  padding: 0 20px;
}

.nav-menu.el-menu-item:hover {
  background-color: #91CFFF !important;
}

.el-icon {
  margin-right: 5px;
  font-size: 16px;
}

.main-content {
  padding-top: 60px;
  min-height: calc(100vh - 50px);
  max-width: 100%;
  margin: 0 auto;
}

</style>