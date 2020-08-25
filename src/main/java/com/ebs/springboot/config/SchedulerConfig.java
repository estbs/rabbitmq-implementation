package com.ebs.springboot.config;

import java.util.Random;

import com.ebs.springboot.messages.MessagesProducer;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Configuration
@EnableScheduling
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SchedulerConfig {

    MessagesProducer messagesProducer;

    @Scheduled(cron = "${app.scheduler.producer}")
    public void scheduleProducerToSendMessage() {
        messagesProducer.queueMessage(new Random().nextInt(10));
    }
    
}