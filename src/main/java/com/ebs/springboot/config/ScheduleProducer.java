package com.ebs.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ebs.springboot.service.RabbitMessageQueueProducer;

@Configuration
@EnableScheduling
public class ScheduleProducer {
	
	@Autowired
	private RabbitMessageQueueProducer rabbitMessageQueueProducer;

	@Scheduled(cron = "10 * * * * *")
	public void startSendMessages() {
		System.out.println("Start to send messages");
		int messageNumber = 0;
		
		while (messageNumber < 10) {
			messageNumber++;
			rabbitMessageQueueProducer.queueMessage(messageNumber);
		}
	}
}
