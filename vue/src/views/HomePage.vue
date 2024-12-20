<template>
  <div>
    <!-- Header Section -->
    <el-row class="header" type="flex" justify="space-between" align="middle">
      <el-col :span="8">
        <h1 class="site-title">校园二手闲置物品交易平台</h1>
      </el-col>
      <el-col :span="8" class="search-col" style="display: flex; justify-content: space-between; align-items: center;">
        <el-input
            v-model="searchQuery"
            placeholder="搜索商品"
            suffix-icon="el-icon-search"
            clearable
            class="search-input"
        />
        <el-col :span="1" class="user-options">
        </el-col>
        <el-button type="primary" @click="searchProducts" class="search-btn">搜索</el-button>
      </el-col>
      <el-col :span="4" class="user-options">
        <el-button type="success" @click="logout" class="logout-btn">登出</el-button>
        <el-button @click="navigateChat">公告</el-button>
      </el-col>
    </el-row>

    <!-- Sorting Bar -->
    <el-row class="sorting-bar" type="flex" justify="start" align="middle" style="padding: 20px 0;">
      <!-- 发布时间排序 -->
      <el-col :span="3">
        <el-select v-model="timeFilter" placeholder="发布时间" style="width: 100%; min-width: 10px"
                   @change="handleTimeSortChange">
          <el-option v-for="item in timeOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </el-col>

      <!-- 价格排序 -->
      <el-col :span="3">
        <el-select
            v-model="priceSort"
            placeholder="价格"
            style="width: 100%; min-width: 120px"
            @change="handlePriceSortChange"
        >
          <el-option
              v-for="item in priceOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-col>

      <!-- Reset Button -->
      <el-col :span="3">
        <el-button type="warning" @click="resetFilters">重置</el-button>
      </el-col>
    </el-row>

    <!-- Product Grid Section -->
    <el-row gutter="20" type="flex" justify="start">
      <el-col :span="6" v-for="product in paginatedProducts" :key="product.id">
        <el-card class="product-card" shadow="hover">
          <template #header>
            <span class="product-name">{{ product.name }}</span>
          </template>
          <div class="card-body">
            <img :src="product.image" alt="product image" class="product-image"/>
            <p class="product-price">¥ {{ product.price }}</p>
            <el-button class="view-details-btn" type="primary" @click="viewProductDetails(product)">查看详情</el-button>
            <el-button class="buy-btn" type="primary" @click="buyProduct(product)">购买</el-button>
            <!-- Favorite button with dynamic state -->
            <el-button
                :type="product.isLiked ? 'danger' : 'success'"
                @click="toggleFavorite(product)"
            >
              <el-icon>
                <Star />
              </el-icon>
            </el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- Pagination Section -->
    <el-pagination
        @current-change="handleCurrentChange"
        :background="background"
        :current-page="currentPage"
        :page-size="pageSize"
        layout="total, prev, pager, next, jumper"
        :total="totalCount">
    </el-pagination>
  </div>
</template>

<script>
import {
  getProductList,
  searchProduct,
  getProductsListByPriceFromH,
  getProductsListByPriceFromL,
  getProductsListByTime,
  getProductsListByTimeWeek,
  getProductsListByTimeMonth, toggleProductWantList, getWantList
} from '@/api';
import {Star} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
import { v4 as uuidv4 } from 'uuid'; // 引入 uuid 库

