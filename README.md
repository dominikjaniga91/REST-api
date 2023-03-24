# REST-api

## Run application

### Run the following commands in terminal or user IDE
```
git clone git@github.com:dominikjaniga91/REST-api.git
cd REST-api
mvn install
cd jmp-service-rest/target
java -jar jmp-service-rest-1.0-SNAPSHOT.jar

```


## Swagger documentation

http://localhost:8080/swagger-ui/index.html#/

## Postman collections
Collection is available in a app main directory

[Link to postman collection](https://github.com/dominikjaniga91/REST-api/blob/master/rest-api.postman_collection.json)

Make sure that Create requests for user and subscription has the following lines:

For user: `pm.globals.set("userId", pm.response.json().id);`

For subscription: `pm.globals.set("subscriptionId", pm.response.json().id);`
