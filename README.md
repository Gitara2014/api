# Getting Started

Spring-REST-API demo. 

```shell
mvn clean package
sudo docker-compose up
java -jar target/*.jar
```

### pg-admin
http://localhost:5050/browser/

...in pg-admin, when creating server you need to use container-name (api-db-container) from docker compose and NOT localhost

    Connection: api-db-container
    Port:   5432
    Maintanence databas:    postgres
    username:               postgres
    password:               changeme

    Servers > my-server > postgres > schemas > blog > Tables (2) > posts 

### API TEST
```shell
curl --location --request POST 'localhost:8080/posts/' \
--header 'Content-Type: application/json' \
--data-raw '{
"author": "Arthur C. Clarke",
"content": "Some cool sci-fi content",
"title": "2001: A Space Odyssey"
}'

curl --location --request PUT 'localhost:8080/posts/' \
--header 'Content-Type: application/json' \
--data-raw '{
"author": "Arthur C. Clarke",
"content": "Second part of some cool sci-fi content",
"uuid": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"title": "2010: Odyssey Two"
}'

curl --location --request GET 'localhost:8080/posts/all' \
--header 'Content-Type: application/json'


curl --location --request GET 'localhost:8080/posts/all-pageable?page=0&size=3' \
--header 'Content-Type: application/json'

curl --location --request GET 'localhost:8080/posts?id=d7aff96d-0469-41f9-90f7-2d8006d00a8a'

curl --location --request DELETE 'localhost:8080/posts?id=d7aff96d-0469-41f9-90f7-2d8006d00a8a'
```

### Architecture
- Hibernate validator to validate data before entering the system
- Rest Controllers expose API endpoints. 
- Transfer Objects - immutable objects. Using Lombok to make code base smaller 
- Using [MapStruct](https://mapstruct.org/) to map TO to Entities and vice versa
- Using [Flyway](https://flywaydb.org/) for DB migration

### MAPSTRUCT
* https://mapstruct.org/
* https://mapstruct.org/documentation/stable/reference/html/
* https://stackoverflow.com/questions/63034956/mapstruct-no-property-named-packaging-exists-in-source-parameters
* https://stackoverflow.com/questions/61710510/mapstruct-cannot-find-implementation
* https://medium.com/globant/mapstruct-advanced-concepts-and-dependency-injection-46f28af54e33
* https://www.baeldung.com/mapstruct#3-the-new-mapper


### LOMBOK 
* https://projectlombok.org/features/Value


#### More 
* https://medium.com/geekculture/clean-architecture-in-a-spring-application-312e119ee8ec
* https://howtodoinjava.com/java/date-time/zoneddatetime-class/
* https://www.javaguides.net/2021/04/spring-boot-dto-validation-example.html
* https://stackoverflow.com/questions/29069956/how-to-test-validation-annotations-of-a-class-using-junit
* https://stackoverflow.com/questions/2342579/http-status-code-for-update-and-delete
* https://www.baeldung.com/spring-data-jpa-pagination-sorting
* https://spring.io/guides/gs/spring-boot-docker/
* https://github.com/khezen/compose-postgres/blob/master/docker-compose.yml
* https://thorben-janssen.com/database-migration-with-spring-boot/