export default {
  components: {Star},
  data() {
    return {
      searchQuery: "",
      productList: [],
      userWantList: [], // 用于存储用户的收藏列表
      currentPage: 1,
      totalCount: 10,
      pageSize: 8,
      background: true,
      // Sort options data
      sortType: '',
      timeFilter: '',
      priceSort: '',
      timeOptions: [
        {value: 'd', label: '一天内'},
        {value: 'w', label: '一星期内'},
        {value: 'm', label: '一月内'}
      ],
      priceOptions: [
        {value: 'high', label: '价格从高到低'},
        {value: 'low', label: '价格从低到高'},
      ]
    };
  },
  computed: {
    paginatedProducts() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.productList.slice(start, end);
    },
  },
  methods: {
    handleTimeSortChange(value) {
      console.log("选择的值:", value);

      const apiMap = {
        d: getProductsListByTime,
        w: getProductsListByTimeWeek,
        m: getProductsListByTimeMonth,
        default: getProductList
      };

      const apiFunction = apiMap[value] || apiMap.default;

      apiFunction()
          .then(response => {
            if (response && response.data && response.data.code === 1) {
              this.productList = response.data.data;
              // only add product which status == 1
              this.productList = this.productList.filter(product => product.status === 1);
              this.totalCount = this.productList.length;
            } else {
              this.$message.error(response.data.message || "Unknown error occurred");
            }
          })
          .catch(error => {
            console.error('API call failed: ', error);
            this.$message.error('Failed to load products');
          });
    },

    handlePriceSortChange(value) {
      console.log("选择的值:", value);

      const apiMap = {
        high: getProductsListByPriceFromH,
        low: getProductsListByPriceFromL,
        default: getProductList
      };

      const apiFunction = apiMap[value] || apiMap.default;

      apiFunction()
          .then(response => {
            if (response && response.data && response.data.code === 1) {
              this.productList = response.data.data;
              // only add product which status == 1
              this.productList = this.productList.filter(product => product.status === 1);
              this.totalCount = this.productList.length;
            } else {
              this.$message.error(response.data.message || "Unknown error occurred");
            }
          })
          .catch(error => {
            console.error('API call failed: ', error);
            this.$message.error('Failed to load products');
          });
    },

    logout() {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      localStorage.removeItem('userRole');
      this.$router.push({name: 'Login'});
    },

    navigateChat() {
      this.$router.push({name: 'Chat'});
    },

    searchProducts() {
      searchProduct(this.searchQuery).then(response => {
        if (response && response.data && response.data.code === 1) {
          this.productList = response.data.data;
          // only add product which status == 1
          this.productList = this.productList.filter(product => product.status === 1);
          this.totalCount = this.productList.length;
        } else {
          this.$message.error(response.data.message || "No products found.");
        }
      }).catch(error => {
        console.error('API call failed: ', error);
        this.$message.error('Failed to load products');
      });
    },

    // 获取用户的收藏列表
    fetchUserWantList() {
      const userId = JSON.parse(localStorage.getItem('user')).id; // 从本地存储中获取用户 ID
      getWantList(userId)
          .then((response) => {
            if (response && response.data && response.data.code === 1) {
              this.userWantList = response.data.data; // 保存收藏列表
              this.updateProductLikes(); // 更新商品的收藏状态
            } else {
              this.$message.error('无法加载收藏列表');
            }
          })
          .catch((error) => {
            console.error('API 请求失败：', error);
            this.$message.error('加载收藏列表失败');
          });
    },

    // 更新商品的收藏状态
    updateProductLikes() {
      this.productList.forEach((product) => {
        product.isLiked = this.userWantList.includes(product.id); // 如果商品 id 在收藏列表中，设置为已收藏
      });
    },

    // 切换商品的收藏状态
    toggleFavorite(product) {
      const userId = JSON.parse(localStorage.getItem('user')).id;
      product.isLiked = !product.isLiked; // 切换收藏状态

      toggleProductWantList(userId, product.id)
          .then((response) => {
            if (response && response.data && response.data.code === 1) {
              const message = product.isLiked ? '已收藏' : '已取消收藏';
              ElMessage.success(message);
            } else {
              ElMessage.error(response.data.message || '操作失败');
            }
          })
          .catch((error) => {
            console.error('API 请求失败：', error);
            ElMessage.error('操作失败，请稍后重试');
          });
    },

    handleCurrentChange(val) {
      this.currentPage = val;
    },

    getProductList() {
      getProductList().then(response => {
        if (response && response.data && response.data.code === 1) {
          const newProductList = response.data.data
          this.productList = newProductList;

          // only add product which status == 1
          this.productList = newProductList.filter(product => product.status === 1);
          this.updateProductLikes(); // 在商品加载完成后更新收藏状态
          this.totalCount = this.productList.length;
        } else {
          this.$message.error(response.data.message || "No products available.");
        }
      }).catch(error => {
        console.error('API call failed: ', error);
        this.$message.error('Failed to load products');
      });
    },

    resetFilters() {
      this.sortType = '';  // Reset to '最近发布'
      this.timeFilter = '';     // Reset to '一天内'
      this.priceSort = '';   // Reset to '价格从高到低'
      this.getProductList();     // Refresh the product list with default values
    },

    viewProductDetails(product) {
      this.$router.push({name: 'ProductDetail', params: {id: product.id}});
    },

    async buyProduct(product) {
      try {
        const subject = product.name;             // Product name
        const traceNo = product.id + '-' + uuidv4();               // Product ID (order trace number)
        const totalAmount = product.price;        // Product price
        const sellerId = product.sellerId;        // Seller ID
        const buyerId = JSON.parse(localStorage.getItem('user')).id; // Buyer ID
        // body = sellerId,buyerId
        const body = sellerId + ',' + buyerId;

        // Construct the payment URL with the necessary query parameters
        const paymentUrl = `http://127.0.0.1:8080/alipay/pay?subject=${encodeURIComponent(subject)}&traceNo=${traceNo}&totalAmount=${totalAmount}&body=${body}`;

        // window.open(paymentUrl, '_blank'); // Open the payment URL in a new tab
        window.open(paymentUrl, '_self'); // Open the payment URL in the same tab

        // Display a success message indicating the purchase has started
        ElMessage.success(`已开始购买: ${subject}`);
      } catch (error) {
        // Handle potential errors (like issues with API calls)
        console.error('Error during purchase: ', error);
        ElMessage.error('购买失败，请稍后再试');
      }
    },
  },
  created() {
    this.fetchUserWantList();
    this.getProductList();
  }
};
</script>

