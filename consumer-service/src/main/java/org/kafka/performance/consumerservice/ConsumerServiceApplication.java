package org.kafka.performance.consumerservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class ConsumerServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(ConsumerServiceApplication.class);
    private final AtomicLong counter = new AtomicLong();

    public static void main(String[] args) {
        SpringApplication.run(ConsumerServiceApplication.class, args);
    }

    @KafkaListener(
            topics = "high-throughput-topic",
            groupId = "high-throughput-group"
    )
    public void consume(String message) {
        long count = counter.incrementAndGet();
        if(count %100_000 == 0){
            log.info("consumed {} messages", count);
        }
    }


}