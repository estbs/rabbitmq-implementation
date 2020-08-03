package com.ebs.springboot.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	public static final String QUEUE_CREATED_MESSAGES = "ebs.messages.created";
	public static final String EXCHANGE_CREATE_MESSAGES = "ebs.messages.create";

	@Bean
	public AmqpAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean
	FanoutExchange messagesExchange() {
		return new FanoutExchange(EXCHANGE_CREATE_MESSAGES);
	}

	@Bean
	Queue ebsMessagesQueue() {
		return new Queue(QUEUE_CREATED_MESSAGES, true);
	}

	@Bean
	Binding messagesExchangeToEbsMessagesQueue() {
		return BindingBuilder.bind(ebsMessagesQueue()).to(messagesExchange());
	}

}
