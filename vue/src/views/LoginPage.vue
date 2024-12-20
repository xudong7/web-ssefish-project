<template>
  <div class="login-container">
    <div class="login-form">
      <h1 class="form-title">校园二手闲置物品交易平台</h1>

      <el-form :model="loginData" ref="form" label-width="120px">
        <el-form-item
            label="用户名"
            prop="username"
            :rules="[{ required: true, message: '请输入用户名', trigger: 'blur' }]">
          <el-input
              v-model="loginData.name"
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
              v-model="loginData.password"
              placeholder="请输入密码" 
              class="input-field"
              prefix-icon="el-icon-lock"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button
              type="primary"
              @click="goToLogin"
              :loading="loading"
              class="login-btn">
            登录
          </el-button>
          <el-button
              @click="goToRegister"
              class="register-btn">
            注册
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { login } from '@/api';

export default {
  data() {
    return {
      loginData: {
        name: '',
        password: ''
      },
      loading: false
    };
  },
  methods: {
    goToLogin() {
      this.loading = true;

      login(this.loginData)
          .then((res) => {
            if (res.data.code === 1) {
              this.$message.success('登录成功');

              localStorage.setItem('token', res.data.data.token);
              localStorage.setItem('user', JSON.stringify(res.data.data.user));

              setTimeout(() => {
                if (this.loginData.name === 'admin' && this.loginData.password === 'admin') {
                  localStorage.setItem('userRole', 'admin');
                  this.$router.push('/admin');
                } else if (this.loginData.name !== '' && this.loginData.password !== '') {
                  localStorage.setItem('userRole', 'user');
                  this.$router.push('/home');
                }

                const storedUser = JSON.parse(localStorage.getItem('user'));
                if (storedUser && storedUser.password) {
                  delete storedUser.password;
                  localStorage.setItem('user', JSON.stringify(storedUser));
                }

                this.loading = false;
              }, 500);
            } else {
              this.$message.error(res.data.message);
              this.loginData.name = '';
              this.loginData.password = '';
            }
          })
          .catch(error => {
            console.error('Failed to login:', error);
            this.$message.error('登录失败');
            this.loginData.name = '';
            this.loginData.password = '';
          })
          .finally(() => {
            this.loading = false;
          });
    },
    checkLoginStatus() {
      const user = localStorage.getItem('user');

      if (user) {
        const parsedUser = JSON.parse(user);
        this.loginData.name = parsedUser.name;
        this.loginData.password = parsedUser.password;
        this.goToLogin();
      } else {
        this.loginData.name = '';
        this.loginData.password = '';
      }
    },
    goToRegister() {
      this.$router.push({name: 'Register'});
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
    this.checkLoginStatus();
  },
  beforeUnmount() {
    this.enableScroll();
  }
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-image: url('@/assets/loginBack.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.login-form {
  max-width: 450px;
  width: 90%;
  padding: 40px 50px;
  background: rgba(255, 255, 255, 0.95);
  border-radius: 15px;
  text-align: center;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
  backdrop-filter: blur(10px);
  transition: transform 0.3s ease;
}

.login-form:hover {
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
