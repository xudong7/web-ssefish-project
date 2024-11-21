<template>
  <div class="login-container">
    <div class="login-form">
      <h1 class="form-title">校园二手闲置物品交易平台</h1>

      <el-form :model="loginData" ref="form" label-width="120px">
        <el-form-item label="Username" prop="username"
                      :rules="[{ required: true, message: 'Please input username', trigger: 'blur' }]">
          <el-input v-model="loginData.name"
                    placeholder="Enter username"
                    class="input-white"></el-input>
        </el-form-item>

        <el-form-item label="Password" prop="password"
                      :rules="[{ required: true, message: 'Please input password', trigger: 'blur' }]">
          <el-input type="password" v-model="loginData.password"
                    placeholder="Enter password"
                    class="input-white"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="login" :loading="loading">Login</el-button>
          <el-button @click="goToRegister">Register</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginData: {
        name: '',
        password: ''
      },
      loading: false // Adding loading state
    };
  },
  methods: {
    login() {
      this.loading = true; // Start loading

      // 检查输入的用户名和密码是否都为 "admin"
      if (this.loginData.name === 'admin' && this.loginData.password === 'admin') {
        // 登录成功，跳转到主页
        this.$router.push('/home');
      } else {
        // 登录失败，清空输入框并显示错误消息
        this.loginData.name = '';
        this.loginData.password = '';
        this.$message.error('Login failed: Invalid credentials');
      }

      this.loading = false; // End loading
    },
    goToRegister() {
      this.$router.push({name: 'Register'});
    },
    disableScroll() {
      document.body.style.overflow = 'hidden'; // Disable body scroll
    },
    enableScroll() {
      document.body.style.overflow = ''; // Enable body scroll
    }
  },
  mounted() {
    this.disableScroll(); // Disable scroll when component is mounted
  },
  beforeUnmount() {
    this.enableScroll(); // Enable scroll when component is unmounted
  }
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
  height: 100vh; /* Full viewport height */
  background-color: #a9ccf1; /* Optional background color for the full screen */
}

.login-form {
  max-width: 400px;
  padding: 50px;
  background: white; /* Set form background color to white if needed */
  border-radius: 8px;
  text-align: center; /* Center text inside the form */
}

.form-title {
  margin-bottom: 20px; /* Space between title and form */
  font-size: 18px; /* Set title font size to a smaller value */
  color: #003366; /* Change title color to dark blue (深蓝色) */
}

.input-white {
  background-color: rgb(255, 255, 255); /* Set input boxes to white */
}
</style>