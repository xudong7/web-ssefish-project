<template>
  <div class="common-layout">
    <el-container>
      <!-- 侧边栏：产品展示部分 -->
      <el-aside width="700px" class="aside-container">
        <el-card>
          <div class="card-body">
            <img :src="product.image" alt="product image" style="max-width: 60%; height: auto;"/>
          </div>
        </el-card>
      </el-aside>

      <!-- 主体：商品详情部分 -->
      <el-main class="main-container">
        <p><strong>名称:</strong> {{product.name}}</p>
        <p><strong>价格:</strong> ¥ {{ product.price }}</p>
        <p><strong>描述:</strong> {{ product.description }}</p>
        <p><strong>卖家:</strong> {{ seller.name }} </p>
        <p><strong>商品新旧程度:</strong> {{ conditions[product.condition] }}</p>
        <p><strong>售卖状态:</strong> {{ status[product.status] }}</p>
        <p><strong>地址:</strong> {{ addressMap[product.address] }}</p>

        <!-- 购买按钮 -->
        <el-button type="primary" @click="buyProduct">购买</el-button>
        <!-- 收藏按钮（星星图标） -->
        <el-button type="success" @click="addToFavorites">
          <el-icon><star /></el-icon>
        </el-button>

        <!-- 返回首页按钮 -->
        <el-button @click="goToHome">返回</el-button>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import {getProductDetail, getSellerById} from '@/api'; // Import API method
import { ElMessage } from 'element-plus'; // Import ElMessage for notifications
import { Star } from '@element-plus/icons-vue'; // Import Star icon for the favorite button

export default {
  components: {
    Star
  },
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
      conditions: {
        1: '二手',
        2: '全新'
      },
      status: {
        1: '待售',
        2: '已售'
      }
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
    },
    buyProduct() {
      // Add logic for the buy button (for now, just a placeholder message)
      ElMessage.success('已购买');
    },
    addToFavorites() {
      // Add logic for adding to favorites (for now, just a placeholder message)
      ElMessage.success('已添加购物车');
    }
  },
  created() {
    this.fetchProductDetail(); // Fetch product details when the component is created
  }
};
</script>

<style scoped>
/* 为 aside 和 main 部分添加边框 */
.aside-container {
  border-right: 1px solid #e0e0e0;
  padding-right: 20px;
}

.main-container {
  border-left: 1px solid #e0e0e0;
  padding-left: 20px;
}

/* 侧边栏商品展示 */
.card-body {
  text-align: center;
  padding: 20px;
}

/* 按钮样式 */
el-button {
  margin-top: 20px;
  margin-right: 10px;
}

el-button[type="primary"] {
  background-color: #409eff;
  color: white;
}

el-button[type="success"] {
  background-color: #67C23A;
  color: white;
}
</style>
