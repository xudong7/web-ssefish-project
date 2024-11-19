<template>
  <div>
    <el-form :model="product" ref="form" label-width="120px">
      <!-- Product Name Input -->
      <el-form-item label="Product Name" prop="name">
        <el-input v-model="product.name" placeholder="Enter product name"></el-input>
      </el-form-item>

      <!-- Price Input -->
      <el-form-item label="Price" prop="price">
        <el-input v-model="product.price" placeholder="Enter product price"></el-input>
      </el-form-item>

      <!-- Image Upload -->
      <el-form-item label="Image" prop="image">
        <el-upload
            ref="upload"
            class="upload-demo"
            :limit="1"
            :on-change="handleFileChange"
            :auto-upload="false"
            action="/api/upload"
        >
        <template #trigger>
          <el-button type="primary">Select file</el-button>
        </template>
        </el-upload>
        <div v-if="product.image">
          <img :src="product.image" alt="Uploaded Image" class="uploaded-image" />
        </div>
      </el-form-item>

      <!-- Description Input -->
      <el-form-item label="Description" prop="description">
        <el-input type="textarea" v-model="product.description" placeholder="Enter product description"></el-input>
      </el-form-item>

      <!-- Publish Button -->
      <el-form-item>
        <el-button type="primary" @click="submitProduct">Publish Product</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { publishProduct, upload } from '@/api';  // Import API methods

export default {
  data() {
    return {
      product: {
        name: "",  // Initially empty
        price: "", // Initially empty
        image: "", // Initially empty (image will be uploaded)
        description: "", // Initially empty
        owner: "admin",  // Default owner
      },
    };
  },
  methods: {
    handleFileChange(file) {
      const formData = new FormData();
      formData.append('image', file.raw);

      // Call the backend API to upload the image
      upload(formData)
          .then((result) => {
            if (result.data.code === 1) {
              // Assuming the URL of the image is returned in the response data
              this.product.image = result.data.data;  // Store the image URL
              this.$message({
                message: 'Image uploaded successfully!',
                type: 'success',
              });
            } else {
              this.$message({
                message: 'Failed to upload image',
                type: 'error',
              });
            }
          })
          .catch((error) => {
            console.error('Error uploading image:', error);
            this.$message({
              message: 'An error occurred while uploading the image',
              type: 'error',
            });
          });
    },

    // Submit the product data to the backend
    submitProduct() {
      // Check if all fields are filled
      if (!this.product.name || !this.product.price || !this.product.description || !this.product.image) {
        this.$message.error('Please fill in all required fields and upload an image.');
        return;
      }

      // Call the API to publish the product
      publishProduct(this.product)
          .then((result) => {
            if (result.data.code === 1) {
              this.$message({
                message: 'Product published successfully!',
                type: 'success',
              });
              this.$router.push('/');  // Redirect to home page after success
            } else {
              this.$message({
                message: 'Failed to publish product',
                type: 'error',
              });
            }
          })
          .catch((error) => {
            console.error('Error publishing product:', error);
            this.$message({
              message: 'An error occurred while publishing the product',
              type: 'error',
            });
          });
    },
  },
};
</script>

<style scoped>
.uploaded-image {
  width: 300px;
  height: 300px;
  object-fit: cover;  /* Ensure the image covers the area without distortion */
  border-radius: 8px;
  border: 1px solid #ddd;
  margin-top: 10px;
}
</style>
