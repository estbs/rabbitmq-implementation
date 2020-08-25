package com.ebs.springboot.messages;

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MessagesProducerImpl implements MessagesProducer {

    public static final String EXCHANGE_CREATE_MESSAGES = "ebs.messages.create";
    public static final String TEXT_PLAIN = "text/plain";
    
    RabbitTemplate rabbitTemplate;

    @Override
    public void queueMessage(int messageNumber) {
        var messageProperties = new MessageProperties();
        messageProperties.setContentType(TEXT_PLAIN);
        var messageBody = Integer.toString(messageNumber);
        var message = new Message(messageBody.getBytes(StandardCharsets.UTF_8), messageProperties);
        rabbitTemplate.convertAndSend(EXCHANGE_CREATE_MESSAGES, StringUtils.EMPTY, message);
    }
    
}