/**
 * 
 */
package com.ebs.springboot.service.impl;

import java.io.IOException;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ebs.springboot.service.RabbitMessageQueueListener2;
import com.rabbitmq.client.Channel;

/**
 * @author esteban
 *
 */
@Component
public class RabbitMessageQueueListener2Impl implements RabbitMessageQueueListener2 {

	@Override
	public void procesarMensaje(@Payload String message, @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) {
		System.out.println("listener2: ".concat(message));
		try {
			// channel.basicAck(deliveryTag, false);
			channel.basicNack(deliveryTag, false, true);
			message = message.concat(" - requeued from listener2");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
