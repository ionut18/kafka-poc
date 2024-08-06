package ro.poc.kafkapoc.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class StringProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendStringMessage(final String msg, final String topic) {
        final CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, msg);
        future.whenComplete((r, e) -> {
            if (e == null) {
                log.info("[Topic: {}] Sent message: [{}] with offset: [{}]", topic, msg, r.getRecordMetadata().offset());
            } else {
                log.error("[Topic: {}] Unable to send message: [{}] due to error: {}", topic, msg, e.getMessage());
            }
        });
    }
}
