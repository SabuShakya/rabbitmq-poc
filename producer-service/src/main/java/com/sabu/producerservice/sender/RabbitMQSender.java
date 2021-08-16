package com.sabu.producerservice.sender;

import com.sabu.producerservice.dto.User;
import com.sabu.producerservice.properties.RabbitMQProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021-08-16
 * @info The job of this class is to send messages to RabbitMQ
 * */
@Service
@RequiredArgsConstructor
public class RabbitMQSender {

    /* The RabbitTemplate class allows sending and receiving messages with RabbitMQ. */
    private final RabbitTemplate rabbitTemplate;

    private final RabbitMQProperties rabbitMQProperties;

    /*
    * calls the convertAndSend() method of the RabbitTemplate class and sets exchange, routing, user to it.
    * This convertAndSend() method then pushes the message to exchange with the specified routing key.
    * */
    public void send(User user) {
        rabbitTemplate.convertAndSend(rabbitMQProperties.getExchange(), rabbitMQProperties.getRoutingkey(), user);
    }

}
