<template>
  <div class="common-layout">
    <el-container>
      <!-- 侧边栏：产品展示部分 -->
      <el-aside width="45%" class="aside-container">
        <el-card>
          <div class="card-body">
            <img :src="product.image" alt="product image" style="max-width: 60%; height: auto;"/>
          </div>
          <p><strong>描述:</strong> {{ product.description }}</p>
        </el-card>
      </el-aside>

      <!-- 主体：商品详情部分 -->
      <el-main class="main-container">
        <p><strong>名称:</strong> {{ product.name }}</p>
        <p><strong>价格:</strong> ¥ {{ product.price }}</p>

        <p><strong>卖家:</strong> {{ seller.name }} </p>
        <p><strong>商品新旧程度:</strong> {{ conditions[product.condition] }}</p>
        <p><strong>售卖状态:</strong> {{ status[product.status] }}</p>
        <p><strong>地址:</strong> {{ addressMap[product.address] }}</p>

        <!-- 购买按钮 -->
        <el-button type="primary" @click="buyProduct">购买</el-button>
        <!-- 收藏按钮（星星图标） -->
        <el-button
            :type="this.product.isLiked ? 'danger' : 'success'"
            @click="toggleFavorite"
        >
          <el-icon>
            <Star />
          </el-icon>
        </el-button>

        <!-- 返回首页按钮 -->
        <el-button @click="goToHome">返回</el-button>
      </el-main>

      <!-- 新增：右侧栏：卖家的其他商品展示 -->
      <el-aside width="25%" class="related-products">
        <!-- 右侧栏：卖家的其他商品展示 -->
        <el-aside width="75%" class="related-products">
          <h3>卖家的其他商品</h3>
          <div v-for="item in sellerProducts" :key="item.id" class="product-card">
            <img :src="item.image" alt="商品图片" class="product-image"/>
            <p><strong>名称:</strong> {{ item.name }}</p>
            <p><strong>价格:</strong> ¥ {{ item.price }}</p>
            <el-button @click="viewProductDetail(item.id)">查看详情</el-button>
          </div>
        </el-aside>
      </el-aside>
    </el-container>
  </div>
</template>

<script>
import {getProductDetail, getSellerById, getProductList, toggleProductWantList, getWantList} from '@/api'; // Import API method
import {ElMessage} from 'element-plus'; // Import ElMessage for notifications
import {Star} from '@element-plus/icons-vue'; // Import Star icon for the favorite button

export default {
  components: {
    Star
  },
  data() {
    return {
      product: {}, // Store product details here
      seller: {}, // Store seller details here
      sellerProducts: [], // 存放卖家发布的商品
      userWantList: [], // 存放用户的收藏列表
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
      this.product.sellerId = sellerId;
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
            this.updateProductLikes(); // Update the product's like status
            this.findSeller(this.product.sellerId); // fetch seller details by seller ID of the product
            this.fetchSellerProducts(this.product.sellerId); // 获取卖家发布的商品
          })
          .catch(error => {
            console.error("Failed to fetch product details:", error);
            this.$message.error('Failed to fetch product details');
          });
    },
    // 获取卖家发布的商品
    fetchSellerProducts(sellerId) {
      getProductList()
          .then(response => {
            // Filter products by seller ID and not this product and only 3 products and status is 1
            this.sellerProducts = response.data.data.filter(product => product.sellerId === sellerId && product.id !== this.product.id && product.status === 1).slice(0, 3);
          })
          .catch(error => {
            console.error("Failed to fetch seller products:", error);
            this.$message.error('Failed to fetch seller products');
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

    updateProductLikes() {
      this.product.isLiked = this.userWantList.includes(this.product.id); // 如果商品 id 在收藏列表中，设置为已收藏
    },

    toggleFavorite() {
      const userId = JSON.parse(localStorage.getItem('user')).id;
      this.product.isLiked = !this.product.isLiked;

      toggleProductWantList(userId, this.product.id)
          .then((response) => {
            if (response && response.data && response.data.code === 1) {
              const message = this.product.isLiked ? '已收藏' : '已取消收藏';
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

    // 查看推荐商品详情
    viewProductDetail(productId) {
      this.$router.push('/products/' + productId); // 跳转到商品详情页
    },
    goToHome() {
      this.$router.push('/home'); // Navigate to the home page
    },
    async buyProduct() {
      try {
        const subject = this.product.name;             // Product name
        const traceNo = this.product.id;               // Product ID (order trace number)
        const totalAmount = this.product.price;        // Product price
        const sellerId = this.product.sellerId;        // Seller ID
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
  watch: {
    // Watch for changes in the route and fetch product details again
    '$route'() {
      this.fetchProductDetail(); // Re-fetch product details and update the page
      this.fetchUserWantList();  // Re-fetch user's favorite list
    }
  },
  created() {
    this.fetchProductDetail(); // Fetch product details when the component is created
    this.fetchUserWantList(); // 获取用户的收藏列表
  },
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

.related-products {
  padding-left: 20px;
}

.product-card {
  margin-bottom: 20px;
  padding: 10px;
  text-align: center;
}

.product-image {
  width: 100%;
  height: auto;
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