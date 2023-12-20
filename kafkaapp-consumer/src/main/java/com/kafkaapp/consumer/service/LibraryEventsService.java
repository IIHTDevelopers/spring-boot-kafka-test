package com.kafkaapp.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaapp.consumer.entity.LibraryEvent;
import com.kafkaapp.consumer.jpa.LibraryEventsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Optional;

@Service
@Slf4j
public class LibraryEventsService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<Integer,String> kafkaTemplate;

    @Autowired
    private LibraryEventsRepository libraryEventsRepository;

    public void processLibraryEvent(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        //Write your logic here
    }

    private void validate(LibraryEvent libraryEvent) {
    	//Write your logic here
    }

    private void save(LibraryEvent libraryEvent) {
    	//Write your logic here
    }

    public void handleRecovery(ConsumerRecord<Integer,String> record){

    	//Write your logic here
    }

    private void handleFailure(Integer key, String value, Throwable ex) {
    	//Write your logic here
    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
    	//Write your logic here
    }
}
