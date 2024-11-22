<template>
  <div class="admin-container">
    <!-- Header -->
    <div class="header">
      <div class="logo">管理</div>
      <div class="user-info">
        <button @click="logout">登出</button>
      </div>
    </div>

    <div class="main-content">
      <!-- Sidebar -->
      <div class="sidebar">
        <ul>
          <li @click="navigateTo('product')" :class="{ active: currentTab === 'product' }">商品管理</li>
          <li @click="navigateTo('user')" :class="{ active: currentTab === 'user' }">用户管理</li>
          <li @click="navigateTo('transaction')" :class="{ active: currentTab === 'transaction' }">交易记录</li>
        </ul>
      </div>

      <!-- Main Content -->
      <div class="content">
        <div v-if="currentTab === 'product'">
          <h3>商品管理</h3>
          <table>
            <thead>
            <tr>
              <th>商品名称</th>
              <th>图片</th>
              <th>价格</th>
              <th>地址</th>
              <th>创建时间</th>
              <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="product in pagedProducts" :key="product.id">
              <td>{{ product.name }}</td>
              <td><img :src="product.image" alt="商品图片" class="image"/></td>
              <td>{{ product.price }}</td>
              <td>{{ product.address }}</td>
              <td>{{ product.createTime }}</td>
              <td>
                <button @click="editProduct(product)">编辑</button>
                <button @click="deleteProduct(product.id)">删除</button>
              </td>
            </tr>
            </tbody>
          </table>

          <br>

          <!-- Pagination for products -->
          <el-pagination
              background
              layout="prev, pager, next"
              :total="products.length"
              :page-size="pageSize"
              :current-page="currentPage"
              @current-change="handlePageChange"
          />
        </div>
        <div v-if="currentTab === 'user'">
          <h3>用户管理</h3>
          <table>
            <thead>
            <tr>
              <th>用户id</th>
              <th>邮箱</th>
              <th>头像</th>
              <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="user in pagedUsers" :key="user.id">
              <td>{{ user.id }}</td>
              <td>{{ user.email }}</td>
              <td><img :src="user.picture" alt="头像" /></td>
              <td>
                <button @click="editUser(user)">编辑</button>
                <button @click="deleteUser(user.id)">删除</button>
              </td>
            </tr>
            </tbody>
          </table>

          <br>

          <!-- Pagination for users -->
          <el-pagination
              background
              layout="prev, pager, next"
              :total="users.length"
              :page-size="pageSize"
              :current-page="currentPage"
              @current-change="handlePageChange"
          />
        </div>
        <div v-if="currentTab === 'transaction'">
          <h3>交易记录</h3>
          <table>
            <thead>
            <tr>
              <th>交易ID</th>
              <th>卖家ID</th>
              <th>买家ID</th>
              <th>商品ID</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="trade in pagedTrades" :key="trade.id">
              <td>{{ trade.id }}</td>
              <td>{{ trade.sellerId }}</td>
              <td>{{ trade.buyerId }}</td>
              <td>{{ trade.productId }}</td>
            </tr>
            </tbody>
          </table>

          <br>

          <!-- Pagination for trades -->
          <el-pagination
              background
              layout="prev, pager, next"
              :total="trades.length"
              :page-size="pageSize"
              :current-page="currentPage"
              @current-change="handlePageChange"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ElPagination } from 'element-plus';
import { getProductList, deleteProductById } from '../api';

