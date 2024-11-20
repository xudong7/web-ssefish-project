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
---
# *接口*

# getProductList
### (/api/products)
### Method: GET

# searchProduct
### (/api/products/search)
### Method: GET
