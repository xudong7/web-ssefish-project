<template>
  <div>
    <el-card>
      <div class="card-body">
        <img :src="product.image" alt="product image" style="max-width: 80%; height: auto;"/>
        <p><strong>Name:</strong>{{product.name}}</p>
        <p><strong>Price:</strong> ¥ {{ product.price }}</p>
        <p><strong>Description:</strong> {{ product.description }}</p>
        <p><strong>Seller:</strong> {{ seller.name }} </p>
        <p><strong>Location:</strong> {{ addressMap[product.address] }}</p>
        <el-button @click="goToHome">Back to Home</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getProductDetail, getSellerById } from '@/api'; // Import API method

export default {
  data() {
    return {
      product: {}, // Store product details here
      seller: {}, // Store seller details here
      addressMap: {
        1: '南校区',
        2: '东校区',
        3: '北校区',
        4: '深圳校区',
        5: '珠海校区'
      },
    };
  },
  methods: {
    findSeller(sellerId) {
      getSellerById(sellerId)
          .then(response => {
            this.seller = response.data.data;
          })
          .catch(error => {
            console.error("Failed to fetch seller details:", error);
            this.$message.error('Failed to fetch seller details');
          });
    },
    // Fetch product details using the product ID from the route params
    fetchProductDetail() {
      const productId = this.$route.params.id; // Get product ID from the URL
      getProductDetail(productId)
          .then(response => {
            this.product = response.data.data; // Store product details in data
            this.findSeller(this.product.sellerId); // fetch seller details by seller ID of the product
          })
          .catch(error => {
            console.error("Failed to fetch product details:", error);
            this.$message.error('Failed to fetch product details');
          });
    },
    goToHome() {
      this.$router.push('/home'); // Navigate to the home page
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
