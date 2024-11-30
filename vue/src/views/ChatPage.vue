<template>
  <div class="announcement-container">
    <!-- 左侧公告列表 -->
    <div class="announcement-sidebar">
      <div class="announcement-history-title">公告列表</div>
      <el-scrollbar class="announcement-history-list">
        <div
            v-for="(announcement, index) in announcements"
            :key="index"
            :class="['announcement-item', activeAnnouncement === index ? 'active' : '']"
            @click="selectAnnouncement(index)"
        >
          <div class="announcement-info">
            <div class="announcement-title">{{ announcement.title }}</div>
            <div class="announcement-time">
              {{ formatDate(announcement.publishedAt) }}
            </div>
          </div>
        </div>
      </el-scrollbar>
    </div>

    <!-- 右侧公告详情 -->
    <div class="announcement-main">
      <el-header class="announcement-header">
        <span class="announcement-name">{{ currentAnnouncement?.title || '请选择公告' }}</span>
      </el-header>

      <el-main class="announcement-content-container">
        <div class="announcement-content">
          <div v-html="currentAnnouncement?.content || ''"></div>
        </div>
      </el-main>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 公告数据，包括发布时间
      announcements: [
        {
          title: "公告 1",
          content: "这是公告 1 的内容，详细内容将在点击后显示。",
          publishedAt: "2024-11-29T10:00:00",  // 公告发布时间
        },
        {
          title: "公告 2",
          content: "这是公告 2 的内容，详细内容将在点击后显示。",
          publishedAt: "2024-11-28T09:00:00",  // 公告发布时间
        },
        {
          title: "公告 3",
          content: "这是公告 3 的内容，详细内容将在点击后显示。",
          publishedAt: "2024-11-27T08:00:00",  // 公告发布时间
        },
      ],
      activeAnnouncement: null, // 当前选中的公告索引
    };
  },
  computed: {
    currentAnnouncement() {
      return this.announcements[this.activeAnnouncement];
    },
  },
  methods: {
    selectAnnouncement(index) {
      this.activeAnnouncement = index; // 切换当前选中的公告
    },
    // 格式化公告发布时间
    formatDate(dateString) {
      const date = new Date(dateString);
      const options = {year: 'numeric', month: 'short', day: 'numeric', hour: '2-digit', minute: '2-digit'};
      return date.toLocaleString('zh-CN', options);  // 返回中文格式的日期时间
    },
  },
};
</script>

<style scoped>
.announcement-container {
  display: flex;
  height: 100vh;
  border-radius: 12px; /* 圆角 */
  overflow: hidden; /* 禁止溢出 */
}

/* 左侧公告列表 */
.announcement-sidebar {
  width: 15%;
  background-color: #f7f7f7;
  border-right: 1px solid #ddd;
  display: flex;
  flex-direction: column;
  border-top-left-radius: 12px;
  border-bottom-left-radius: 12px;
  box-shadow: 2px 0px 10px rgba(0, 0, 0, 0.1); /* 加阴影 */
}

.announcement-history-title {
  padding: 18px 20px;
  font-weight: bold;
  font-size: 18px;
  background-color: #4fa3f7;
  color: white;
  text-align: center;
  border-top-left-radius: 12px;
}

.announcement-history-list {
  flex: 1;
  padding: 15px;
  overflow-y: auto;
}

.announcement-item {
  display: flex;
  align-items: center;
  padding: 12px;
  cursor: pointer;
  border-radius: 10px;
  margin-bottom: 8px;
  transition: background-color 0.3s, transform 0.2s;
}

.announcement-item:hover {
  background-color: #e6f7ff;
  transform: scale(1.02);
}

.announcement-item.active {
  background-color: #4fa3f7;
  color: white;
}

.announcement-info {
  margin-left: 15px;
  flex: 1;
}

.announcement-title {
  font-weight: bold;
  font-size: 16px;
}

.announcement-time {
  font-size: 12px;
  color: #888;
  margin-top: 5px;
}

/* 右侧公告详情 */
.announcement-main {
  flex: 1;
  display: flex;
  flex-direction: column;
  background-color: white;
  border-top-right-radius: 12px;
  border-bottom-right-radius: 12px;
}

.announcement-header {
  background-color: #4fa3f7;
  color: white;
  padding: 12px 20px;
  display: flex;
  align-items: center;
  border-top-right-radius: 12px;
}

.announcement-name {
  font-size: 18px;
}

.announcement-content-container {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.announcement-content {
  font-size: 16px;
  color: #333;
  line-height: 1.6;
}
</style>