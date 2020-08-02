package com.ebs.springboot.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMqConfig {

	public static final String QUEUE_MESSAGES = "ebs.messages.queue";
	public static final String QUEUE_MESSAGES_DLQ = "ebs.messages.queue.dlq";
	public static final String BINDING_MESSAGES = "ebs.messages";
	public static final String EXCHANGE_MESSAGES = "topic.ebs.messages";

	public static final String DEAD_LETTER_EXCHANGE_ARG = "x-dead-letter-exchange";
	public static final String DEAD_LETTER_ROUTINGKEY_ARG = "x-dead-letter-routing-key";

	@Bean
	public AmqpAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean
	TopicExchange messagesExchange() {
		return new TopicExchange(EXCHANGE_MESSAGES);
	}

	@Bean
	Queue messagesQueue() {
		return QueueBuilder.durable(QUEUE_MESSAGES).withArgument(DEAD_LETTER_EXCHANGE_ARG, "")
				.withArgument(DEAD_LETTER_ROUTINGKEY_ARG, QUEUE_MESSAGES_DLQ).build();
	}

	@Bean
	Queue deadLetterQueue() {
		return QueueBuilder.durable(QUEUE_MESSAGES_DLQ).build();
	}

	@Bean
	Binding bindingMessages() {
		return BindingBuilder.bind(messagesQueue()).to(messagesExchange()).with(BINDING_MESSAGES);
	}

}
