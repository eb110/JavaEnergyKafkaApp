package figura.user_service;

import figura.user_service.entity.User;
import figura.user_service.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UserServiceApplicationTests {

    private static final int NUMBER_OF_USERS = 100;
    private static final String[] NAMES = new String[]{"Henio", "Stefan", "Zygfyd", "Alojz", "Miecio"};
    private static final String[] LOCATIONS = new String[]{"Zgierz", "Sosnowiec", "Waligóra", "Ustrzyki", "Metalchem"};

    @Autowired
    private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

    @Disabled
    @Test
    void seedUsers(){

        for(int i = 0; i < NUMBER_OF_USERS; i++){
            var user = User.builder()
                .name(NAMES[(int)(Math.random() * NAMES.length)])
                .email(NAMES[(int)(Math.random() * NAMES.length)] + i + 1 + "@op.pl")
                .surname(NAMES[(int)(Math.random() * NAMES.length)] + "wski" + i + 1)
                .address(LOCATIONS[(int)(Math.random() * LOCATIONS.length)])
                .alerting(i % 2 == 0)
                .energyAlertingThreshold(1000.0 + i)
                .build();
            userRepository.save(user);
        }
        log.info("All users created - seed process finished");
    }

}
