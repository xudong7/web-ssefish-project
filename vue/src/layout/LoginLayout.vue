<script setup>

</script>

<template>
  <div class="login-layout">
    <el-main class="login-main">
      <div class="login-content">
        <router-view></router-view>
      </div>
    </el-main>
<!--    <el-footer class="login-footer">-->
<!--      <p>© 2024 二手物品交易平台 | All Rights Reserved</p>-->
<!--    </el-footer>-->
  </div>
</template>

<style scoped>
/* 主布局容器 */
.login-layout {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background: linear-gradient(to bottom, #a9ccf1, #f4f4f4); /* 渐变背景 */
}



</style>
