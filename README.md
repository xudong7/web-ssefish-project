# *中山大学软件工程java期末项目*
# *软工闲鱼*

---
# *技术栈*

## vue element-plus axios
## springboot mybatis mysql pagehelper

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
# *数据对象*

# product
#### id
#### name
#### price
#### image
#### description
#### buyer_id 买家的id
#### seller_id 卖家的id 
#### condition 二手或者全新...0 1 2 3
#### status（如“待支付”、“已支付”、“已发货”、“已完成”等）
#### date_listed 发布日期
#### likes 想要人数

# user
#### id 
#### name
#### password
#### email 
#### wantList(加入likes的商品的id)
#### phonenumber
#### picture
#### intro
#### address 地址 东校,南校...
#### num_transactions 交易树,上架数...

# tradeList
#### buyer(user)
#### seller(user)
#### product
#### status(已完成订单,订单进行中,订单取消)
#### date(订单创建日期)

---
## 建表语句
```sql
create table users
(
    id              INT AUTO_INCREMENT PRIMARY KEY, -- 用户ID，自增主键
    name            VARCHAR(255) NOT NULL,          -- 用户名
    password        VARCHAR(255) NOT NULL,          -- 密码
    email           VARCHAR(255) NOT NULL UNIQUE,   -- 邮箱，唯一
    wantList        JSON,                           -- 想要的商品ID列表，存储为JSON格式
    phoneNumber     VARCHAR(15),                    -- 手机号码
    picture         VARCHAR(255),                   -- 头像路径或URL
    intro           VARCHAR(255),                   -- 个人简介
    address         VARCHAR(255),                   -- 地址
    numTransactions INT DEFAULT 0                   -- 交易数或上架数，默认值为0
);

create table products
(
    id          INT AUTO_INCREMENT PRIMARY KEY,                                   -- 商品ID，自增主键
    name        VARCHAR(255)   NOT NULL,                                          -- 商品名称
    price       DECIMAL(10, 2) NOT NULL,                                          -- 商品价格，支持两位小数
    image       VARCHAR(255),                                                     -- 商品图片路径或URL
    description VARCHAR(255),                                                     -- 商品描述
    address     INT,                                                              -- 地址
    buyerId     INT,                                                              -- 买家ID，外键（可选）
    sellerId    INT            NOT NULL DEFAULT 1,                                -- 卖家ID，外键
    `condition` TINYINT        NOT NULL DEFAULT 1 CHECK (`condition` IN (1, 2)),  -- 商品成色：二手(1)、全新(2)
    status      TINYINT        NOT NULL DEFAULT 1 CHECK (status IN (1, 2, 3, 4)), -- 状态：待支付(1)、已支付(2)、已发货(3)、已完成(4)
    createTime  TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,                -- 创建时间，默认当前时间
    likes       INT                     DEFAULT 0                                 -- 想要人数，默认0
);

create table trades
(
    id         INT AUTO_INCREMENT PRIMARY KEY,               -- 交易ID，自增主键
    buyerId    INT     NOT NULL DEFAULT 1,                   -- 买家ID
    sellerId   INT     NOT NULL DEFAULT 1,                   -- 卖家ID
    productId  INT     NOT NULL DEFAULT 1,                   -- 商品ID
    status     TINYINT NOT NULL CHECK (status IN (1, 2, 3)), -- 订单状态：进行中(1)、已完成(2)、取消(3)
    createDate TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,   -- 交易创建时间
    FOREIGN KEY (buyerId) REFERENCES users (id),             -- 关联买家ID
    FOREIGN KEY (sellerId) REFERENCES users (id),            -- 关联卖家ID
    FOREIGN KEY (productId) REFERENCES products (id)         -- 关联商品ID
);

```


---
# *接口*

# getProductList
### (/api/products)
### Method: GET

# searchProduct
### (/api/products/search)
### Method: GET
