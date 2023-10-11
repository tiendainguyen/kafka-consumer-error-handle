package aibles.poc.nonblockingmechanism.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import java.net.SocketException;

@Service
@Slf4j
public class ListenerService {
    @RetryableTopic(attempts = "5",
        backoff = @Backoff(value = 3000L),
        include = {SocketException.class, RuntimeException.class})
    @KafkaListener(id = "group", topics = "non-blocking-topic")
    public void listen(String message) throws ClassNotFoundException {
        log.info("message is: {}", message);
        throw new RuntimeException("consumer error internal");
//        throw new ClassNotFoundException();
    }
}
