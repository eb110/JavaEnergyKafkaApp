package figura.device_service;

import figura.device_service.entity.Device;
import figura.device_service.model.DeviceType;
import figura.device_service.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@Slf4j
@SpringBootTest
class DeviceServiceApplicationTests {

    private static final String[] LOCATIONS = new String[]{"Zgierz", "Sosnowiec", "Waligóra", "Ustrzyki", "Metalchem"};
    private static final int NUMBER_OF_DEVICES = 100;
    private static final int USERS = 100;

    @Autowired
    private DeviceRepository deviceRepository;

	@Test
	void contextLoads() {
	}

    @Disabled
    @Test
    void seedDevices(){

        for(int i = 0; i < NUMBER_OF_DEVICES; i++){
            var device = Device.builder()
                .name("Device"+i)
                .type(DeviceType.values()[(int)(Math.random() * DeviceType.values().length)])
                .location(LOCATIONS[(int)(Math.random() * LOCATIONS.length)])
                .user_id((long)(Math.random() * USERS + 10))
                .build();
            deviceRepository.save(device);
        }
        log.info("All devices created - seed process finished");
    }

}
