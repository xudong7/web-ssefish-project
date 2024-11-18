<template>
    <div class="login-form">
      <el-form :model="loginData" ref="form" label-width="120px">
        <el-form-item label="Username" prop="username">
          <el-input v-model="loginData.username" placeholder="Enter username"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input type="password" v-model="loginData.password" placeholder="Enter password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login">Login</el-button>
          <el-button @click="goToRegister">Register</el-button>
        </el-form-item>
      </el-form>
    </div>
  </template>
  
  <script>
  import { login } from '@/api';  // Import the login method from API
  
  export default {
    data() {
      return {
        loginData: {
          username: '',
          password: ''
        }
      };
    },
    methods: {
      login() {
        login(this.loginData)  // Call the login method
          .then(response => {
            // Assuming the backend returns a token, store it in localStorage or Vuex
            localStorage.setItem('authToken', response.data.token);
            this.$router.push('/');
          })
          .catch(error => {
            console.error("Login failed:", error);
            this.$message.error('Login failed');
          });
      },
      goToRegister() {
        this.$router.push({ name: 'Register' });
      }
    }
  };
  </script>
  
  <style scoped>
  .login-form {
    max-width: 400px;
    margin: 0 auto;
    padding: 50px;
    background: #f7f7f7;
    border-radius: 8px;
  }
  </style>
  