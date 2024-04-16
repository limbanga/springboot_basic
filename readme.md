# Spring API + Auth +JWT

---
## Description
> Learning how to create a Spring API and JWT authentication.
### Detail
- Api: RESTFUL and validation.
- Auth: Spring security and jwt token.
### Test

> Test the API with Postman.
1. Product:
- get all product: `GET /api/product/`

![img of test](previews/product_get_all.png)

- get product by id: `GET /api/product/{id}`

![img of test](previews/product_get_by_id.png)

- create product: `POST /api/product/`

![img of test](previews/product_create.png)

- update product: `PUT /api/product/`

![img of test](previews/product_update.png)
> example validation
>
![img of test](previews/product_update_validation.png)

- delete product: `DELETE /api/product/{id}`

![img of test](previews/product_delete.png)

2. Order:
- get all order: `GET /api/order/`

![image of test](previews/order_get_all.png)

- get order by id: `GET /api/order/{id}`

![image of test](previews/order_get_by_id.png)

- create order: `POST /api/order/`
> owner of order is the user who created it. (get from jwt token)
![image of test](previews/order_create.png)

- update order: `PUT /api/order/`
> Only admin can update order.
> 
![img.png](previews/order_update.png)

- delete order: `DELETE /api/order/{id}`
- Only admin can delete order.

![img.png](previews/order_delete.png)

3. Auth:
- login: `POST /api/auth/login`

![img.png](previews/login.png)

- register: `POST /api/auth/register`

![img.png](previews/register.png)
> if username existed
> 
![img.png](previews/register_existed.png)

- access security endpoint:

![img.png](previews/access_secure.png)
