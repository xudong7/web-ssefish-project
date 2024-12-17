# 软工闲鱼
- [English](README.md)
- [Chinese](README/README_zh.md)

## 介绍
本项目灵感来源于我们学院的“软工集市”项目，针对学生之间二手交易的需求，我们开发了“软工闲鱼”来帮助学生进行这些交易。
---

## 如何运行

### 1. 运行后端服务器
```
找到 BackApplication.java 并运行。

请确保您已更改 application.properties 中的设置

以及项目中使用的其他设置。

项目中已添加 TODO 标签。
```

### 2. 运行前端服务器
#### 运行以下命令：
```
cd vue
npm install
npm run serve
```

### 3. 启动 Natapp
```
打开 Natapp 目录并单击 start.bat。
记住Natapp的URL（例如http://xxxx.natapp.cc）。
替换application.properties中的URL。
```

### 4. 打开浏览器
```
下载支付宝沙盒应用进行支付。
```
使用您自己的沙盒账户和密码。
支付宝账号：whaphg2436@sandbox.com
支付宝密码：111111

---

## 技术栈
- **前端**: Vue 3, Element-Plus, Axios, Vuex, Vue-Router, Webpack
- **后端**: Spring Boot, MyBatis, MySQL, Lombok
- **其他**: Natapp, Nginx, ...

---

## 端口配置

```
后端服务器：8080
前端服务器：3000
如果要更改，记得在后端的 WebConfig.java 中更新跨域设置。
```
