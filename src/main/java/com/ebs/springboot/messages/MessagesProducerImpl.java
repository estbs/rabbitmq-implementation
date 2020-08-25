package com.ebs.springboot.messages;

import org.apache.commons.lang3.StringUtils;
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
    
    RabbitTemplate rabbitTemplate;

    @Override
    public void queueMessage(int messageNumber) {
        rabbitTemplate.convertAndSend(EXCHANGE_CREATE_MESSAGES, StringUtils.EMPTY, Integer.toString(messageNumber));
    }
    
}