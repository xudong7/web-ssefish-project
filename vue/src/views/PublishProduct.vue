<template>
  <div class="form-container">
    <h2 class="center-title">发布闲置</h2>
    <el-form :model="product" ref="form" label-width="120px">
      <!-- Product Name Input -->
      <el-form-item label="商品名称" prop="name">
        <el-input v-model="product.name" placeholder="Enter product name" class="short-input"></el-input>
      </el-form-item>

      <!-- Price Input -->
      <el-form-item label="价格" prop="price">
        <el-input v-model="product.price" placeholder="Enter product price" class="short-input"></el-input>
      </el-form-item>

      <!--新增 Location 输入框-->
      <el-form-item label="地址" prop="location">
        <el-select v-model="product.address" placeholder="Select a province" class="short-input">
          <el-option v-for="province in provinces"
                     :key="province.code"
                     :label="province.name"
                     :value="province.code">
          </el-option>
        </el-select>
      </el-form-item>

      <!-- Image Upload -->
      <el-form-item label="图片" prop="image">
        <el-upload
            ref="upload"
            class="upload-demo"
            :limit="1"
            :on-change="handleFileChange"
            :auto-upload="false"
            action="/api/upload"
        >
          <template #trigger>
            <el-button type="primary">选择文件</el-button>
          </template>
        </el-upload>
        <div v-if="product.image">
          <img :src="product.image" alt="Uploaded Image" class="uploaded-image" />
        </div>
      </el-form-item>

      <!--新增 condition 输入框-->
      <el-form-item label="新旧程度" prop="condition">
        <el-select v-model="product.condition" placeholder="Select a level" class="short-input">
          <el-option v-for="level in levels"
                     :key="level.code"
                     :label="level.name"
                     :value="level.code">
          </el-option>
        </el-select>
      </el-form-item>

      <!-- Description Input -->
      <el-form-item label="描述" prop="description">
        <el-input type="textarea" v-model="product.description" placeholder="Enter product description"></el-input>
      </el-form-item>

      <!-- Publish Button -->
      <el-form-item>
        <el-button type="primary" @click="submitProduct" class="button-container">发布商品</el-button>
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
        image: "",
        description: "",
        name: "",
        price: "",
        address: "",
        buyerId: "",
        sellerId: 1,
        condition:"",
        status: 1,
        createTime: "",
        likes: 0,
      },
      provinces: [
        { code: '1', name: '广州南校区' },
        { code: '2', name: '广州东校区' },
        { code: '3', name: '广州北校区' },
        { code: '4', name: '深圳校区' },
        { code: '5', name: '珠海校区' }
      ],
      levels: [
        { code: '1', name: '二手' },
        { code: '2', name: '全新' },
      ],
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

              // Clear the selected file and reset the image preview
              this.$refs.upload.clearFiles();
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

      // Set the seller ID to the current user's ID
      // this.product.sellerId = this.$store.state.user.id;
      this.product.sellerId = JSON.parse(localStorage.getItem('user')).id;

      // Call the API to publish the product
      publishProduct(this.product)
          .then((result) => {
            if (result.data.code === 1) {
              this.$message({
                message: 'Product published successfully!',
                type: 'success',
              });
              this.$router.push('/home');  // Redirect to home page after success
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
.form-container {
  display: flex;
  flex-direction: column;
  align-items: center; /* 居中所有内容 */
  justify-content: flex-start; /* 垂直开始对齐内容 */
  min-height: 100vh; /* 使容器至少占满视口高度 */
  margin-top: -10px; /* 向上调整 */
}

.center-title {
  margin-bottom: 20px; /* 底部间距 */
  font-size: 24px; /* 字体大小 */
  font-weight: bold; /* 加粗 */
}

.short-input {
  width: 300px; /* 设置输入框的宽度 */
  margin: 0; /* 移除默认边距 */
}

.upload-demo {
  max-width: 100%; /* 限制图标和文本的最大宽度 */
  max-height: 100%; /* 限制图标和文本的最大高度 */
  display: flex;
  align-items: center; /* 垂直居中 */
  justify-content: center; /* 水平居中 */
  background-color: transparent; /* 设置背景透明以去掉边框效果 */
}

.uploaded-image {
  width: 300px;
  height: 300px;
  object-fit: cover; /* Ensure the image covers the area without distortion */
  border-radius: 8px;
  border: 1px solid #ddd;
  margin-top: 10px;
}

/* 居中按钮 */
.button-container {
  text-align: center; /* 居中按钮 */
  margin-top: 20px; /* 顶部间距 */
}
</style>