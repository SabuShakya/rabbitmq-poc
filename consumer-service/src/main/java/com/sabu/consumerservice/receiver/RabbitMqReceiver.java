package com.sabu.consumerservice.receiver;

import com.sabu.consumerservice.properties.RabbitMQConsumerProperties;
import com.sabu.producerservice.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021-08-16
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMqReceiver implements RabbitListenerConfigurer {

    private final RabbitMQConsumerProperties mqConsumerProperties;

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receiveMessage(User user) {
        log.info("User Details Received ::::: {}", user);
    }
}
