package ro.poc.kafkapoc.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import ro.poc.kafkapoc.model.MessageModel;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageProducer {

    private final KafkaTemplate<String, MessageModel> messageModelKafkaTemplate;

    public void sendMessageModel(final MessageModel msg, final String topic) {
        final CompletableFuture<SendResult<String, MessageModel>> future = messageModelKafkaTemplate.send(topic, msg);
        future.whenComplete((r, e) -> {
            if (e == null) {
                log.info("[Topic: {}] Sent message: [{}] with offset: [{}]", topic, msg.toString(), r.getRecordMetadata().offset());
            } else {
                log.error("[Topic: {}] Unable to send message: [{}] due to error: {}", topic, msg.toString(), e.getMessage());
            }
        });
    }
}
