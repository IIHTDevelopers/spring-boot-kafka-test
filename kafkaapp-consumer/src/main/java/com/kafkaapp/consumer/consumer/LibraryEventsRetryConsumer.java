package com.kafkaapp.consumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafkaapp.consumer.service.LibraryEventsService;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LibraryEventsRetryConsumer {

    @Autowired
    private LibraryEventsService libraryEventsService;

   //add annotation for listener (Retry)
    public void onMessage(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {

        //write your code here

    }
}
