package org.kafka.performance.producerservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class ProducerServiceApplication implements CommandLineRunner {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final Logger log =
            LoggerFactory.getLogger(ProducerServiceApplication.class);


    public ProducerServiceApplication(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        int messageCount = 1_000_000; // ilk test

        long start = System.currentTimeMillis();
        for (int i = 0; i < messageCount; i++) {
            kafkaTemplate.send("high-throughput-topic", "key-" +i, "message-" + i);
            if (i % 100_000 == 0) {
                System.out.println("Produced " + i + " messages");
            }
            log.info("Produced {} messages", i);

        }
        long end = System.currentTimeMillis();
        System.out.println("Sent " + messageCount + " messages in " + (end - start) + " ms");
    }

}
