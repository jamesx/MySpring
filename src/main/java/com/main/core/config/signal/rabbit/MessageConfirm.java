package com.main.core.config.signal.rabbit;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

/**
 * 消息到达交换机时被调用
 * ack=true 成功 ,ack=false 失败
 */
@Component
public class MessageConfirm implements ConfirmCallback {

	protected Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (!ack) {
			logger.error("消息无法到达交换机[ack: " + ack + ",correlationData: " + correlationData + ",cause : " + cause+"].");
        }
	}

}
