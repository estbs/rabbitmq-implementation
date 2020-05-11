/**
 * 
 */
package com.ebs.springboot.service.impl;

import java.io.IOException;
import java.util.Random;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ebs.springboot.service.RabbitMessageQueueListener1;
import com.rabbitmq.client.Channel;

/**
 * @author esteban
 *
 */
@Component
public class RabbitMessageQueueListener1Impl implements RabbitMessageQueueListener1 {

	@Override
	public void procesarMensaje(@Payload String message, @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) {
		System.out.println("listener1: ".concat(message));
		try {
			if (new Random().nextBoolean()) {
				// channel.basicNack(deliveryTag, false, true);
				channel.basicReject(deliveryTag, false);
				// channel.basicReject(deliveryTag, true);
	        } else {
	        	channel.basicAck(deliveryTag, true);
	        }
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
