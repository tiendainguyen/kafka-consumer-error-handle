package aibles.poc.blockingmechanism.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ListenerService {
    @KafkaListener(id = "group", topics = "blocking-topic")
    public void listen(String message) {
        log.info("message is: {}", message);
        throw new RuntimeException("consumer error internal");
    }
}
