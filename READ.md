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
    it is executed during user-device app startup but handles all servcies!
device-service
    1 user can have multiple devices - 1 device can have only one user
global exception
aol for logging and time measurement


Annotations to remember ##################################
@AfterReturning -> aop
@AllArgsConstructor -> lombok
@Aspect -> aop -> allows to create a functionality that reflects to a group of methods
    for example -> to all service methods -> check pointcut, before, afterReturning
@Autowired ->
    test to call repository
@Before -> aop
@Builder -> lombok for getters and setters
@Column -> name in brackets points to the schema column name
@Component
@ControllerAdvice -> this mapping checks if a class that handles exceptions (global exception handler)
    knows how to handle controller thrown exceptions
@Data -> lombok for getters and setters builder pattern
@DeleteMapping
@Disabled -> to disable unit test, for example finished seed or just broken test
@Entity
@Enumerated -> in case of Enum usage by db entity
    @Enumerated(EnumType.STRING)  
@ExceptionHandler -> utilised by global exception class
@GeneratedValue(strategy= GenerationType.IDENTITY)
@GetMapping
@Id -> entity id
@NoArgsConstructor -> lombok
@PathVariable -> client simple parameter
@Pointcut -> aop -> information to apply -> for example to all service method
    @Pointcut("execution(* figura.user_service.service.*.*(..))")
@PostMapping
@PutMapping
@Repository -> jpa
@RequestBody -> if expecting model from the client
@RequestMapping -> custom address "/api/blablabla"
@RestController
@Service
@Slf4j -> lombok logger "log.info()..."
@SpringBootApplication -> always in the startup class
@SpringBootTest
@Table -> schema table
@Test


shortcuts#########################
ctrl shift t -> go to test
ctrl / -> comment uncomment
alt f12 -> terminal