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
#### seller_id
#### condition
#### status
#### date_listed
#### likes

# user
#### id
#### name
#### password
#### email
#### phonenumber
#### picture
#### bio
#### location
#### rating
#### num_transactions
#### interest

# cartItems 购物车
# soldRecords 购买记录
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
