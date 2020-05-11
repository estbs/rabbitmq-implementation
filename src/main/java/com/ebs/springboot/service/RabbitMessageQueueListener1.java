/**
 * 
 */
package com.ebs.springboot.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import com.rabbitmq.client.Channel;

/**
 * @author esteban
 *
 */
public interface RabbitMessageQueueListener1 {

	@RabbitListener(admin = "rabbitAdmin", containerFactory = "rabbitListenerContainerFactory", queues = {"ebs.messages.queue"})
	void procesarMensaje(@Payload String message, @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag);
	
}
