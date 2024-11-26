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
        />
        <el-col :span="1" class="user-options">
        </el-col>
        <el-button type="primary" @click="searchProducts">搜索</el-button>
      </el-col>
      <el-col :span="4" class="user-options">
        <el-button type="success" @click="logout">登出</el-button>
        <el-button @click="navigateChat">消息</el-button>
      </el-col>
    </el-row>

    <!-- Sorting Bar -->
    <el-row class="sorting-bar" type="flex" justify="start" align="middle" style="padding: 20px 0;">
      <!-- 综合排序 -->
      <el-col :span="3">
        <el-select v-model="sortType" placeholder="综合" style="width: 100%; min-width: 10px">
          <el-option v-for="item in sortOptions" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select>
      </el-col>

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
            <el-button class="buy-btn" type="primary" @click="buyProduct">购买</el-button>
            <el-button class="add-btn" type="success" @click="addToFavorites">
              <el-icon>
                <star/>
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
  getProductsListByTimeMonth
} from '@/api';
import {Star} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";

export default {
  components: {Star},
  data() {
    return {
      searchQuery: "",
      productList: [],
      currentPage: 1,
      totalCount: 10,
      pageSize: 8,
      background: true,
      // Sort options data
      sortType: '',
      timeFilter: '',
      priceSort: '',
      // 数据选项
      sortOptions: [
        {value: 'recent', label: '最近发布'},
        {value: 'nearby', label: '距离最近'},
        {value: 'credit', label: '信用排序'}
      ],
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
          this.totalCount = this.productList.length;
        } else {
          this.$message.error(response.data.message || "No products found.");
        }
      }).catch(error => {
        console.error('API call failed: ', error);
        this.$message.error('Failed to load products');
      });
    },

    handleCurrentChange(val) {
      this.currentPage = val;
    },

    getProductList() {
      getProductList().then(response => {
        if (response && response.data && response.data.code === 1) {
          this.productList = response.data.data;
          this.totalCount = this.productList.length;
        } else {
          this.$message.error(response.data.message || "No products available.");
        }
      }).catch(error => {
        console.error('API call failed: ', error);
        this.$message.error('Failed to load products');
      });
    },

    viewProductDetails(product) {
      this.$router.push({name: 'ProductDetail', params: {id: product.id}});
    },

    buyProduct() {
      ElMessage.success('已购买');
    },

    addToFavorites() {
      ElMessage.success('已添加购物车');
    }
  },
  created() {
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