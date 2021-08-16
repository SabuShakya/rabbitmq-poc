package com.sabu.producerservice.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021-08-16
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = User.class)
/*
* implements the Serializable interface as it will be transmitted over a messaging system.
* The @JsonIdentityInfo annotation indicates the object identity during the serialization and deserialization process.
 * */
public class User implements Serializable {

    private String userId;

    private String userName;
}
