<template>
  <div class="user-profile">
    <el-container>
      <!-- 个人信息 -->
      <el-header class="header">
        <div class="avatar-wrapper">
          <el-avatar :src="user.img" size="64"></el-avatar>
          <div class="user-info">
            <h2>{{ user.name }}</h2>
            <p>ID: {{ user.id }}</p>
          </div>
        </div>
        <el-button plain @click="editProfile">修改个人信息</el-button>
      </el-header>

      <br>
      <!-- 侧边选择栏 -->
      <el-container>
        <el-aside width="200px">
          <el-scrollbar>
            <el-menu :default-active="activeIndex" @select="handleSelect">
              <el-menu-item index="cart">
                <el-icon><el-icon-shopping-cart /></el-icon>
                <span>购物车</span>
              </el-menu-item>
              <el-menu-item index="sales">
                <el-icon><el-icon-pie-chart /></el-icon>
                <span>出售记录</span>
              </el-menu-item>
              <el-menu-item index="purchases">
                <el-icon><el-icon-receipt /></el-icon>
                <span>购买记录</span>
              </el-menu-item>
              <el-menu-item index="just-off-shelf">
                <el-icon><el-icon-remove /></el-icon>
                <span>已下架</span>
              </el-menu-item>
              <el-menu-item index="published">
                <el-icon><el-icon-check-circle /></el-icon>
                <span>已发布</span>
              </el-menu-item>
            </el-menu>
          </el-scrollbar>
        </el-aside>

        <!-- 商品展示 -->
        <el-main>
          <div v-if="activeIndex === 'cart'">
            <h3>购物车</h3>
            <el-card v-for="item in cartItems" :key="item.id">
              <p>{{ item.name }} - ${{ item.price }}</p>
            </el-card>
          </div>
          <div v-else-if="activeIndex === 'sales'">
            <h3>出售记录</h3>
            <el-card v-for="item in soldRecords" :key="item.id">
              <p>{{ item.name }} - Sold for ${{ item.price }}</p>
            </el-card>
          </div>
          <div v-else-if="activeIndex === 'purchases'">
            <h3>购买记录</h3>
            <el-card v-for="item in purchaseRecords" :key="item.id">
              <p>{{ item.name }} - Purchased for ${{ item.price }}</p>
            </el-card>
          </div>
          <div v-else-if="activeIndex === 'just-off-shelf'">
            <h3>刚刚下架</h3>
            <el-card v-for="item in removedItems" :key="item.id">
              <p>{{ item.name }} - Now off shelf</p>
            </el-card>
          </div>
          <div v-else-if="activeIndex === 'published'">
            <h3>已经发布</h3>
            <el-card v-for="item in publishedItems" :key="item.id">
              <p>{{ item.name }} - ${{ item.price }}</p>
            </el-card>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus'
import { ElIcon, ElIconShoppingCart, ElIconPieChart, ElIconReceipt, ElIconRemove, ElIconCheckCircle } from 'element-plus';
import 'element-plus/dist/index.css';

export default {
  name: 'SidebarMenu',
  components: {
    ElIcon,
    ElIconShoppingCart,
    ElIconPieChart,
    ElIconReceipt,
    ElIconRemove,
    ElIconCheckCircle
  },

  setup() {
    const user = ref({
      img: 'path/to/avatar.jpg',
      name: '用户名',
      id: '123456',
      // 其他用户信息
    });


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
    const removedItems = ref([
      { id: 1, name: 'Off Shelf Item A', price: 110 },
      { id: 2, name: 'Off Shelf Item B', price: 210 }
    ]);
    const publishedItems = ref([
      { id: 1, name: 'Published Item A', price: 130 },
      { id: 2, name: 'Published Item B', price: 230 }
    ]);

    const handleSelect = (key, keyPath) => {
      console.log(`Selected menu item: ${key}, Key path: ${keyPath}`);
      activeIndex.value = key;
    }

    const editProfile = () => {
      ElMessageBox.prompt('New name', 'Tip', {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        inputPattern:
            /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        inputErrorMessage: 'Invalid Email',
      })
          .then(({ value }) => {
            ElMessage({
              type: 'success',
              message: `Your email is:${value}`,
            })
          })
          .catch(() => {
            ElMessage({
              type: 'info',
              message: 'Input canceled',
            })
          })
    }

    // 模拟从API或Vuex获取用户数据
    onMounted(() => {
      // 通常这里你会从API或Vuex获取用户数据并赋值给user
      // 例如：user.value = fetchedUserProfile;
    });

    return {
      user,
      activeIndex,
      cartItems,
      soldRecords,
      purchaseRecords,
      removedItems,
      publishedItems,
      handleSelect,
      editProfile,
    };
  },
};
</script>

<style scoped>
.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px;
  background-color: #f5f7fa;
}

.avatar-wrapper {
  display: flex;
  align-items: center;
}

.user-info {
  margin-left: 20px;
}

.user-info h2 {
  margin: 0;
  font-size: 24px;
}

.user-info p {
  margin: 5px 0 0;
  font-size: 16px;
  color: #8c939d;
}

.el-card {
  margin-bottom: 20px;
}
</style>