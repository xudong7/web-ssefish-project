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
# *接口*

# getProductList
### (/api/products)
### Method: GET

# searchProduct
### (/api/products/search)
### Method: GET
