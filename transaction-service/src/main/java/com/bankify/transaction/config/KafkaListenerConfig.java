package com.bankify.transaction.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaListenerConfig {

    private ObjectMapper objectMapper= new ObjectMapper();

    Map<String,String> transcationTopic;

    @Autowired
    KafkaTemplate<Object,Object> kafkaTemplate;

    public KafkaListenerConfig(){
        transcationTopic=new HashMap<>();

        transcationTopic.put("BANK1","BANK1-DO-TXN");
        transcationTopic.put("BANK2","BANK2-DO-TXN");
    }

    @KafkaListener(topics = "DO-TXN", groupId = "txn-grp")
    public void  consumerForDoTransaction(ConsumerRecord record) throws JsonProcessingException {



        Map<String,Object> map= objectMapper.readValue(record.value().toString(), HashMap.class);
        System.out.println(map);
        System.out.println((map.get("user1BankType")));
        System.out.println(transcationTopic.get((map.get("user1BankType").toString())));

        kafkaTemplate.send(transcationTopic.get((String)map.get("user1BankType")),Map.of("email",map.get("user1Email"),"amount",map.get("amount")));
        kafkaTemplate.send(transcationTopic.get((String)map.get("user2BankType")),Map.of("email",map.get("user2Email"),"amount",-1*((double)map.get("amount"))));

    }

}
