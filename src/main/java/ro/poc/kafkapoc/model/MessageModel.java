package ro.poc.kafkapoc.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MessageModel {

    private String value;
    private String sender;
}
