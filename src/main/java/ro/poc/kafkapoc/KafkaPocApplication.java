package ro.poc.kafkapoc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class KafkaPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaPocApplication.class, args);
    }

}
