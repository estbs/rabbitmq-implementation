package com.ebs.springboot.messages;

import com.rabbitmq.client.Channel;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.amqp.support.AmqpHeaders;

@Component
public class MessagesListenerImpl implements MessagesListener {

    @Override
    public void getCreatedMessage(@Payload String payload, @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) {
        // TODO Auto-generated method stub

    }
    
}