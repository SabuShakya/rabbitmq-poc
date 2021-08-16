package com.sabu.consumerservice.config;


import com.sabu.consumerservice.properties.RabbitMQConsumerProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021-08-16
 */
@Configuration
public class RabbitMQConfig {

    private final RabbitMQConsumerProperties rabbitMQProperties;

    public RabbitMQConfig(RabbitMQConsumerProperties rabbitMQProperties) {
        this.rabbitMQProperties = rabbitMQProperties;
    }

    @Bean
    Queue queue(){
//        return QueueBuilder.durable(properties.getQueue()).build();
//        OR
        return new Queue(rabbitMQProperties.getQueue(),true);
    }

    @Bean
    Exchange myExchange(){
        return ExchangeBuilder.directExchange(rabbitMQProperties.getExchange()).durable(true).build();
    }

    @Bean
    Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(myExchange())
                .with(rabbitMQProperties.getRoutingKey())
                .noargs();
    }

    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(rabbitMQProperties.getHost());
        cachingConnectionFactory.setUsername(rabbitMQProperties.getUsername());
        cachingConnectionFactory.setPassword(rabbitMQProperties.getPassword());
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
