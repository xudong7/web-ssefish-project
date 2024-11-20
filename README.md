# *中山大学软件工程java期末项目*
# *软工闲鱼*

---
# *技术栈*

## vue element-plus axios
## springboot mybatis mysql pagehelper

---
# *端口设置*

## back server 8080
## front server 8081

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
#### seller_id 卖家的id 确认卖家是谁
#### condition 二手或者全新...0 1 2 3
#### status 订单完成情况: 已卖出,未卖出
#### date_listed 发布日期
#### likes 想要人数

# user
#### id 
#### name
#### password
#### email 
#### phonenumber
#### picture
#### intro
#### location 地址 东校,南校...
#### // rating 
#### num_transactions 交易树,上架数...
#### // interest 

# cartItems 购物车
# soldRecords 出售记录
# purchaseRecords 购买记录
# removedItems 已下架物品
# publishedItems 已发布物品

# Order
#### seller
#### buyer
#### product_id
#### quantity（购买数量）
#### total_price（订单总价）
#### status（如“待支付”、“已支付”、“已发货”、“已完成”等）
#### order_date（订单创建日期）
#### payment_method（支付方式，如“信用卡”、“支付宝”等）
#### shipping_address（收货地址）
---
# *接口*

# getProductList
### (/api/products)
### Method: GET

# searchProduct
### (/api/products/search)
### Method: GET
