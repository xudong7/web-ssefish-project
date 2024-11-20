<template>
  <div class="user-profile">
    <!-- Header Section -->
    <el-header class="header">
      <div class="avatar-wrapper">
        <el-avatar :src="user.avatar" size="64"></el-avatar>
        <div class="user-info">
          <h2>{{ user.nickname }}</h2>
          <p>ID: {{ user.id }}</p>
        </div>
      </div>
      <el-button plain @click="editProfile">修改个人信息</el-button>
    </el-header>

    <!-- Main Content Section -->
    <el-main>
      <el-row :gutter="20">
        <!-- Recently Posted Items -->
        <el-col :span="12">
          <el-card header="刚刚发布">
            <el-list>
              <el-list-item v-for="item in recentlyPosted" :key="item.id">
                <span>{{ item.name }}</span>
              </el-list-item>
            </el-list>
          </el-card>
        </el-col>

        <!-- Removed Items -->
        <el-col :span="12">
          <el-card header="已经下架">
            <el-list>
              <el-list-item v-for="item in removedItems" :key="item.id">
                <span>{{ item.name }}</span>
              </el-list-item>
            </el-list>
          </el-card>
        </el-col>
      </el-row>

      <el-row :gutter="20" v-if="showMoreSections">
        <!-- My Cart -->
        <el-col :span="12">
          <el-card header="我的购物车">
            <el-list>
              <el-list-item v-for="item in cartItems" :key="item.id">
                <span>{{ item.name }}</span>
              </el-list-item>
            </el-list>
          </el-card>
        </el-col>

        <!-- Sold Records -->
        <el-col :span="12">
          <el-card header="出售记录">
            <el-list>
              <el-list-item v-for="item in soldRecords" :key="item.id">
                <span>{{ item.name }}</span>
              </el-list-item>
            </el-list>
          </el-card>
        </el-col>
      </el-row>
    </el-main>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus'

export default {
  setup() {
    const user = ref({
      avatar: 'path/to/avatar.jpg',
      nickname: '用户名',
      id: '123456',
      // 其他用户信息
    });

    const recentlyPosted = ref([
      { id: 1, name: '二手手机' },
      { id: 2, name: '二手电脑' },
      // 其他刚刚发布的物品
    ]);

    const removedItems = ref([
      { id: 1, name: '旧书' },
      { id: 2, name: '旧椅子' },
      // 其他已经下架的物品
    ]);

    const cartItems = ref([
      { id: 1, name: '二手耳机' },
      { id: 2, name: '二手键盘' },
      // 其他购物车中的物品
    ]);

    const soldRecords = ref([
      { id: 1, name: '二手平板' },
      { id: 2, name: '二手相机' },
      // 其他出售记录
    ]);

    const showMoreSections = ref(true); // 控制是否显示更多部分（购物车和出售记录）


    const editProfile = () => {
      ElMessageBox.prompt('Please input your e-mail', 'Tip', {
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
      recentlyPosted,
      removedItems,
      cartItems,
      soldRecords,
      showMoreSections,
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