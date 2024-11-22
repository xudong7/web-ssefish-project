<template>
  <div class="login-container">
    <div class="login-form">
      <h1 class="form-title">校园二手闲置物品交易平台</h1>

      <el-form :model="loginData" ref="form" label-width="120px">
        <el-form-item
            label="用户名"
            prop="username"
            :rules="[{ required: true, message: 'Please input username', trigger: 'blur' }]"
        >
          <el-input
              v-model="loginData.name"
              placeholder="Enter username"
              class="input-white"
          ></el-input>
        </el-form-item>

        <el-form-item
            label="密码"
            prop="password"
            :rules="[{ required: true, message: 'Please input password', trigger: 'blur' }]"
        >
          <el-input
              type="password"
              v-model="loginData.password"
              placeholder="Enter password"
              class="input-white"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button
              type="primary"
              @click="login"
              :loading="loading"
          >登录</el-button>
          <el-button
              @click="goToRegister"
          >注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { login } from '@/api'; // Import login API method

export default {
  data() {
    return {
      loginData: {
        name: '',
        password: ''
      },
      loading: false // 控制登录按钮加载状态
    };
  },
  methods: {
    // 登录方法
    login() {
      // 启用加载状态
      this.loading = true;

      login(this.loginData) // 调用登录接口
          .then((res) => {
            if (res.data.code === 1) {
              this.$message.success('登录成功'); // 提示登录成功
              // 模拟登录逻辑，根据用户名分配角色
              setTimeout(() => {
                if (this.loginData.name === 'admin' && this.loginData.password === 'admin') {
                  localStorage.setItem('userRole', 'admin'); // 保存角色为管理员
                  this.$router.push('/admin'); // 跳转到管理员页面
                } else if (this.loginData.name !== '' && this.loginData.password !== '') {
                  localStorage.setItem('userRole', 'user'); // 保存角色为普通用户
                  this.$router.push('/home'); // 跳转到主页
                }
                // 停止加载状态
                this.loading = false;
              }, 500); // 模拟网络延迟
            } else {
              this.$message.error(res.data.message); // 提示登录失败
              // 清空数据
              this.loginData.name = '';
              this.loginData.password = '';
            }
          })
          .catch(error => {
            console.error('Failed to login:', error);
            this.$message.error('登录失败'); // 提示登录失败
            // 清空数据
            this.loginData.name = '';
            this.loginData.password = '';
          })
          .finally(() => {
            this.loading = false; // 停止加载状态
          });
    },
    // 跳转到注册页面
    goToRegister() {
      this.$router.push({ name: 'Register' });
    },
    // 禁用滚动
    disableScroll() {
      document.body.style.overflow = 'hidden';
    },
    // 启用滚动
    enableScroll() {
      document.body.style.overflow = '';
    }
  },
  mounted() {
    this.disableScroll(); // 组件挂载时禁用滚动
  },
  beforeUnmount() {
    this.enableScroll(); // 组件卸载时启用滚动
  }
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  height: 100vh; /* 全屏高度 */
  background-color: #a9ccf1; /* 背景颜色 */
}

.login-form {
  max-width: 400px;
  padding: 50px;
  background: white; /* 表单背景颜色 */
  border-radius: 8px;
  text-align: center; /* 文本居中 */
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* 添加阴影 */
}

.form-title {
  margin-bottom: 20px; /* 标题与表单的间距 */
  font-size: 20px; /* 标题字体大小 */
  font-weight: bold; /* 标题加粗 */
  color: #003366; /* 标题颜色 */
}

.input-white {
  background-color: rgb(255, 255, 255); /* 输入框背景为白色 */
}

.el-button {
  margin: 0 5px; /* 按钮间距 */
}
</style>
