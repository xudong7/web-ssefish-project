<template>
  <div class="register-container">
    <div class="register-form">
      <h1 class="form-title" style="text-align: center;">校园二手闲置物品交易平台</h1>

      <el-form :model="user" ref="form" label-width="120px">
        <el-form-item 
            label="用户名" 
            prop="name"
            :rules="[{ required: true, message: '请输入用户名', trigger: 'blur' }]">
          <el-input
              v-model="user.name"
              placeholder="请输入用户名"
              class="input-field"
              prefix-icon="el-icon-user"
          ></el-input>
        </el-form-item>

        <el-form-item 
            label="密码" 
            prop="password"
            :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
          <el-input
              type="password" 
              v-model="user.password"
              placeholder="请输入密码"
              class="input-field"
              prefix-icon="el-icon-lock"
          ></el-input>
        </el-form-item>

        <el-form-item 
            label="邮箱" 
            prop="email"
            :rules="[
              { required: true, message: '请输入邮箱', trigger: 'blur' },
              { type: 'email', message: '请输入有效的邮箱地址', trigger: ['blur', 'change'] }
            ]">
          <el-input
              v-model="user.email"
              placeholder="请输入邮箱"
              class="input-field"
              prefix-icon="el-icon-message"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button
              type="primary"
              @click="register"
              :loading="loading"
              class="login-btn">
            注册
          </el-button>
          <el-button
              @click="goToLogin"
              class="register-btn">
            返回登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { addUser } from "@/api";

export default {
  data() {
    return {
      user: {
        name: "",
        password: "",
        email: "",
      },
      loading: false
    };
  },
  methods: {
    register() {
      this.loading = true;

      addUser(this.user)
          .then(() => {
            this.$message.success('注册成功');
            this.$router.push({name: 'Login'});
          })
          .catch(error => {
            console.error('Failed to register:', error);
            this.$message.error('注册失败');
          })
          .finally(() => {
            this.loading = false;
          });
    },
    goToLogin() {
      this.$router.push({name: 'Login'});
    },
    disableScroll() {
      document.body.style.overflow = 'hidden';
    },
    enableScroll() {
      document.body.style.overflow = '';
    }
  },
  mounted() {
    this.disableScroll();
  },
  beforeUnmount() {
    this.enableScroll();
  }
};
</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-image: url('@/assets/loginBack.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.register-form {
  width: 400px;
  padding: 40px;
  background: rgba(255, 255, 255, 0.95);
  border-radius: 15px;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

.register-form:hover {
  transform: translateY(-5px);
}

.form-title {
  margin-bottom: 30px;
  font-size: 24px;
  font-weight: 600;
  color: #2c3e50;
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
}

.input-field {
  background-color: #ffffff;
  border-radius: 8px;
  transition: all 0.3s ease;
}

.input-field:hover, .input-field:focus {
  box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
}

.login-btn, .register-btn {
  width: 120px;
  height: 40px;
  margin: 10px;
  border-radius: 20px;
  font-size: 16px;
  transition: all 0.3s ease;
}

.login-btn {
  background: #409EFF;
  border: none;
}

.login-btn:hover {
  background: #66b1ff;
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(64, 158, 255, 0.3);
}

.register-btn {
  background: #ffffff;
  border: 1px solid #dcdfe6;
}

.register-btn:hover {
  background: #f5f7fa;
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

:deep(.el-form-item__label) {
  font-size: 16px;
  color: #2c3e50;
}
</style>
