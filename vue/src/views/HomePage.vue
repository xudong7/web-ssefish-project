<template>
  <div>
    <el-row>
      <el-col :span="8" v-for="product in products" :key="product.id">
        <el-card>
          <template #header>
            <span>{{ product.name }}</span>
          </template>
          <div class="card-body">
            <img :src="product.imageUrl" alt="product image" />
            <p>{{ product.price }} USD</p>
            <el-button @click="goToProductDetail(product.id)">View Details</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getProducts } from '@/api';  // Import the getProducts method from API

export default {
  data() {
    return {
      products: []
    };
  },
  methods: {
    fetchProducts() {
      getProducts()  // Call the getProducts method
        .then(response => {
          this.products = response.data;
        })
        .catch(error => {
          console.error("Failed to fetch products:", error);
          this.$message.error('Failed to fetch products');
        });
    },
    goToProductDetail(id) {
      this.$router.push({ name: 'ProductDetail', params: { id } });
    }
  },
  created() {
    this.fetchProducts();
  }
};
</script>

<style scoped>
.card-body {
  text-align: center;
}
</style>
