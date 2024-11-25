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
          <el-avatar :src="conversation.avatar"/>
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
          <el-avatar :src="currentConversation?.avatar"/>
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
            {content: "Hi, is the item available?", isMine: false},
            {content: "Yes, it's still available.", isMine: true},
          ],
        },
        {
          name: "Buyer B",
          avatar: "/buyer-b-avatar.png",
          messages: [
            {content: "Can you provide more details about the item?", isMine: false},
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
  border-radius: 12px; /* 圆角 */
  overflow: hidden; /* 禁止溢出 */
}

/* 左侧历史会话列表 */
.chat-sidebar {
  width: 25%;
  background-color: #f7f7f7;
  border-right: 1px solid #ddd;
  display: flex;
  flex-direction: column;
  border-top-left-radius: 12px;
  border-bottom-left-radius: 12px;
  box-shadow: 2px 0px 10px rgba(0, 0, 0, 0.1); /* 加阴影 */
}

.chat-history-title {
  padding: 18px 20px;
  font-weight: bold;
  font-size: 18px;
  background-color: #4fa3f7;
  color: white;
  text-align: center;
  border-top-left-radius: 12px;
}

.chat-history-list {
  flex: 1;
  padding: 15px;
  overflow-y: auto;
}

.conversation-item {
  display: flex;
  align-items: center;
  padding: 12px;
  cursor: pointer;
  border-radius: 10px;
  margin-bottom: 8px;
  transition: background-color 0.3s, transform 0.2s;
}

.conversation-item:hover {
  background-color: #e6f7ff;
  transform: scale(1.02);
}

.conversation-item.active {
  background-color: #4fa3f7;
  color: white;
}

.conversation-info {
  margin-left: 15px;
  flex: 1;
}

.conversation-name {
  font-weight: bold;
  font-size: 16px;
}

.conversation-preview {
  font-size: 14px;
  color: #888;
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
  border-top-right-radius: 12px;
  border-bottom-right-radius: 12px;
}

.chat-header {
  background-color: #4fa3f7;
  color: white;
  padding: 12px 20px;
  display: flex;
  align-items: center;
  border-top-right-radius: 12px;
}

.chat-participants {
  display: flex;
  align-items: center;
}

.chat-name {
  margin-left: 10px;
  font-size: 18px;
}

.chat-messages-container {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.chat-messages {
  display: flex;
  flex-direction: column;
}

.message {
  max-width: 70%;
  padding: 12px;
  border-radius: 10px;
  margin: 8px 0;
  word-wrap: break-word;
}

.message.mine {
  align-self: flex-end;
  background-color: #4fa3f7;
  color: white;
  border-radius: 12px 12px 0 12px;
}

.message.partner {
  align-self: flex-start;
  background-color: #f0f0f0;
  border-radius: 12px 12px 12px 0;
}

/* 底部输入栏 */
.chat-footer {
  display: flex;
  align-items: center;
  padding: 12px 20px;
  background-color: #f7f7f7;
  border-bottom-right-radius: 12px;
}

.chat-input {
  flex: 1;
  margin-right: 15px;
  border-radius: 20px;
  padding: 10px;
}

.el-button {
  border-radius: 50px; /* 圆形按钮 */
}
</style>
