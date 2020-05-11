/**
 * 
 */
package com.ebs.springboot.service;

/**
 * @author esteban
 *
 */
public interface RabbitMessageQueueProducer {

	void queueMessage(int messageNumber);
}
