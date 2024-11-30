<template>
  <div class="user-profile">
    <el-container>
      <!-- 个人信息 -->
      <el-header class="header">
        <div class="avatar-wrapper">
          <el-avatar :src="user.picture" size="64"></el-avatar>
          <div class="user-info">
            <h2>{{ user.name }}</h2>
            <p>ID: {{ user.id }} email: {{ user.email }}</p>
          </div>
        </div>
        <el-col :span="4" class="user-options">
          <!-- 登出 -->
          <el-button type="success" @click="logout" style="width: 60px; background-color: #4CAF50; color: white; border-color: #4CAF50;">登出</el-button>
          <el-button plain @click="showEditDialog">修改头像</el-button>
        </el-col>
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
                <th>图片</th>
                <th>价格</th>
                <th>情况</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="item in paginatedCartItems" :key="item.id">
                <td>{{ item.name }}</td>
                <td><img :src="item.image" alt="商品图片" class="image" /></td>
                <td>¥{{ item.price }}</td>
                <td>{{ statusMap[item.status] }}</td>
                <td>
                  <el-button type="success">购买</el-button>
                </td>
              </tr>
              </tbody>
            </table>
            <!-- 分页 --><br>
            <el-pagination
                @current-change="handleCartPageChange"
                :current-page="cartCurrentPage"
                :page-size="pageSize"
                :total="cartItems.length"
                layout="total, prev, pager, next, jumper"
                :background="true"
            ></el-pagination>
          </div>
          <div v-else-if="activeIndex === 'sales'">
            <h3>出售记录</h3>
            <table>
              <thead>
              <tr>
                <th>商品名称</th>
                <th>图片</th>
                <th>价格</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="item in paginatedSalesRecords" :key="item.id">
                <td>{{ item.name }}</td>
                <td><img :src="item.image" alt="商品图片" class="image" /></td>
                <td>¥{{ item.price }}</td>
              </tr>
              </tbody>
            </table>
            <!-- 分页 --><br>
            <el-pagination
                @current-change="handleSalesPageChange"
                :current-page="salesCurrentPage"
                :page-size="pageSize"
                :total="soldRecords.length"
                layout="total, prev, pager, next, jumper"
                :background="true"
            ></el-pagination>
          </div>
          <div v-else-if="activeIndex === 'purchases'">
            <h3>购买记录</h3>
            <table>
              <thead>
              <tr>
                <th>商品名称</th>
                <th>图片</th>
                <th>价格</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="item in paginatedPurchaseRecords" :key="item.id">
                <td>{{ item.name }}</td>
                <td><img :src="item.image" alt="商品图片" class="image" /></td>
                <td>¥{{ item.price }}</td>
              </tr>
              </tbody>
            </table>
            <!-- 分页 --><br>
            <el-pagination
                @current-change="handlePurchasePageChange"
                :current-page="purchaseCurrentPage"
                :page-size="pageSize"
                :total="purchaseRecords.length"
                layout="total, prev, pager, next, jumper"
                :background="true"
            ></el-pagination>
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
                <th>状态</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="item in paginatedPublishedItems" :key="item.id">
                <td>{{ item.name }}</td>
                <td><img :src="item.image" alt="商品图片" class="image" /></td>
                <td>¥{{ item.price }}</td>
                <td>{{ item.createTime }}</td>
                <td>{{ statusMap[item.status] }}</td>
                <td>
                  <el-button type="danger">删除</el-button>
                </td>
              </tr>
              </tbody>
            </table>
            <!-- 分页 --><br>
            <el-pagination
                @current-change="handlePublishedPageChange"
                :current-page="publishedCurrentPage"
                :page-size="pageSize"
                :total="publishedItems.length"
                layout="total, prev, pager, next, jumper"
                :background="true"
            ></el-pagination>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>

  <!-- 修改用户的对话框 -->
  <el-dialog title="修改头像" v-model="editDialogVisible" width="50%" @close="editDialogClosed" :close-on-click-modal="false">
    <el-form :model="editUserForm" ref="editUserFormRef" label-width="70px">
      <el-form-item label="上传头像" prop="picture">
        <el-upload
            :before-upload="handleBeforeUpload"
            :on-change="handleAvatarChange"
            :limit="1"
            :auto-upload="false"
            action="/api/upload"
        >
          <el-button type="primary">上传文件</el-button>
        </el-upload>
        <div v-if="editUserForm.picture" class="avatar-preview">
          <el-avatar :src="editUserForm.picture" size="64"></el-avatar>
        </div>
      </el-form-item>
    </el-form>
    <span class="dialog-footer">
      <el-button @click="editDialogVisible = false">取消</el-button>
      <el-button type="primary" @click="editUser">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import {computed, onMounted, ref} from 'vue';
import 'element-plus/dist/index.css';
import {getProductList, getPublishedProductBySellerId, getWantListProduct, updateUser, upload} from '@/api';
import router from "@/router";
import {ElMessage} from "element-plus"; // 导入API方法

