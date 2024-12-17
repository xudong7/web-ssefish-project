# ssefish
[English](#) | [Chinese](#)

## Introduction
This project is inspired by the "Software Engineering Market" project from our college, and addresses the need for second-hand transactions among students. We developed "Software Engineering Second-hand Fish" to help students with these transactions.

---

## How to run 

### 1. Run the Backend Server
```
Find the BackApplication.java and run it.
Please make sure you have changed settings in application.properties
and other settings used in the project.
TODO tags have been added in the project.
```

### 2. Run the Frontend Server 
#### Run the following commands:
```
cd vue
npm install
npm run serve
```

### 3. Start Natapp 
```
Open the Natapp directory and click start.bat.
Remember Natapp's URL (e.g., http://xxxx.natapp.cc).
Replace the URL in application.properties.
```

### 4. Open the Browser 
```
Download the Alipay sandbox app to make payments.
```
Use your own sandbox account and password.  
Alipay account: whaphg2436@sandbox.com  
Alipay password: 111111

---

## Tech Stack
- **Frontend**: Vue 3, Element-Plus, Axios, Vuex, Vue-Router, Webpack
- **Backend**: Spring Boot, MyBatis, MySQL, Lombok
- **Other**: Natapp, Nginx, ...

---

## Port Configuration

```
Backend Server: 8080
Frontend Server: 3000
If you want to change, remember to update cross-origin settings in WebConfig.java on the backend.
```
