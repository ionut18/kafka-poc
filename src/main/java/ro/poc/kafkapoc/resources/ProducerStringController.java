package ro.poc.kafkapoc.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.poc.kafkapoc.messaging.StringProducer;

@RestController
@RequiredArgsConstructor
@RequestMapping("/string")
public class ProducerStringController {

    private final StringProducer stringProducer;

    @GetMapping("/produce/both")
    public final String produce(@RequestParam final String message) {
        stringProducer.sendStringMessage(message + "1", "test1");
        stringProducer.sendStringMessage(message + "2", "test2");
        return message;
    }

    @GetMapping("/produce/{topic}")
    public final String produce(@PathVariable final String topic, @RequestParam final String message) {
        stringProducer.sendStringMessage(message + " topic", topic);
        return message;
    }
}
