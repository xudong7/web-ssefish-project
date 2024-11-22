<template>
  <div class="chat-container">
    <!-- 左侧历史会话列表 -->
    <div class="chat-sidebar">
      <div class="chat-history-title">会话</div>
      <el-scrollbar class="chat-history-list">
        <div
            v-for="(conversation, index) in conversations"
            :key="index"
            :class="['conversation-item', activeConversation === index ? 'active' : '']"
            @click="selectConversation(index)"
        >
          <el-avatar :src="conversation.avatar" />
          <div class="conversation-info">
            <div class="conversation-name">{{ conversation.name }}</div>
            <div class="conversation-preview">
              {{ conversation.messages[conversation.messages.length - 1]?.content || 'No messages yet' }}
            </div>
          </div>
        </div>
      </el-scrollbar>
    </div>

    <!-- 右侧聊天窗口 -->
    <div class="chat-main">
      <el-header class="chat-header">
        <div class="chat-participants">
          <el-avatar :src="currentConversation?.avatar" />
          <span class="chat-name">{{ currentConversation?.name || 'Select a conversation' }}</span>
        </div>
      </el-header>

      <el-main class="chat-messages-container">
        <div class="chat-messages">
          <div
              v-for="(message, index) in currentConversation?.messages || []"
              :key="index"
              :class="['message', message.isMine ? 'mine' : 'partner']"
          >
            <div class="message-content">{{ message.content }}</div>
          </div>
        </div>
      </el-main>

      <el-footer class="chat-footer">
        <el-input
            v-model="newMessage"
            placeholder="Type a message..."
            class="chat-input"
            @keyup.enter="sendMessage"
            :disabled="!currentConversation"
        ></el-input>
        <el-button type="primary" @click="sendMessage" :disabled="!currentConversation">发送</el-button>
      </el-footer>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 历史会话数据
      conversations: [
        {
          name: "Seller A",
          avatar: "/seller-a-avatar.png",
          messages: [
            { content: "Hi, is the item available?", isMine: false },
            { content: "Yes, it's still available.", isMine: true },
          ],
        },
        {
          name: "Buyer B",
          avatar: "/buyer-b-avatar.png",
          messages: [
            { content: "Can you provide more details about the item?", isMine: false },
          ],
        },
      ],
      activeConversation: null, // 当前激活的会话索引
      newMessage: "", // 当前输入的新消息
    };
  },
  computed: {
    currentConversation() {
      return this.conversations[this.activeConversation];
    },
  },
  methods: {
    selectConversation(index) {
      this.activeConversation = index; // 切换当前会话
    },
    sendMessage() {
      if (!this.newMessage.trim() || this.activeConversation === null) return;

      // 添加新消息到当前会话
      this.currentConversation.messages.push({
        content: this.newMessage,
        isMine: true,
      });

      this.newMessage = ""; // 清空输入框

      // 模拟对方的响应
      setTimeout(() => {
        this.currentConversation.messages.push({
          content: "Thanks for reaching out! Let me check.",
          isMine: false,
        });
      }, 1000);
    },
  },
};
</script>

<style scoped>

.chat-container {
  display: flex;
  height: 100vh;
  border-radius: 10px; /* 圆角 */
  overflow: hidden; /* 禁止溢出 */
}

/* 左侧历史会话列表 */
.chat-sidebar {
  width: 25%;
  background-color: #f5f5f5;
  border-right: 1px solid #ddd;
  display: flex;
  flex-direction: column;
  border-top-left-radius: 10px; /* 左上角圆角 */
  border-bottom-left-radius: 10px; /* 左下角圆角 */
}

.chat-history-title {
  padding: 10px 15px;
  font-weight: bold;
  font-size: 16px;
  background-color: lightskyblue;
  color: white;
  text-align: center;
  border-top-left-radius: 10px; /* 左上角圆角 */
}

.chat-history-list {
  flex: 1;
  padding: 10px;
  overflow-y: auto; /* 允许历史会话列表滚动 */
}

.conversation-item {
  display: flex;
  align-items: center;
  padding: 10px;
  cursor: pointer;
  border-radius: 8px; /* 圆角 */
  margin-bottom: 5px;
  transition: background-color 0.2s;
}

.conversation-item:hover {
  background-color: #e6f7ff;
}

.conversation-item.active {
  background-color: lightskyblue;
  color: white;
}

.conversation-info {
  margin-left: 10px;
  flex: 1;
}

.conversation-name {
  font-weight: bold;
}

.conversation-preview {
  font-size: 14px;
  color: gray;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

/* 右侧聊天窗口 */
.chat-main {
  flex: 1;
  display: flex;
  flex-direction: column;
  background-color: white;
  border-top-right-radius: 10px; /* 右上角圆角 */
  border-bottom-right-radius: 10px; /* 右下角圆角 */
}

/* 顶部聊天栏 */
.chat-header {
  background-color: lightskyblue;
  color: white;
  padding: 10px;
  display: flex;
  align-items: center;
  border-top-right-radius: 10px; /* 右上角圆角 */
}

/* 消息内容区 */
.chat-messages-container {
  flex: 1;
  padding: 10px;
  overflow-y: auto;
}

.chat-messages {
  display: flex;
  flex-direction: column;
}

.message {
  max-width: 60%;
  padding: 10px;
  border-radius: 8px; /* 圆角 */
  margin: 5px 0;
  word-wrap: break-word;
}

.message.mine {
  align-self: flex-end;
  background-color: lightskyblue;
  color: white;
  border-radius: 12px 12px 0 12px; /* 消息右侧圆角 */
}

.message.partner {
  align-self: flex-start;
  background-color: #f0f0f0;
  border-radius: 12px 12px 12px 0; /* 消息左侧圆角 */
}

/* 底部输入栏 */
.chat-footer {
  display: flex;
  align-items: center;
  padding: 10px;
  background-color: #f5f5f5;
  border-bottom-right-radius: 10px; /* 右下角圆角 */
}

.chat-input {
  flex: 1;
  margin-right: 10px;
  border-radius: 20px; /* 输入框圆角 */
}
</style>
