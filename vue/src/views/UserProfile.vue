<template>
  <div class="user-profile">
    <el-container>
      <!-- 个人信息 -->
      <el-header class="header">
        <div class="avatar-wrapper">
          <el-avatar :src="user.picture" size="64"></el-avatar>
          <div class="user-info">
            <h2>{{ user.name }}</h2>
            <p>ID: {{ user.id }}</p>
          </div>
        </div>
        <el-button plain @click="editProfile">修改个人信息</el-button>
      </el-header>

      <el-container>
        <!-- 侧边选择栏 -->
        <el-aside width="200px">
          <el-scrollbar>
            <el-menu :default-active="activeIndex" @select="handleSelect">
              <el-menu-item index="cart">
                <el-icon><el-icon-shopping-cart /></el-icon>
                <span>购物车</span>
              </el-menu-item>
              <el-menu-item index="published">
                <el-icon><el-icon-check-circle /></el-icon>
                <span>已发布</span>
              </el-menu-item>
              <el-menu-item index="sales">
                <el-icon><el-icon-pie-chart /></el-icon>
                <span>出售记录</span>
              </el-menu-item>
              <el-menu-item index="purchases">
                <el-icon><el-icon-receipt /></el-icon>
                <span>购买记录</span>
              </el-menu-item>
            </el-menu>
          </el-scrollbar>
        </el-aside>

        <!-- 商品展示 -->
        <el-main>
          <div v-if="activeIndex === 'cart'">
            <h3>购物车</h3>
            <table>
              <thead>
              <tr>
                <th>商品名称</th>
                <th>价格</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="item in cartItems" :key="item.id">
                <td>{{ item.name }}</td>
                <td>¥{{ item.price }}</td>
                <td>
                  <button>编辑</button>
                  <button>删除</button>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
          <div v-else-if="activeIndex === 'sales'">
            <h3>出售记录</h3>
            <table>
              <thead>
              <tr>
                <th>商品名称</th>
                <th>价格</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="item in soldRecords" :key="item.id">
                <td>{{ item.name }}</td>
                <td>¥{{ item.price }}</td>
                <td>
                  <button>编辑</button>
                  <button>删除</button>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
          <div v-else-if="activeIndex === 'purchases'">
            <h3>购买记录</h3>
            <table>
              <thead>
              <tr>
                <th>商品名称</th>
                <th>价格</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="item in purchaseRecords" :key="item.id">
                <td>{{ item.name }}</td>
                <td>¥{{ item.price }}</td>
                <td>
                  <button>编辑</button>
                  <button>删除</button>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
          <div v-else-if="activeIndex === 'published'">
            <h3>已发布商品</h3>
            <table>
              <thead>
              <tr>
                <th>商品名称</th>
                <th>图片</th>
                <th>价格</th>
                <th>创建时间</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="item in publishedItems" :key="item.id">
                <td>{{ item.name }}</td>
                <td><img :src="item.image" alt="商品图片" class="image" /></td>
                <td>¥{{ item.price }}</td>
                <td>{{ item.createTime }}</td>
                <td>
                  <button>编辑</button>
                  <button>删除</button>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { ElIcon, ElIconShoppingCart, ElIconPieChart, ElIconReceipt, ElIconCheckCircle } from 'element-plus';
import 'element-plus/dist/index.css';
import { getPublishedProductBySellerId } from '@/api'; // 导入API方法

export default {
  name: 'SidebarMenu',
  components: {
    ElIcon,
    ElIconShoppingCart,
    ElIconPieChart,
    ElIconReceipt,
    ElIconCheckCircle
  },
  setup() {
    // user从localStorage中获取
    const user = ref(JSON.parse(localStorage.getItem('user')) || {});

    const activeIndex = ref('cart'); // 默认选中购物车
    const cartItems = ref([
      { id: 1, name: 'Product A', price: 100 },
      { id: 2, name: 'Product B', price: 200 }
    ]);
    const soldRecords = ref([
      { id: 1, name: 'Sold Item A', price: 150 },
      { id: 2, name: 'Sold Item B', price: 250 }
    ]);
    const purchaseRecords = ref([
      { id: 1, name: 'Bought Item A', price: 120 },
      { id: 2, name: 'Bought Item B', price: 220 }
    ]);
    const publishedItems = ref([]); // 动态获取已发布商品

    const getPublishedItems = () => {
      getPublishedProductBySellerId(user.value.id)
          .then(response => {
            publishedItems.value = response.data.data || [];
            alert(publishedItems.value);
          })
          .catch(error => {
            console.error('Failed to fetch published items:', error);
            ElMessage.error('Failed to fetch published items');
          });
    };

    const handleSelect = (key) => {
      activeIndex.value = key;
    };

    const editProfile = () => {
      ElMessageBox.prompt('请输入新用户名', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^.{1,50}$/,
        inputErrorMessage: '用户名不能为空或超长',
      })
          .then(({ value }) => {
            user.value.name = value;
            ElMessage.success('用户名修改成功');
          })
          .catch(() => {
            ElMessage.info('取消修改用户名');
          });
    };

    onMounted(() => {
      getPublishedItems(); // 加载已发布商品数据
    });

    return {
      user,
      activeIndex,
      cartItems,
      soldRecords,
      purchaseRecords,
      publishedItems,
      handleSelect,
      editProfile,
    };
  }
};
</script>

<style scoped>
/* 顶部用户信息样式 */
.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px;
  background-color: #f9fafb; /* 更柔和的背景色 */
  border-bottom: 1px solid #e0e0e0;
}

.avatar-wrapper {
  display: flex;
  align-items: center;
}

.user-info {
  margin-left: 15px;
}

.user-info h2 {
  margin: 0;
  font-size: 20px;
  color: #333;
}

.user-info p {
  margin: 5px 0 0;
  font-size: 14px;
  color: #666;
}

/* 侧边栏菜单样式 */
.el-aside {
  background-color: #fff;
  border-right: 1px solid #e0e0e0;
}

.el-menu {
  border: none;
}

.el-menu-item {
  display: flex;
  align-items: center;
  font-size: 14px;
}

.el-menu-item span {
  margin-left: 10px;
}

/* 商品展示区样式 */
.el-main {
  padding: 20px;
}

.el-card {
  margin-bottom: 20px;
  padding: 15px;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.el-card p {
  margin: 0;
  font-size: 14px;
  color: #333;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

thead {
  background-color: #f9fafb;
}

thead th {
  padding: 10px;
  text-align: left;
  font-size: 14px;
  color: #666;
  border-bottom: 1px solid #e0e0e0;
}

tbody tr {
  border-bottom: 1px solid #e0e0e0;
}

tbody td {
  padding: 10px;
  font-size: 14px;
  color: #333;
}

.image {
  width: 50px;
  height: 50px;
  object-fit: cover;
  border-radius: 4px;
}

/* 按钮样式 */
button {
  background-color: #409eff;
  border: none;
  padding: 5px 10px;
  color: #fff;
  font-size: 12px;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 10px;
}

button:hover {
  background-color: #66b1ff;
}

button:last-child {
  background-color: #f56c6c;
}

button:last-child:hover {
  background-color: #ff8585;
}
</style>
