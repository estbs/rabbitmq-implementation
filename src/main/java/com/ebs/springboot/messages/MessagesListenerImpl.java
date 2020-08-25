package com.ebs.springboot.messages;

import com.rabbitmq.client.Channel;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.support.AmqpHeaders;

@Slf4j
@Component
public class MessagesListenerImpl implements MessagesListener {

    @Override
    public void getCreatedMessage(@Payload String payload, @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag)
            throws Exception {
        log.info("message queued: ".concat(payload));
        if (Integer.parseInt(payload) < 3) {
            throw new Exception();
        } else if (Integer.parseInt(payload) < 6) {
            channel.basicReject(deliveryTag, false);
        }
    }
    
}