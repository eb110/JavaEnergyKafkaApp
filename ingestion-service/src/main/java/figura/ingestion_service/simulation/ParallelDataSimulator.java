package figura.ingestion_service.simulation;

import figura.ingestion_service.dto.EnergyUsageDto;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Component
public class ParallelDataSimulator implements CommandLineRunner {

    private final Random random = new Random();
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${simulation.parallel-threads}")
    private int parallelThreads;

    @Value("${simulation.requests-per-interval}")
    private int requestPerInterval;

    @Value("${simulation.endpoint}")
    private String ingestionEndpoint;

    private final ExecutorService executorService;

    public ParallelDataSimulator() {
        this.executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("\n\nParallelDataSimulatorn\n\n");
        ((ThreadPoolExecutor)executorService).setCorePoolSize(parallelThreads);
    }

    @Scheduled(fixedRateString = "${simulation.interval-ms}")
    public void sendMockData(){
        log.info("\nENERGY USAGES FOR KAFKA Simulation started\n");
        int batchSize = requestPerInterval / parallelThreads;
        int reminder = requestPerInterval % parallelThreads;

        for(int i = 0; i < parallelThreads; i++){
            int requestForThread = batchSize + (i < reminder ? 1 : 0);
            executorService.submit(() -> {
                for(int j = 0; j < requestForThread; j++) {
                    EnergyUsageDto dto = EnergyUsageDto.builder()
                        .deviceId(random.nextLong(4, 12))
                        .energyConsumed(Math.round(random.nextDouble(0.0, 2.0) * 100.0) / 100.0)
                        .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
                        .build();

                    try{
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);

                        HttpEntity<EnergyUsageDto> request = new HttpEntity<>(dto, headers);
                        restTemplate.postForEntity(ingestionEndpoint, request, Void.class);

                        log.info("Simulation request sent {}", dto);
                    }catch (Exception e){
                        log.error("Simulation request failed: {}", e.getMessage());
                    }
                }
            });
        }
    }

    @PreDestroy
    public void shutdown(){
        executorService.shutdown();
        log.info("\nParallel simulator shut down...\n");
    }
}
