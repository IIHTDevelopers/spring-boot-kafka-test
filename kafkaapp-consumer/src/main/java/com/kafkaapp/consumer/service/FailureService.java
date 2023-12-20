package com.kafkaapp.consumer.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import com.kafkaapp.consumer.entity.FailureRecord;
import com.kafkaapp.consumer.jpa.FailureRecordRepository;

@Service
public class FailureService {

    private FailureRecordRepository failureRecordRepository;

    public FailureService(FailureRecordRepository failureRecordRepository) {
        this.failureRecordRepository = failureRecordRepository;
    }

    public void saveFailedRecord(ConsumerRecord<Integer, String> record, Exception exception, String recordStatus){
        //Write your code here
    }
}
