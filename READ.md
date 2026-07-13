Logic steps #########################################
main folder
add user service
compose mysql

DOCKER ###########################################
docker-compose
docker-mysql

Docker up
```
docker compose -v up -d
```

Docker down
```
docker compose down
```

DB ####################################################
Mysql - docker
The inner port has to be 3306
example: 3307:3306


Services ###################################################
user-service
    contains alerting for energy consumption - utilises emails
docker mysql
flyway db migration tool - version control of migrations
    all changes to schemas, tables etc. requires resources/db.migration sql files
    it is executed during app startup

Annotations to remember ##################################
@Builder -> lombok for getters and setters
@Data -> lombok for getters and setters builder pattern
@PostMapping
@RequestBody -> if expecting model from the client
@RequestMapping -> custom address "/api/blablabla"
@RestController
@Service
@Slf4j -> lombok logger "log.info()..."
