package com.ebs.springboot.messages;

public interface MessagesProducer {

    void queueMessage(int messageNumber);

}