package com.ebs.springboot.messages;

import java.io.IOException;

import com.rabbitmq.client.Channel;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

public interface MessagesListener {

    @RabbitListener(admin = "rabbitAdmin", containerFactory = "rabbitListenerContainerFactory", queues = "ebs.messages.created")
    void getCreatedMessage(Message message, @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException;

    @RabbitListener(admin = "rabbitAdmin", containerFactory = "rabbitListenerContainerFactory", queues = "ebs.messages.created.1")
    void getCreatedMessage1(Message message, @Header(AmqpHeaders.CHANNEL) Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException;

    @RabbitListener(admin = "rabbitAdmin", containerFactory = "rabbitListenerContainerFactory", queues = "ebs.messages.created.2")
    void getCreatedMessage2(Message message, @Header(AmqpHeaders.CHANNEL) Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException;

    @RabbitListener(admin = "rabbitAdmin", containerFactory = "rabbitListenerContainerFactory", queues = "ebs.messages.created.3")
    void getCreatedMessage3(Message message, @Header(AmqpHeaders.CHANNEL) Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException;

    @RabbitListener(admin = "rabbitAdmin", containerFactory = "rabbitListenerContainerFactory", queues = "ebs.messages.created.dlq")
    void getCreatedMessageDLQ(Message message, @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException;

}