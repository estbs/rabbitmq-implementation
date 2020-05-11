/**
 * 
 */
package com.ebs.springboot.service.impl;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebs.springboot.service.RabbitMessageQueueProducer;

/**
 * @author esteban
 *
 */
@Service
public class RabbitMessageQueueProducerImpl implements RabbitMessageQueueProducer {
	
	public static final String BINDING_MESSAGES = "ebs.messages";
	public static final String EXCHANGE_MESSAGES = "topic.ebs.messages";
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void queueMessage(int messageNumber) {
		try {
			rabbitTemplate.convertAndSend(EXCHANGE_MESSAGES, BINDING_MESSAGES, "Message number: ".concat(Integer.toString(messageNumber)));
		} catch (AmqpException e) {
			System.out.println(e.getMessage());
		}
	}

}
