package com.ebs.springboot.config;

import java.util.HashMap;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	public static final String EXCHANGE_CREATE_MESSAGES = "ebs.messages.create";
	public static final String QUEUE_CREATED_MESSAGES_1 = "ebs.messages.created.1";
	public static final String QUEUE_CREATED_MESSAGES_2 = "ebs.messages.created.2";
	public static final String QUEUE_CREATED_MESSAGES_3 = "ebs.messages.created.3";
	
	public static final String EXCHANGE_CREATE_MESSAGES_DLQ = "ebs.messages.create.dlq";
	public static final String BINDING_CREATE_MESSAGES_DLQ = "ebs.messages.dlq";
	public static final String QUEUE_CREATED_MESSAGES_DLQ = "ebs.messages.created.dlq";

	private static final String DEAD_LETTER_EXCHANGE_ARGUMENT = "x-dead-letter-exchange";
	private static final String DEAD_LETTER_ROUTINGKEY_ARGUMENT = "x-dead-letter-routing-key";

	@Bean
	public AmqpAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean
	FanoutExchange messagesExchange() {
		return new FanoutExchange(EXCHANGE_CREATE_MESSAGES);
	}

	@Bean
	DirectExchange messagesDLExchange() {
		return new DirectExchange(EXCHANGE_CREATE_MESSAGES_DLQ);
	}

	@Bean
	Queue ebsMessagesDLQueue() {
		return new Queue(QUEUE_CREATED_MESSAGES_DLQ, true);
	}

	@Bean
	Queue ebsMessagesQueue1() {
		var arguments = new HashMap<String, Object>();
		arguments.put(DEAD_LETTER_EXCHANGE_ARGUMENT, EXCHANGE_CREATE_MESSAGES_DLQ);
		arguments.put(DEAD_LETTER_ROUTINGKEY_ARGUMENT, BINDING_CREATE_MESSAGES_DLQ);
		return new Queue(QUEUE_CREATED_MESSAGES_1, true, false, false, arguments);
	}

	@Bean
	Queue ebsMessagesQueue2() {
		var arguments = new HashMap<String, Object>();
		arguments.put(DEAD_LETTER_EXCHANGE_ARGUMENT, EXCHANGE_CREATE_MESSAGES_DLQ);
		arguments.put(DEAD_LETTER_ROUTINGKEY_ARGUMENT, BINDING_CREATE_MESSAGES_DLQ);
		return new Queue(QUEUE_CREATED_MESSAGES_2, true, false, false, arguments);
	}

	@Bean
	Queue ebsMessagesQueue3() {
		var arguments = new HashMap<String, Object>();
		arguments.put(DEAD_LETTER_EXCHANGE_ARGUMENT, EXCHANGE_CREATE_MESSAGES_DLQ);
		arguments.put(DEAD_LETTER_ROUTINGKEY_ARGUMENT, BINDING_CREATE_MESSAGES_DLQ);
		return new Queue(QUEUE_CREATED_MESSAGES_3, true, false, false, arguments);
	}

	@Bean
    Binding ebsMessagesDLQBinding() {
        return BindingBuilder.bind(ebsMessagesDLQueue()).to(messagesDLExchange()).with(BINDING_CREATE_MESSAGES_DLQ);
    }

	@Bean
	Binding messagesExchangeToEbsMessagesQueue1() {
		return BindingBuilder.bind(ebsMessagesQueue1()).to(messagesExchange());
	}

	@Bean
	Binding messagesExchangeToEbsMessagesQueue2() {
		return BindingBuilder.bind(ebsMessagesQueue2()).to(messagesExchange());
	}

	@Bean
	Binding messagesExchangeToEbsMessagesQueue3() {
		return BindingBuilder.bind(ebsMessagesQueue3()).to(messagesExchange());
	}

}
