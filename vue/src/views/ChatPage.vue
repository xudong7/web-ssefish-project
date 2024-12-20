<template>
  <div class="announcement-container">
    <!-- 顶部大图 -->
    <div class="announcement-header">
      <div class="header-content">
        <h1 class="header-title">{{ currentAnnouncement?.title || '公告' }}</h1>
        <div class="header-meta">
          <span class="meta-item">{{ formatDate(currentAnnouncement?.createTime) || '暂无日期' }}</span>
          <span class="meta-item">{{ currentAnnouncement?.views || 229 }} 次阅读</span>
          <span class="meta-item">{{ currentAnnouncement?.duration || 2 }} 分钟</span>
        </div>
      </div>
    </div>

    <!-- 主要内容区域 -->
    <div class="announcement-main">
      <!-- 左侧目录 -->
      <div class="catalog-wrapper">
        <div class="announcement-catalog">
          <div class="catalog-title">目录</div>
          <div class="catalog-items">
            <div 
              v-for="(announcement, index) in announcements" 
              :key="announcement.id"
              class="catalog-item"
              :class="{ active: index === activeAnnouncement }"
              @click="selectAnnouncement(index)"
            >
              {{ announcement.title }}
            </div>
          </div>
        </div>
      </div>

      <!-- 右侧内容 -->
      <div class="content-wrapper">
        <div class="announcement-content" v-html="currentAnnouncement?.content || '请选择一个公告查看详细内容'"></div>
      </div>
    </div>
  </div>
</template>

<script>
import { getAnnouncementList } from '@/api';

export default {
  data() {
    return {
      // 公告数据，包括发布时间
      announcements: [],
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
    getAllAnnouncements() {
      getAnnouncementList().then(response => {
        this.announcements = response.data.data;
      });
    },
  },
  created() {
    this.getAllAnnouncements();
  },
};
</script>

<style scoped>
.announcement-container {
  min-height: 100vh;
  background-color: #f5f7fa;
}

/* 顶部大图样式 */
.announcement-header {
  height: 400px;
  background: linear-gradient(135deg, #e6f3ff 0%, #409EFF 100%);  /* 从浅蓝到主题蓝的渐变 */
  display: flex;
  align-items: center;
  justify-content: center;
  color: #2c3e50;  /* 改为深色文字 */
  text-align: center;
  padding: 0 20px;
  position: relative;
  overflow: hidden;
}

/* 调整波浪效果 */
.announcement-header::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(45deg, rgba(255,255,255,0.2) 25%, transparent 25%, transparent 50%, rgba(255,255,255,0.2) 50%, rgba(255,255,255,0.2) 75%, transparent 75%, transparent);
  background-size: 30px 30px;
  opacity: 0.3;
}

.header-content {
  max-width: 800px;
  position: relative;  /* 确保内容在波浪效果之上 */
  z-index: 1;
}

.header-title {
  font-size: 36px;
  font-weight: 600;
  margin-bottom: 20px;
  text-shadow: none;  /* 移除文字阴影 */
  color: #2c3e50;
}

.header-meta {
  display: flex;
  justify-content: center;
  gap: 20px;
  font-size: 14px;
}

.meta-item {
  opacity: 1;
  background: rgba(255, 255, 255, 0.4);
  padding: 4px 12px;
  border-radius: 15px;
  color: #2c3e50;
}

/* 主要内容区域样式 */
.announcement-main {
  display: flex;
  max-width: 1200px;
  margin: -60px auto 0;
  padding: 0 20px;
  position: relative;
  gap: 20px;
}

/* 左侧目录样式 */
.catalog-wrapper {
  width: 280px;
}

.announcement-catalog {
  background-color: white;
  border-radius: 8px;
  padding: 20px;
  position: sticky;
  top: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.catalog-title {
  font-size: 18px;
  font-weight: 600;
  color: #2c3e50;
  margin-bottom: 16px;
  padding-bottom: 12px;
  border-bottom: 1px solid #e6e6e6;
}

.catalog-items {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.catalog-item {
  font-size: 14px;
  color: #606266;
  cursor: pointer;
  padding: 10px;
  border-radius: 4px;
  transition: all 0.3s;
}

.catalog-item:hover {
  background-color: #ecf5ff;
  color: #409EFF;
}

.catalog-item.active {
  background-color: #409EFF;
  color: white;
}

/* 右侧内容样式 */
.content-wrapper {
  flex: 1;
}

.announcement-content {
  background-color: white;
  border-radius: 8px;
  padding: 40px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  font-size: 15px;
  line-height: 1.8;
  color: #606266;
}

/* 响应式设计 */
@media (max-width: 1200px) {
  .announcement-main {
    padding: 0 10px;
  }
}

@media (max-width: 768px) {
  .announcement-header {
    height: 300px;
  }

  .header-title {
    font-size: 28px;
  }

  .announcement-main {
    flex-direction: column;
    margin-top: -40px;
  }

  .catalog-wrapper {
    width: 100%;
  }

  .announcement-catalog {
    position: static;
  }

  .announcement-content {
    padding: 20px;
  }
}
</style>