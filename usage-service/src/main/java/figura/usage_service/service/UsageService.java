package figura.usage_service.service;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import figura.kafka.event.EnergyUsageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsageService {

    private final InfluxDBClient influxDBClient;

    @Value("${influx.bucket}")
    private String influxBucket;

    @Value("${influx.org}")
    private String influxOrg;

    //@Autowired
    public UsageService(InfluxDBClient influxDBClient) {
        this.influxDBClient = influxDBClient;
    }

    //obtain the data from kafka and push it to the influxDB
    @KafkaListener(topics = "energy-usage", groupId = "usage-service")
    public void energyUsageEvent(EnergyUsageEvent energyUsageEvent) {
        log.info("\n\n receiver energy Usage Event: {}", energyUsageEvent);

        //Point from influxDB
        Point point = Point.measurement("energy_usage")
            .addTag("deviceId", String.valueOf(energyUsageEvent.deviceId()))
            .addField("energyConsumed", energyUsageEvent.energyConsumed())
            .time(energyUsageEvent.timestamp(), WritePrecision.MS);

        influxDBClient.getWriteApiBlocking().writePoint(influxBucket, influxOrg, point);
    }

}
