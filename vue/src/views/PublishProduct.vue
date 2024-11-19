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
            action="customUpload"
            :show-file-list="false"
            :before-upload="beforeAvatarUpload"
        >
<!--        <img v-if="product.image" :src="product.image" class="avatar" />-->
<!--        <i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
        <el-button
            v-if="!product.image"
            type="primary"
            style="width: auto; height: auto"
        >
          Upload
        </el-button>
        </el-upload>
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
import {publishProduct, upload} from '@/api';  // Import the publishProduct method from API

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
    //文件上传相关
    async customUpload({ image }) {
      try {
        const res = await upload(image); // 调用封装的 API
        this.product.image = res.data; // 使用返回的 URL
        this.$message.success("上传成功！");
      } catch (error) {
        this.$message.error("上传失败！");
      }
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    // Submit the product data to the backend
    submitProduct() {
      // Check if all fields are filled
      // if (!this.product.name || !this.product.price || !this.product.description || !this.product.image) {
      //   this.$message.error('Please fill in all required fields and upload an image.');
      //   return;
      // }

      // Call the API to publish the product
      publishProduct(this.product)
          .then((result) => {
            if (result.data.code === 1) {
              this.$message({
                message: 'Product published successfully',
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
/* Style for the upload button to make it like other input fields */
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.avatar {
  width: 100px;
  height: 100px;
  display: block;
}

.upload-btn {
  width: 100%; /* Make the button take the full width like the input fields */
  max-width: 100%; /* Ensure it doesn't exceed container width */
}

/* Style for the uploaded image preview */
.uploaded-image {
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 8px;
  border: 1px solid #ddd;
  margin-top: 10px;
}

/* Style for the upload button */
.upload-btn .el-button {
  width: 100%;
  padding: 10px;
  font-size: 12px;
  text-align: center;
}
</style>
