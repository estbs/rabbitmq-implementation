package com.ebs.springboot.messages;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagesProducerImpl implements MessagesProducer {

    public static final String EXCHANGE_CREATE_MESSAGES = "ebs.messages.create";
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void queueMessage(int messageNumber) {
        rabbitTemplate.convertAndSend(EXCHANGE_CREATE_MESSAGES, "", Integer.toString(messageNumber));
    }
    
}