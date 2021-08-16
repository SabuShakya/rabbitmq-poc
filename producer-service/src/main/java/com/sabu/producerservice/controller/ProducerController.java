package com.sabu.producerservice.controller;

import com.sabu.producerservice.dto.User;
import com.sabu.producerservice.properties.RabbitMQProperties;
import com.sabu.producerservice.sender.RabbitMQSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021-08-16
 */
@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class ProducerController {

    private final RabbitMQSender rabbitMQSender;

    private final RabbitMQProperties rabbitMQProperties;

    @PostMapping("/user")
    private ResponseEntity<?> publishUserDetails(@RequestBody User user) {
        rabbitMQSender.send(user);
        return ResponseEntity.ok(rabbitMQProperties.getMessage());
    }
}
