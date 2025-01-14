<template>
  <div class="user-layout">
    <header class="header">
      <div class="header-container">
        <!-- Logo Section -->
        <div class="logo-section" @click="goHomeAndActivate">
          <img src="../assets/logo.png" class="logo-img" alt="logo">
          <h2 class="title">软工闲鱼</h2>
        </div>

        <!-- Navigation Menu -->
        <el-menu
            mode="horizontal"
            :default-active="activeMenu"
            class="nav-menu"
            background-color="#D6E9FF"
            text-color="#409EFF"
            active-text-color="#1890FF">
          <el-menu-item index="1" @click="goChat">
            <el-icon><Bell /></el-icon>
            <span>公告</span>
          </el-menu-item>
          <el-menu-item index="2" @click="goHome">
            <el-icon><House /></el-icon>
            <span>主页</span>
          </el-menu-item>
          <el-menu-item index="3" @click="goPublish">
            <el-icon><Plus /></el-icon>
            <span>商品发布</span>
          </el-menu-item>
          <el-menu-item index="4" @click="goProfile">
            <el-icon><User /></el-icon>
            <span>个人中心</span>
          </el-menu-item>
        </el-menu>
      </div>
    </header>
    
    <main class="main-content">
      <router-view></router-view>
    </main>
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
.user-layout {
  min-height: 100vh;
  background: linear-gradient(135deg, #f0f5ff 0%, #f5f7fa 100%);
}

.header {
  padding: 0;
  background: linear-gradient(to bottom, rgba(214, 233, 255, 0.95), rgba(214, 233, 255, 0.85));
  backdrop-filter: blur(10px);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  width: 100%;
  z-index: 999;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.header-container {
  display: flex;
  align-items: center;
  max-width: 80%;
  margin: 0 auto;
  height: 60px;
}

.logo-section {
  display: flex;
  align-items: center;
  cursor: pointer;
  padding: 0 20px;
  transition: all 0.3s ease;
}

.logo-section:hover {
  transform: translateY(-1px);
}

.logo-img {
  width: 32px;
  height: auto;
  margin-right: 12px;
  transition: transform 0.3s ease;
}

.logo-section:hover .logo-img {
  transform: scale(1.05);
}

.title {
  background: linear-gradient(45deg, #1890ff, #409EFF);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  font-size: 24px;
  margin: 0;
  font-weight: 600;
  transition: all 0.3s ease;
}

.nav-menu {
  flex: 1;
  display: flex;
  justify-content: flex-end;
  border: none !important;
  background-color: transparent !important;
}

.nav-menu :deep(.el-menu-item) {
  font-size: 14px;
  height: 60px;
  line-height: 60px;
  padding: 0 24px;
  border: none !important;
  color: #666;
  transition: all 0.3s ease;
}

.nav-menu :deep(.el-menu-item:hover) {
  background-color: rgba(24, 144, 255, 0.08) !important;
  color: #1890ff !important;
}

.nav-menu :deep(.el-menu-item.is-active) {
  background-color: rgba(24, 144, 255, 0.1) !important;
  color: #1890ff !important;
  border-bottom: 2px solid #1890ff !important;
  font-weight: 500;
}

.nav-menu :deep(.el-menu-item .el-icon) {
  margin-right: 6px;
  font-size: 16px;
  transition: transform 0.3s ease;
}

.nav-menu :deep(.el-menu-item:hover .el-icon) {
  transform: translateY(-1px);
}

.main-content {
  padding-top: 80px;
  min-height: calc(100vh - 60px);
  max-width: 80%;
  margin: 0 auto;
  transition: all 0.3s ease;
}

@media (max-width: 1200px) {
  .header-container,
  .main-content {
    max-width: 90%;
  }
}

@media (max-width: 768px) {
  .header-container,
  .main-content {
    max-width: 95%;
  }
  
  .nav-menu :deep(.el-menu-item) {
    padding: 0 15px;
  }
  
  .title {
    font-size: 20px;
  }
}
</style>