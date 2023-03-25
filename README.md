# REST-api

## Task description

:white_check_mark: 1. Create maven project with 4 modules :

- jmp-dto - jmp-service-api - jmp-cloud-service-impl - jmp-service-rest

:white_check_mark: 2. Create the fallowing classes under jmp-dto module :

- [User] Long id; String name; String surname; LocalDate birthday; - [Subscription] Long id; User user; LocalDate startDate; - [UserRequestDto] Long id; String name; String surname; String birthday; - [SubscriptionRequestDto] Long id; Long userId; - [UserResponseDto] Long id; String name; String surname; String birthday; - [SubscriptionResponseDto] Long id; Long userId; String startDate;

:white_check_mark: 3. Create UserController under jmp-service-rest module with following methods:

- createUser(UserRequestDto); - updateUser(UserRequestDto); - deleteUser(Long); - getUser(Long); - getAllUser();

:white_check_mark: 4. Use id filed into UserRequestDto only for updating data.

:white_check_mark: 5. Must be returned UserResponseDto from the following methods :

- createUser(UserRequestDto); - updateUser(UserRequestDto); - getUser(Long); - getAllUser();

:white_check_mark: 6. Create ServiceController under jmp-service-rest module with following methods :

- createSubscription(SubscriptionRequestDto); - updateSubscription(SubscriptionRequestDto); - deleteSubscription(Long); - getSubscription(Long); - getAllSubscription();

:white_check_mark: 7. Use id filed into SubscriptionRequestDto only for updating data.

:white_check_mark: 8. Must be returned SubscriptionResponseDto from the following methods :

- createSubscription(SubscriptionRequestDto); - updateSubscription(SubscriptionRequestDto); - getSubscription(Long); - getAllSubscription(); (as list)

:white_check_mark: 9. Add necessary interfaces/methods to jmp-service-api.

:white_check_mark: 10. Implement interfaces/methods under jmp-service-api into jmp-cloud-service-impl.

:white_check_mark: 11. UserController and ServiceController must provide REST API interfaces according to 2nd of REST API maturity.

:x: 12. You can use memory structures to store the data (List, Map..). -> **I used in-memory DB**

:white_check_mark: 13. Use jmp-cloud-service-impl to implement UserController and ServiceController.

:white_check_mark: 14. Implement Application class with @SpringBootApplication annotation and main method.

:white_check_mark: 15. Use lambdas and Java 8 features wherever it’s applicable.

:white_check_mark: 16. Make sample requests to UserController and ServiceController, provide screenshots/responses. (3-4 stars)

[Get subscription request](https://github.com/dominikjaniga91/REST-api/blob/master/get_subscription_postman_request.png)

[Get user request](https://github.com/dominikjaniga91/REST-api/blob/master/get_user_postman_request.png)

:white_check_mark: 17. Use a DB to store the data (List, Map..).

:white_check_mark: 18. Use spring boot data jpa module to access data.

:white_check_mark: 19. Use spring converters to convert :

- UserRequestDto to User - User to UserResponseDto - SubscriptionRequestDto to Subscription - Subscription to SubscriptionResponseDto

:white_check_mark: 20. Document methods in UserController and ServiceController using Swagger 2 annotations.

:white_check_mark: 21. Make sample requests to UserController and ServiceController via Swagger UI, provide screenshots. (5 stars)

[Subscription swagger request](https://github.com/dominikjaniga91/REST-api/blob/master/user_request.png)

[User swagger request]()

:x: 22. Use Java 9 modules wherever it’s applicable. -> **I used Maven modules**

:white_check_mark: 23. Implement REST APIs according to all Richardson Maturity Level (0 - 3).

:white_check_mark: 24. Make sample requests to UserController and ServiceController, provide screenshots/responses.



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
