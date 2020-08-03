package com.ebs.springboot.messages;

import com.rabbitmq.client.Channel;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

public interface MessagesListener {
    
    @RabbitListener(admin = "rabbitAdmin", containerFactory = "rabbitListenerContainerFactory", queues="ebs.messages.created")
    void getCreatedMessage(@Payload String payload, @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag);

}