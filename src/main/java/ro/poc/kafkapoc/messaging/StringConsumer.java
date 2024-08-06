package ro.poc.kafkapoc.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringConsumer {

    @KafkaListener(topics = "test1")
    public void listenDefaultGroupId(@Payload String message) {
        log.info("[test] Received message with value {}", message);
    }

    @KafkaListener(topics = "test1", groupId = "test2")
    public void listenCustomGroupId(@Payload String message) {
        log.info("[test2] Received message with value {}", message);
    }

    @KafkaListener(topics = {"test1", "test2"}, groupId = "test3")
    public void listenMultipleTopics(@Payload String message,
                                     @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                     @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("[test-multiple] Received message with value {} partition {} topic {}", message, partition, topic);
    }

    @KafkaListener(topics = "test2",
            containerFactory = "filterKafkaListenerContainerFactory")
    public void listenWithFiler(@Payload String message) {
        log.info("[test-with-filer] Received message with value {}", message);
    }
}
