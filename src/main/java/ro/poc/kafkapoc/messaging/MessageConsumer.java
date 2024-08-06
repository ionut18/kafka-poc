package ro.poc.kafkapoc.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ro.poc.kafkapoc.model.MessageModel;

@Component
@Slf4j
public class MessageConsumer {

    @KafkaListener(topics = "message-topic",
            containerFactory = "messageModelKafkaListenerContainerFactory")
    public void listen(@Payload final MessageModel message) {
        log.info("[message-topic] Message received: {}", message.toString());
    }
}