export default {
  name: 'AdminPage',
  components: {
    ElPagination
  },
  data() {
    return {
      currentTab: 'product', // 当前选中的 tab
      pageSize: 5, // 每页显示的商品数/用户数/交易记录数
      currentPage: 1, // 当前页数
      products: [], // 商品数据
      users: [
        { id: 1, email: 'user1@example.com', picture: 'vue/src/assets/logo.png' },
        { id: 2, email: 'user2@example.com', picture: 'vue/src/assets/logo.png' },
        { id: 3, email: 'user3@example.com', picture: 'vue/src/assets/logo.png' },
        { id: 4, email: 'user4@example.com', picture: 'vue/src/assets/logo.png' },
        { id: 5, email: 'user5@example.com', picture: 'vue/src/assets/logo.png' }
      ],
      trades: [
        { id: 1, buyerId: 1234, sellerId: 123, productId: 1 },
        { id: 2, buyerId: 5678, sellerId: 567, productId: 2 },
        { id: 3, buyerId: 2345, sellerId: 123, productId: 3 },
        { id: 4, buyerId: 6789, sellerId: 456, productId: 4 },
        { id: 5, buyerId: 3456, sellerId: 789, productId: 5 },
        { id: 6, buyerId: 5670, sellerId: 890, productId: 6 },
        { id: 7, buyerId: 2347, sellerId: 901, productId: 7 },
        { id: 8, buyerId: 6780, sellerId: 101, productId: 8 },
        { id: 9, buyerId: 1230, sellerId: 102, productId: 9 },
        { id: 10, buyerId: 5675, sellerId: 103, productId: 10 }
      ],
    };
  },
  computed: {
    // 分页后的商品数据
    pagedProducts() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.products.slice(start, end);
    },
    // 分页后的用户数据
    pagedUsers() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.users.slice(start, end);
    },
    // 分页后的交易记录数据
    pagedTrades() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.trades.slice(start, end);
    }
  },
  methods: {
    getAllProducts() {
      getProductList().then(response => {
        this.products = response.data.data;
      });
    },
    logout() {
      this.$router.push({ name: 'Login' }); // Navigate to the Login page
    },
    navigateTo(tab) {
      this.currentTab = tab;
    },
    editProduct(product) {
      const updatedProduct = {...product, price: product.price + 100}; // 假设我们只是增加价格作为编辑示例
      const index = this.products.findIndex(p => p.id === product.id);
      if (index !== -1) {
        this.products.splice(index, 1, updatedProduct);
      }
      alert(`商品已编辑: ${updatedProduct.name}`);
    },
    deleteProduct(id) {
      deleteProductById(id).then(() => {
        alert(`商品已删除`);
      });
    },
    editUser(user) {
      alert(`编辑用户 ${user.email}`);
    },
    deleteUser(id) {
      const index = this.users.findIndex(user => user.id === id);
      if (index !== -1) {
        this.users.splice(index, 1);
      }
      alert(`用户已删除`);
    },
    handlePageChange(page) {
      this.currentPage = page;
    }
  },
  created() {
    this.getAllProducts();
  }
};
</script>


<style scoped>
.admin-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  font-family: 'Arial', sans-serif;
  background-color: #f4f6f9;
}

.header {
  display: flex;
  justify-content: space-between;
  padding: 15px 30px;
  background-color: lightskyblue;
  color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.header .logo {
  font-size: 30px;
  font-weight: bold;
}

.header .user-info button {
  background: #007BFF;
  color: white;
  border: none;
  padding: 8px 18px;
  cursor: pointer;
  font-size: 14px;
  border-radius: 4px;
  transition: background 0.3s;
}

.header .user-info button:hover {
  background: #c0392b;
}

.main-content {
  display: flex;
  flex-grow: 1;
  background-color: #ffffff;
}

.sidebar {
  width: 200px;
  background-color: lightskyblue;
  color: white;
  padding-top: 30px;
  box-shadow: 2px 0 5px rgb(51.2, 126.4, 204);
}

.sidebar ul {
  list-style: none;
  padding-left: 0;
}

.sidebar li {
  padding: 12px;
  cursor: pointer;
  text-align: center;
  font-size: 17px;
  transition: background-color 0.3s, color 0.3s;
}

.sidebar li:hover,
.sidebar li.active {
  background-color: #2980b9;
  color: white;
}

.content {
  flex-grow: 1;
  padding: 30px;
  overflow-y: auto;
}

h3 {
  font-size: 20px;
  color: #333;
  margin-bottom: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
  background-color: #fff;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

table th, table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
  font-size: 14px;
}

table th {
  background-color: #ecf0f1;
  color: #34495e;
}

button {
  padding: 5px 10px;
  border: none;
  cursor: pointer;
  font-size: 14px;
}

button:hover {
  background-color: #d5d5d5;
}

.image {
  max-width: 100px;
  height: auto;
}
</style>