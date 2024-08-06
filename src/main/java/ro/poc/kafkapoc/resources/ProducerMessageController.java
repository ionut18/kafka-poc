package ro.poc.kafkapoc.resources;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.poc.kafkapoc.messaging.MessageProducer;
import ro.poc.kafkapoc.model.MessageModel;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
@Slf4j
public class ProducerMessageController {

    private final MessageProducer messageProducer;

    @PostMapping("/produce")
    public final String produceMessage(@RequestBody MessageModel message) {
        try {
            messageProducer.sendMessageModel(message, "message-topic");
        } catch (Exception e) {
            log.error("Error while sending message: {}", e.getMessage());
            return "failed";
        }
        return "success";
    }
}
