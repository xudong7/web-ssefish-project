# 中山大学软件工程java期末项目
# 软工闲鱼
# 项目简介
本项目受到学院项目软工集市影响，联想到校内同学对于二手交易的需求，因此开发了软工闲鱼，希望能够帮助同学们更好地进行二手交易。
---
# How to run
## 1. run back server
```
find the BackApplication.java and run it
please make sure you have change settings in application.properties
and other settings which used in the project
TODO tag has been added in the project
```
## 2. run front server
### run the following command
```
cd vue
```
```
npm install 
```
```
npm run serve
```
## 3. open natapp dir and click start.bat
```
ps. 
remember natapp's url(like http://xxxx.natapp.cc)
replace the url in application.properties
```
## 4. open browser and enter the url
```
download alipay sandbox app to pay
```   
```
(:使用你自己的沙箱账号和密码
alipay账号： whaphg2436@sandbox.com
alipay密码： 111111
```

---
# 技术栈

## vue3 element-plus axios vuex vue-router webpack
## springboot mybatis mysql lombok
## natapp nginx ...

---
# 端口设置
```
back server 8080
front server 3000
如果你要更改，记得在后端的WebConfig.java中更改跨域设置
```