export default {
  name: 'SidebarMenu',
  setup() {
    //修改部分
    const logout = () => {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      localStorage.removeItem('userRole');
      router.push({name: 'Login'});
    };

    const statusMap = {
      '1': '未售出',
      '2': '已售出'
    };

    const user = ref(JSON.parse(localStorage.getItem('user')) || {});
    const activeIndex = ref('cart');
    const cartItems = ref([]);
    const soldRecords = ref([]);
    const purchaseRecords = ref([]);
    const publishedItems = ref([]);
    const editDialogVisible = ref(false);
    const pageSize = 5;
    const cartCurrentPage = ref(1);
    const salesCurrentPage = ref(1);
    const purchaseCurrentPage = ref(1);
    const publishedCurrentPage = ref(1);

    const getPublishedItems = () => {
      getPublishedProductBySellerId(user.value.id)
          .then(response => {
            publishedItems.value = response.data.data || [];
          })
          .catch(error => {
            console.error('Failed to fetch published items:', error);
          });
    };

    const getCartItems = () => {
      getWantListProduct(user.value.id)
          .then(response => {
            cartItems.value = response.data.data || [];
          })
          .catch(error => {
            console.error('Failed to fetch cart items:', error);
          });
    }

    const getSoldRecords = () => {
      getProductList()
          .then(response => {
            soldRecords.value = response.data.data || [];
            // 只要已售出且sellerId为当前用户的商品
            soldRecords.value = soldRecords.value.filter(item => item.status === 2 && item.sellerId === user.value.id);
          })
          .catch(error => {
            console.error('Failed to fetch sold records:', error);
          });
    }

    const getPurchaseRecords = () => {
      getProductList()
          .then(response => {
            purchaseRecords.value = response.data.data || [];
            // 只要已售出且sellerId为当前用户的商品
            purchaseRecords.value = purchaseRecords.value.filter(item => item.status === 2 && item.buyerId === user.value.id);
          })
          .catch(error => {
            console.error('Failed to fetch purchase records:', error);
          });
    }

    const handleSelect = (key) => {
      activeIndex.value = key;
    };

    const showEditDialog = () => {
      editDialogVisible.value = true; // 显示对话框
    };

    const editUserForm = ref({
      picture: user.value.picture || '',
    });

    const handleBeforeUpload = (file) => {
      // 限制图片大小和格式
      const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJpgOrPng) {
        ElMessage.error('上传图片只能是 JPG 或 PNG 格式!');
        return false;
      }
      if (!isLt2M) {
        ElMessage.error('上传图片大小不能超过 2MB!');
        return false;
      }
      return true;
    };

    const handleAvatarChange = (file) => {
      const formData = new FormData();
      formData.append('image', file.raw);

      // 调用后端 API 上传文件
      upload(formData)
          .then((response) => {
            if (response.data.code === 1) {
              editUserForm.value.picture = response.data.data; // 返回头像 URL
            } else {
              ElMessage.error(response.data.message || '头像上传失败');
            }
          })
          .catch((error) => {
            console.error('Error uploading avatar:', error);
            ElMessage.error('上传失败，请稍后重试');
          });
    };

    const editUser = () => {
      user.value.picture = editUserForm.value.picture;

      updateUser(user.value)
          .then((response) => {
            if (response.data.code === 1) {
              ElMessage.success('头像更新成功');
              localStorage.setItem('user', JSON.stringify(user.value));
              editDialogVisible.value = false; // 关闭对话框
            } else {
              ElMessage.error(response.data.message || '头像更新失败');
            }
          })
          .catch((error) => {
            console.error('Error updating user:', error);
            ElMessage.error('更新失败，请稍后重试');
          });
    };

    // 分页处理函数
    const handleCartPageChange = (page) => {
      cartCurrentPage.value = page;
    };
    const handleSalesPageChange = (page) => {
      salesCurrentPage.value = page;
    };
    const handlePurchasePageChange = (page) => {
      purchaseCurrentPage.value = page;
    };
    const handlePublishedPageChange = (page) => {
      publishedCurrentPage.value = page;
    };

    // 获取每一页的数据
    const paginatedCartItems = computed(() => {
      const start = (cartCurrentPage.value - 1) * pageSize;
      return cartItems.value.slice(start, start + pageSize);
    });

    const paginatedSalesRecords = computed(() => {
      const start = (salesCurrentPage.value - 1) * pageSize;
      return soldRecords.value.slice(start, start + pageSize);
    });

    const paginatedPurchaseRecords = computed(() => {
      const start = (purchaseCurrentPage.value - 1) * pageSize;
      return purchaseRecords.value.slice(start, start + pageSize);
    });

    const paginatedPublishedItems = computed(() => {
      const start = (publishedCurrentPage.value - 1) * pageSize;
      return publishedItems.value.slice(start, start + pageSize);
    });

    onMounted(() => {
      getPublishedItems();
      getCartItems();
      getSoldRecords();
      getPurchaseRecords();
    });

    return {
      logout, //修改
      user,
      statusMap,
      showEditDialog,
      handleSelect,
      activeIndex,
      cartItems,
      soldRecords,
      purchaseRecords,
      publishedItems,
      cartCurrentPage,
      salesCurrentPage,
      purchaseCurrentPage,
      publishedCurrentPage,
      pageSize,
      handleCartPageChange,
      handleSalesPageChange,
      handlePurchasePageChange,
      handlePublishedPageChange,
      paginatedCartItems,
      paginatedSalesRecords,
      paginatedPurchaseRecords,
      paginatedPublishedItems,
      editDialogVisible,
      editUserForm,
      handleAvatarChange,
      editUser,
      handleBeforeUpload,
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

.dialog-footer {
  display: flex;
  justify-content: center; /* 居中 */
  gap: 20px; /* 设置按钮之间的间距 */
  padding: 10px 0; /* 给底部区域添加适当的内边距 */
}

</style>