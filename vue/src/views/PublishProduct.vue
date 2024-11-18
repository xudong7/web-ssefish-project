<template>
  <div>
    <el-form :model="product" ref="form" label-width="120px">
      <el-form-item label="Product Name" prop="name">
        <el-input v-model="product.name"></el-input>
      </el-form-item>
      <el-form-item label="Price" prop="price">
        <el-input v-model="product.price"></el-input>
      </el-form-item>
      <el-form-item label="Description" prop="description">
        <el-input type="textarea" v-model="product.description"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitProduct">Publish Product</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { publishProduct } from '@/api';  // Import the publishProduct method from API

export default {
  data() {
    return {
      product: {
        name: '',
        price: '',
        description: ''
      }
    };
  },
  methods: {
    submitProduct() {
      publishProduct(this.product)  // Call the publishProduct method
        .then(() => {
          this.$router.push({ name: 'Home' });
        })
        .catch(error => {
          console.error("Failed to publish product:", error);
          this.$message.error('Failed to publish product');
        });
    }
  }
};
</script>

<style scoped>
/* Add your styles here */
</style>
