<template>
    <div>
      <el-card>
        <template #header>
          <span>{{ product.name }}</span>
        </template>
        <div class="card-body">
          <img :src="product.imageUrl" alt="product image" />
          <p>{{ product.price }} USD</p>
          <p>{{ product.description }}</p>
          <el-button @click="goToHome">Back to Home</el-button>
        </div>
      </el-card>
    </div>
  </template>
  
  <script>
  import { getProductDetail } from '@/api';  // Import the getProductDetail method from API
  
  export default {
    data() {
      return {
        product: {}
      };
    },
    methods: {
      fetchProductDetail() {
        const productId = this.$route.params.id;
        getProductDetail(productId)  // Call the getProductDetail method
          .then(response => {
            this.product = response.data;
          })
          .catch(error => {
            console.error("Failed to fetch product details:", error);
            this.$message.error('Failed to fetch product details');
          });
      },
      goToHome() {
        this.$router.push('/');
      }
    },
    created() {
      this.fetchProductDetail();
    }
  };
  </script>
  
  <style scoped>
  .card-body {
    text-align: center;
  }
  </style>
  