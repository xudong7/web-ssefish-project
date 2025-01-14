# 软工闲鱼 - 校园二手交易平台 🎓

[![Vue](https://img.shields.io/badge/Vue-3.2.13-brightgreen.svg)](https://vuejs.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-latest-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

## 📖 简介

软工闲鱼(School of Software Engineering Fish)是一个面向在校学生的二手交易平台。该项目仿照我们学院的"软工集市"项目，旨在为学生提供一个安全、便捷的二手物品交易环境。

### 主要功能
- 🛍️ 二手商品发布与浏览
- 💰 支付宝沙箱支付集成
- 👤 用户信息管理
- 📱 响应式界面设计
<!-- - 💬 即时聊天与商品咨询 -->

## ✨ 特性

- 💡 **现代化技术栈**: 基于 Vue 3 + Spring Boot 构建
- 📦 **安全支付**: 集成支付宝沙箱支付功能
- 🛡 **用户验证**: 完整的用户认证和授权系统
- ⚡️ **高性能**: 快速的前后端分离架构
- 🎨 **精美界面**: 基于 Element Plus 的 UI 设计
<!-- - 🔐 **数据安全**: 完善的数据加密和安全机制 -->

## 🚀 快速开始

### 1. 运行后端服务
```
找到 BackApplication.java 并运行
请确保已修改 application.properties 中的配置
项目中已添加 TODO 标签以标识需要修改的地方
```

### 2. 运行前端服务
```
cd vue
npm install
npm run serve
```

### 3. 启动内网穿透
```
打开 Natapp 目录并运行 start.bat
记录生成的 URL (例如: http://xxxx.natapp.cc)
在 application.properties 中更新该 URL
```

### 4. 配置支付功能
```
下载支付宝沙箱 App 进行支付测试
```
测试账号信息:  
- 支付宝账号: whaphg2436@sandbox.com  
- 支付宝密码: 111111

## 🔧 技术栈

### 前端
- **Vue 3**: 渐进式 JavaScript 框架
- **Element Plus**: 基于 Vue 3 的组件库
- **Axios**: HTTP 请求工具
- **Vuex**: 状态管理模式
- **Vue Router**: 官方路由管理器
- **Webpack**: 静态模块打包工具

### 后端
- **Spring Boot**: 简化 Spring 应用开发框架
- **MyBatis**: 优秀的持久层框架
- **MySQL**: 关系型数据库
- **Lombok**: Java 工具类库

### 其他工具
- **Natapp**: 内网穿透工具
- **Nginx**: 高性能的 HTTP 和反向代理服务器

## ⚙️ 系统配置

### 端口配置
```
后端服务: 8080
前端服务: 3000
如需修改端口,请同步更新后端 WebConfig.java 中的跨域设置
```

### 开发环境要求
- Node.js >= 14
- JDK >= 1.8
- Maven >= 3.6
- MySQL >= 5.7

## 📝 许可证

本项目采用 [MIT License](LICENSE) 开源许可证

## 👥 贡献指南

我们非常欢迎各种形式的贡献:
- 🐛 提交问题和建议
- 📝 改进文档
- 🔀 提交代码
- 🌟 分享项目

### 贡献步骤
1. Fork 本仓库
2. 创建特性分支
3. 提交改动
4. 推送到分支
5. 创建 Pull Request

## 📞 联系我们

如有任何问题或建议,欢迎通过以下方式联系我们:
- 提交 Issue
- 发送邮件至: [xudong7@mail2.sysu.edu.cn](xudong7@mail2.sysu.edu.cn)
