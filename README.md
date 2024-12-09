# *中山大学软件工程java期末项目*
# *软工闲鱼*
---
# How to run
## 1. run back server
### find the BackApplication.java and run it
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
sandbox-app: https://open.alipay.com/portal/forum/post/191601027
alipay账号： whaphg2436@sandbox.com
alipay密码： 111111
```

---
# *技术栈*

## vue3 element-plus axios vuex vue-router webpack
## springboot mybatis mysql lombok
## natapp nginx

---
# *端口设置*

## back server 8080
## front server 3000

---
## front setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).



---

## 建表语句
```sql
create table users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,                            -- 用户ID，自增主键
    name     VARCHAR(255) NOT NULL,                                     -- 用户名
    password VARCHAR(255) NOT NULL,                                     -- 密码
    email    VARCHAR(255) NOT NULL UNIQUE,                              -- 邮箱，唯一
    wantList varchar(255),                                              -- 想要的商品ID列表，存储为JSON格式
    picture  VARCHAR(255) DEFAULT 'https://img.icons8.com/ios/452/user' -- 头像路径或URL
);

create table products
(
    id          INT AUTO_INCREMENT PRIMARY KEY,                                   -- 商品ID，自增主键
    name        VARCHAR(255)   NOT NULL,                                          -- 商品名称
    price       DECIMAL(10, 2) NOT NULL,                                          -- 商品价格，支持两位小数
    image       VARCHAR(255),                                                     -- 商品图片路径或URL
    description VARCHAR(255),                                                     -- 商品描述
    address     INT,                                                              -- 地址
    buyerId     INT,                                                              -- 买家ID
    sellerId    INT            NOT NULL DEFAULT 1,                                -- 卖家ID
    `condition` TINYINT        NOT NULL DEFAULT 1 CHECK (`condition` IN (1, 2)),  -- 商品成色：二手(1)、全新(2)
    status      TINYINT        NOT NULL DEFAULT 1 CHECK (status IN (1, 2, 3, 4)), -- 状态：待支付(1)、已支付(2)、已发货(3)、已完成(4)
    createTime  TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,                -- 创建时间，默认当前时间
    likes       INT                     DEFAULT 0                                 -- 想要人数，默认0
);

create table trades
(
    id          INT AUTO_INCREMENT PRIMARY KEY,                      -- 交易ID，自增主键
    buyerId     INT            NOT NULL DEFAULT 1,                   -- 买家ID
    sellerId    INT            NOT NULL DEFAULT 1,                   -- 卖家ID
    productId   INT            NOT NULL DEFAULT 1,                   -- 商品ID
    productName VARCHAR(255)   NOT NULL,                             -- 商品名称
    totalAmount DECIMAL(10, 2) NOT NULL,                             -- 总金额
    status      TINYINT        NOT NULL CHECK (status IN (1, 2, 3)), -- 订单状态：进行中(1)、已完成(2)、取消(3)
    createTime  TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- 交易创建时间
);

insert into users (id, name, password, email, wantList, picture,)
values (1, 'admin', 'admin', 'admin@admin.com', null, 'https://img.icons8.com/ios/452/user');

```
## 项目结构
```
├── README.md
├── natapp
│   ├── natapp.exe
│   ├── start.bat
├── vue
│   ├── README.md
│   ├── babel.config.js
│   ├── package-lock.json
│   ├── package.json
│   ├── public
│   │   ├── favicon.ico
│   │   ├── favicon.png
│   │   └── index.html
│   ├── src
│   │   ├── App.vue
│   │   ├── main.js
│   │   ├── assets
│   │   │   ├── loginBack.jpg
│   │   │   └── logo.png
│   │   ├── layout
│   │   │   │── AdminLayout.vue
│   │   │   │── LoginLayout.vue
│   │   │   └── UserLayout.vue
│   │   ├── views
│   │   │   ├── AdminPage.vue
│   │   │   ├── ChatPage.vue
│   │   │   ├── LoginPage.vue
│   │   │   ├── ProductDetail.vue
│   │   │   ├── PublishProduct.vue
│   │   │   ├── RegisterPage.vue
│   │   │   ├── UserProfile.vue
│   │   │   └── HomePage.vue
│   │   ├── api
│   │   │   └── index.js
│   │   ├── router
│   │   │   └── index.js
│   │   └── store
│   │       └── index.js
│   └── vue.config.js
└── back
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    └── src
        ├── main
        │   ├── java
        │   │   └── com
        │   │       └── dunjia
        │   │           └── back
        │   │               ├── BackApplication.java
        │   │               ├── config
        │   │               │   ├── AliPayConfig.java
        │   │               │   └── WebConfig.java
        │   │               ├── controller
        │   │               │   ├── AliPayController.java   
        │   │               │   ├── LoginController.java
        │   │               │   ├── UploadController.java                     
        │   │               │   ├── ProductController.java
        │   │               │   ├── TradeController.java
        │   │               │   └── UserController.java
        │   │               ├── mapper
        │   │               │   ├── ProductMapper.java
        │   │               │   ├── TradeMapper.java
        │   │               │   └── UserMapper.java
        │   │               ├── pojo
        │   │               │   ├── Alipay.java
        │   │               │   ├── Result.java                
        │   │               │   ├── Product.java
        │   │               │   ├── Trade.java
        │   │               │   └── User.java
        │   │               ├── service
        │   │               │   ├── ProductService.java
        │   │               │   ├── TradeService.java
        │   │               │   └── UserService.java
        │   │               └── utils
        │   │                   ├── AliOSSUtils.java
        │   │                   └── JwtUtils.java
        │   └── resources
        │       ├── application.properties
        │       └── com.dunjia.back.mapper
        │           ├── ProductMapper.xml
        │           ├── TradeMapper.xml
        │           └── UserMapper.xml
        └── test
            └── java
                └── com
                    └── dunjia
                        └── back
                            └── BackApplicationTests.java
                                                     
```

