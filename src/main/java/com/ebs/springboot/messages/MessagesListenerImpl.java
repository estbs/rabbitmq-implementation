package com.ebs.springboot.messages;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import com.rabbitmq.client.Channel;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MessagesListenerImpl implements MessagesListener {

    RabbitTemplate rabbitTemplate;

    @Override
    public void getCreatedMessage(Message message, @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
        var payload = new String(message.getBody(), StandardCharsets.US_ASCII);
        log.info("message queued: ".concat(payload));
        if (Integer.parseInt(payload) < 3) {
            channel.basicReject(deliveryTag, true);
        } else if (Integer.parseInt(payload) < 6) {
            channel.basicReject(deliveryTag, false);
        } else {
            channel.basicAck(deliveryTag, false);
        }
    }

    @Override
    public void getCreatedMessage1(@Payload String payload, @Header(AmqpHeaders.CHANNEL) Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
        log.info("message 1 queued: ".concat(payload));
        if (Integer.parseInt(payload) < 5) {
            channel.basicReject(deliveryTag, false);
        } else {
            channel.basicAck(deliveryTag, false);
        }
    }

    @Override
    public void getCreatedMessage2(@Payload String payload, @Header(AmqpHeaders.CHANNEL) Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
        log.info("message 2 queued: ".concat(payload));
        if (Integer.parseInt(payload) < 5) {
            channel.basicReject(deliveryTag, false);
        } else {
            channel.basicAck(deliveryTag, false);
        }
    }

    @Override
    public void getCreatedMessage3(@Payload String payload, @Header(AmqpHeaders.CHANNEL) Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
        log.info("message 3 queued: ".concat(payload));
        if (Integer.parseInt(payload) < 5) {
            channel.basicReject(deliveryTag, false);
        } else {
            channel.basicAck(deliveryTag, false);
        }
    }

    @Override
    public void getCreatedMessageDLQ(Message message, @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
        log.info("message DLQ queued: ".concat(Arrays.toString(message.getBody())));
        var xDeathHeaders = message.getMessageProperties().getXDeathHeader();
        if (!xDeathHeaders.isEmpty()) {
            var countDLQ = (Long) xDeathHeaders.get(0).get("count");
            if (countDLQ < 3) {
                var originalQueue = (String) xDeathHeaders.get(0).get("queue");
                rabbitTemplate.convertAndSend(StringUtils.EMPTY, originalQueue, message);
                channel.basicAck(deliveryTag, false);
            }
        }
    }
    
}