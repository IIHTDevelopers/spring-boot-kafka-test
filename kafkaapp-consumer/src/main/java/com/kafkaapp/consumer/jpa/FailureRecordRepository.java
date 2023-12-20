package com.kafkaapp.consumer.jpa;

import org.springframework.data.repository.CrudRepository;

import com.kafkaapp.consumer.entity.FailureRecord;

import java.util.List;

public interface FailureRecordRepository extends CrudRepository<FailureRecord,Integer> {

    
}
