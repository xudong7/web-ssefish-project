<template>
  <div class="register-container">
    <div class="register-form">
      <h1 class="form-title">注册校园二手闲置物品交易平台</h1>

      <el-form :model="registerData" ref="form" label-width="120px">
        <el-form-item label="Username" prop="username"
                      :rules="[{ required: true, message: '请输入昵称', trigger: 'blur' }]">
          <el-input v-model="registerData.nickname"
                    placeholder="输入昵称"
                    class="input-white"></el-input>
        </el-form-item>

        <el-form-item label="Password" prop="password"
                      :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
          <el-input type="password" v-model="registerData.password"
                    placeholder="输入密码"
                    class="input-white"></el-input>
        </el-form-item>

        <el-form-item label="Email" prop="email"
                      :rules="[
                          { required: true, message: '请输入邮箱', trigger: 'blur' },
                          { type: 'email', message: '请输入有效的邮箱地址', trigger: ['blur', 'change'] }
                        ]">
          <el-input v-model="registerData.email"
                    placeholder="输入邮箱"
                    class="input-white"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="register" :loading="loading">注册</el-button>
          <el-button @click="goToLogin">返回登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      registerData: {
        Username: '',
        Password: '',
        email: ''
      },
      loading: false // Loading state
    };
  },
  methods: {
    register() {
      this.loading = true; // Start loading

      // 模拟注册成功的逻辑
      if (this.registerData.nickname && this.registerData.password && this.registerData.email) {
        // 如果输入有效，显示成功消息并跳转
        this.$message.success('注册成功！');
        this.$router.push('/login'); // 假设跳转回登录页面
      } else {
        // 清空输入框并显示错误消息
        this.$message.error('注册失败: 请填写所有字段');
      }

      this.loading = false; // End loading
    },
    goToLogin() {
      this.$router.push({name: 'Login'}); // 跳转到登录页
    },
    disableScroll() {
      document.body.style.overflow = 'hidden'; // 禁用滚动
    },
    enableScroll() {
      document.body.style.overflow = ''; // 启用滚动
    }
  },
  mounted() {
    document.body.style.overflow = 'hidden'; // Disable scroll when component is mounted
  },
  beforeUnmount() {
    document.body.style.overflow = ''; // Enable scroll when component is unmounted
  }
};
</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
  height: 100vh; /* Full viewport height */
  background-color: #a9ccf1; /* Optional background color for the full screen */
}

.register-form {
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
