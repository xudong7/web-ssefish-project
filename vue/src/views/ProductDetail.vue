<template>
  <div>
    <el-card>
<!--      <template #header>-->
<!--        <span>{{ product.name }}</span>-->
<!--      </template>-->
      <div class="card-body">
        <img :src="product.image" alt="product image" style="max-width: 100%; height: auto;"/>
        <p><strong>Name:</strong>{{product.name}}</p>
        <p><strong>Price:</strong> ¥ {{ product.price }}</p>
        <p><strong>Description:</strong> {{ product.description }}</p>
        <p><strong>Owner:</strong> {{ product.owner }}</p>
        <el-button @click="goToHome">Back to Home</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getProductDetail } from '@/api'; // Import API method

export default {
  data() {
    return {
      product: {} // Store product details here
    };
  },
  methods: {
    // Fetch product details using the product ID from the route params
    fetchProductDetail() {
      const productId = this.$route.params.id; // Get product ID from the URL
      getProductDetail(productId)
          .then(response => {
            this.product = response.data.data; // Store product details in data
          })
          .catch(error => {
            console.error("Failed to fetch product details:", error);
            this.$message.error('Failed to fetch product details');
          });
    },
    // Navigate back to home page
    goToHome() {
      this.$router.push('/'); // Navigate to the home page
    }
  },
  created() {
    this.fetchProductDetail(); // Fetch product details when the component is created
  }
};
</script>

<style scoped>

.card-body {
  text-align: center;
  padding: 20px;
}

.product-image {
  width: 100%;
  max-width: 300px;
  margin: 0 auto;
  display: block;
}

el-button {
  margin-top: 20px;
}
</style>