<style scoped>
/* Header styles */
.header {
  background-color: lightskyblue;
  padding: 15px;
}

.site-title {
  color: white;
  font-size: 24px;
  font-weight: bold;
  margin: 0;
}

.search-col {
  text-align: right;
}

.logout-btn {
  background-color: #ffcccc !important;
  border-color: #ffcccc !important;
  color: grey !important;
}

.search-input {
  width: 100%;
  max-width: 400px;
  margin-right: 10px;
}

.search-input :deep(.el-input__wrapper) {
  background-color: rgba(255, 255, 255, 0.9);
  border-radius: 20px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

.search-input :deep(.el-input__wrapper):hover,
.search-input :deep(.el-input__wrapper):focus {
  background-color: #ffffff;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.search-input :deep(.el-input__inner) {
  height: 40px;
  font-size: 16px;
  color: #333;
  padding: 0 20px;
}

.search-btn {
  height: 40px;
  border-radius: 20px;
  padding: 0 25px;
  font-size: 16px;
  background: #4CAF50;
  border-color: #4CAF50;
  transition: all 0.3s ease;
}

.search-btn:hover {
  background: #45a049;
  border-color: #45a049;
  transform: translateY(-1px);
  box-shadow: 0 4px 12px rgba(76, 175, 80, 0.3);
}

.user-options {
  text-align: right;
}

/* Product Card Styles */
.product-card {
  border-radius: 10px;
  overflow: hidden;
  background-color: #fff;
  transition: transform 0.3s ease;
}

.product-card:hover {
  transform: translateY(-5px);
}

.product-name {
  font-size: 18px;
  font-weight: bold;
  color: #333;
  padding: 10px;
  display: block;
  text-align: center;
}

.card-body {
  text-align: center;
  padding: 15px;
}

.product-image {
  max-width: 100%;
  height: auto;
  border-radius: 8px;
  margin-bottom: 15px;
}

.product-price {
  font-size: 16px;
  color: #ff5722;
  margin: 10px 0;
}

.view-details-btn {
  background-color: #007BFF;
  border: none;
  color: white;
  padding: 10px 20px;
  font-size: 14px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.view-details-btn:hover {
  background-color: #0056b3;
}

/* Pagination styles */
.el-pagination {
  margin-top: 20px;
  text-align: center;
}

.product-image {
  width: 100%; /* Make the image take up the full width of its container */
  height: 200px; /* Set a fixed height to ensure consistency across images */
  object-fit: contain; /* Ensure the image maintains its aspect ratio while fitting within the container */
  border-radius: 8px;
  margin-bottom: 15px;
}
</style>