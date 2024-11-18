<template>
    <div>
      <el-form :model="user" ref="form" label-width="120px">
        <el-form-item label="Username" prop="username">
          <el-input v-model="user.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input v-model="user.email" disabled></el-input>
        </el-form-item>
        <el-form-item label="Phone Number" prop="phoneNumber">
          <el-input v-model="user.phoneNumber" disabled></el-input>
        </el-form-item>
        <el-form-item label="Address" prop="address">
          <el-input v-model="user.address"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateProfile">Update Profile</el-button>
          <el-button @click="goBack">Back</el-button>
        </el-form-item>
      </el-form>
    </div>
  </template>
  
  <script>
  import { updateUserProfile } from '@/api';  // Import the updateUserProfile method from API
  
  export default {
    data() {
      return {
        user: {
          username: '',
          email: '',
          phoneNumber: '',
          address: ''
        }
      };
    },
    methods: {
      fetchUserProfile() {
        // Fetch the user profile from API or Vuex store
        // For example, if it's stored in Vuex:
        const user = JSON.parse(localStorage.getItem('userProfile')); // Assuming user info is stored in localStorage
        this.user = user || {};
      },
      updateProfile() {
        updateUserProfile(this.user)  // Call the updateUserProfile method
          .then(() => {
            this.$message.success('Profile updated successfully');
          })
          .catch(error => {
            console.error("Failed to update profile:", error);
            this.$message.error('Failed to update profile');
          });
      },
      goBack() {
        this.$router.push({ name: 'Home' });
      }
    },
    created() {
      this.fetchUserProfile();
    }
  };
  </script>
  
  <style scoped>
  /* Add your styles here */
  </style>
  