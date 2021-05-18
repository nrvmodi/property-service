#Speed Home Property Service

### Reference Documentation

### Run Application
`mvn spring-boot:run`

Application will be run on `8080` port

I have used `h2 in memory database`. so after terminating instance it will remove data.

I have implemented `api-key` based authentication where client must provide below header 

`X-API-KEY: speedhome-token` in all request

You can configure `X-API-KEY` header value using `application.properties`


I have created `Property` entity with below fields.

    * id
    * name
    * city
    * address
    * country
    
where I have implemented normal search and like search functionality using dynamic specification builder.
For that you can refer `com.speedhome.specification.MatrixSpecificationsBuilder` class

-----

Curl request to execute those apis.
-----
##### Save API:

```
curl -X POST \
  http://localhost:8080/api/properties \
  -H 'X-API-KEY: speedhome-token' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: a60598b1-7b32-433d-ed54-3618de5ea730' \
  -d '{"name": "flat-5b", "city": "Ahmedabad", "address": \ 
      "Amba township", "country": "Malasiya"}'
```

```
curl -X POST \
  http://localhost:8080/api/properties \
  -H 'X-API-KEY: speedhome-token' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: a60598b1-7b32-433d-ed54-3618de5ea730' \
  -d '{"name": "town-5b", "city": "Ahmedabad", "address": \ 
      "Amba township", "country": "INDIA"}'
```
-----
##### Get All API:

```
curl -X GET \
  http://localhost:8080/api/properties \
  -H 'X-API-KEY: speedhome-token' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json'
```

------
##### Update API:
###### Note: Replace placeholder <PROPERTY_ID>

```
curl -X PUT \
  http://localhost:8080/api/properties/<PROPERTY_ID> \
  -H 'X-API-KEY: speedhome-token' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: a60598b1-7b32-433d-ed54-3618de5ea730' \
  -d '{"name": "flat-2", "city": "ahm", "address": "5b", "country": "AUS"}'
```

------
##### Delete API:
###### Note: Replace placeholder <PROPERTY_ID>

```
curl -X DELETE \
  http://localhost:8080/api/properties/<PROPERTY_ID> \
  -H 'X-API-KEY: speedhome-token' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: a60598b1-7b32-433d-ed54-3618de5ea730'
```

------
##### Search API:
###### Note: Here we search based on name
```
curl -X GET \
  http://localhost:8080/api/properties?name=flat-1 \
  -H 'X-API-KEY: speedhome-token' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json'
```

------
##### Like Search API:
```
curl -X GET \
  http://localhost:8080/api/properties?name_LIKE=flat \
  -H 'X-API-KEY: speedhome-token' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json'
```

```
curl -X GET \
  http://localhost:8080/api/properties?name_LIKE=town \
  -H 'X-API-KEY: speedhome-token' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json'
```