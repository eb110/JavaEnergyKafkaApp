Logic steps #########################################
main folder
add user service
compose mysql

DOCKER ###########################################
docker-compose
docker-mysql
docker-kafka
docker-kafkaUi

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
ingestion-service
    all devices will be sending data (electrical information, power consumption etc)
    ingestion service will handle the data (TB per day) and post it to db
kafka docker
kafka ui docker


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
@EnableScheduling -> required by scheduler, place in start application class
@Entity
@Enumerated -> in case of Enum usage by db entity
    @Enumerated(EnumType.STRING)  
@ExceptionHandler -> utilised by global exception class
@GeneratedValue(strategy= GenerationType.IDENTITY)
@GetMapping
@Id -> entity id
@JsonFormat -> useful for type serialization
    @JsonFormat(shape =  JsonFormat.Shape.STRING)
@NoArgsConstructor -> lombok
@Override -> inheritance for example new version of abstract function
@PathVariable -> client simple parameter
@Pointcut -> aop -> information to apply -> for example to all service method
    @Pointcut("execution(* figura.user_service.service.*.*(..))")
@PostMapping
@PreDestroy -> for example - shutdown of threads
@PutMapping
@Repository -> jpa
@RequestBody -> if expecting model from the client
@RequestMapping -> custom address "/api/blablabla"
@ResponseStatus -> this automatically sends back the status
    we don't have to return it
@RestController
@Scheduled -> scheduler to run functionality, requires enabling
    go to @EnableScheduling
    - for example method @Scheduled(fixedRate = 5000) run every 5s
@Service
@Slf4j -> lombok logger "log.info()..."
@SpringBootApplication -> always in the startup class
@SpringBootTest
@Table -> schema table
@Test
@Value -> property from configuration
    @Value("${simulation.request-per-interval")

shortcuts#########################
ctrl shift t -> go to test
ctrl / -> comment uncomment
alt f12 -> terminal