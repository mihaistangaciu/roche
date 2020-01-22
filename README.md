# Build application
mvn clean install

# Persistence
H2 in memory database : http://localhost:8080/h2-console <br/>
JDBC URL: jdbc:h2:mem:rouchedb <br/>
User:sa <br/>
Pass: <br/>

# Endpoints

## Products
### Create Products
Path: POST localhost:8080/rouche/api/products/create<br/>  
**Request**: <br/>
```json
{
	"name" : "Product Name",
	"price": 1.34
	
}
```
<br/>

**Response**<br/>
```json
{
        "sku" : 1,
	"name" : "Product Name",
	"price": 1.34
	
}
```

<br/>

### Update Products

Path: POST localhost:8080/rouche/api/products/update <br/>  
**Request**: <br/>
```json
{
	"name" : "Product Name",
	"price": 1.34
	
}
```
<br/>

**Response**<br/>
```json
{
        "sku" : 1,
	"name" : "Product Name",
	"price": 1.34
	
}
```


### Retrieve all products

Path: GET localhost:8080/rouche/api/products <br/>  
**Request**: <br/>
<br/>

**Response**<br/>
```json
{
    "sku" : 1,
	"name" : "Product Name",
	"price": 1.34
	
}
```


### Delete product (soft-delete)

Path: DELETE localhost:8080/rouche/api/products/{sku}<br/>  
**Request**: <br/>
<br/>

**Response**<br/>
```json
{
        "sku" : 1,
	"name" : "Product Name",
	"price": 1.34
	
}
```



## Orders

### Create Order

**Request** POST localhost:8080/rouche/api/orders/create <br/>

**Response** <br/>

```json
{
    "orderProducts": [
        {
            "quantity": 2,
            "product": {
                "sku": 1,
                "name": "mango",
                "price": 1.34,
                "creationDate": "2020-01-22 23:30:42",
                "deleted": false
            },
            "totalPrice": 2.68
        },
        {
            "quantity": 1,
            "product": {
                "sku": 2,
                "name": "mango",
                "price": 1.34,
                "creationDate": "2020-01-22 23:30:44",
                "deleted": false
            },
            "totalPrice": 1.34
        }
    ],
    "id": 4,
    "dateCreated": "2020-01-22 23:29:53",
    "buyerEmail": "ceva@saas.com",
    "totalOrderAmount": 4.02
}

```

### Retrieve all orders in a time and date range

**Request** GET localhost:8080/rouche/api/orders/{startDate}/{endDate} <br/>
Date format yyyy-MM-dd HH:mm:ss <br/>

**Response** <br/>

````json
[
    {
        "orderProducts": [
            {
                "quantity": 2,
                "product": {
                    "sku": 1,
                    "name": "mango",
                    "price": 1.34,
                    "creationDate": "2020-01-22 23:28:42",
                    "deleted": false
                },
                "totalPrice": 2.68
            },
            {
                "quantity": 1,
                "product": {
                    "sku": 2,
                    "name": "mango",
                    "price": 1.34,
                    "creationDate": "2020-01-22 23:28:44",
                    "deleted": false
                },
                "totalPrice": 1.34
            }
        ],
        "id": 4,
        "dateCreated": "2020-01-22 23:29:53",
        "buyerEmail": "ceva@saas.com",
        "totalOrderAmount": 4.02
    }
]
````
# To do next
* Unit Tests Orders
* Validation Orders and decouple OrderEntitiy from COntroller using a ModelMapper
* Create Swagger COntract and autogenerate Models using Swagger codegen
* Expose Swagger.ui page for documentation
* Add deployment YAML and place in Kubernetes/Openshift cluster
* Create JBeehave tests and add it to Jenkinsfile
* Set-up Jenkins and Sonar servers
* Monitoring and Alerts
<br/>
More improvements in GitHib Issues section

