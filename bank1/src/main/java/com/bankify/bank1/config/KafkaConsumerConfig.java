package com.bankify.bank1.config;

import com.bankify.bank1.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    private ObjectMapper objectMapper= new ObjectMapper();

    @Autowired
    UserService userService;


    @KafkaListener(topics = "BANK1-DO-TXN", groupId = "BANK1-DO-TXN")
    public void consumerForBank1Transaction(ConsumerRecord record) throws JsonProcessingException {

        Map<String,Object> map= objectMapper.readValue(record.value().toString(), HashMap.class);

        userService.doTransaction(map);

    }



}
