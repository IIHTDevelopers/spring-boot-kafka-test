package com.kafkaapp.consumer.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



public class FailureRecord {
   
    private Integer bookId;
    private String topic;
    private Integer key_value;
    private String errorRecord;
    private Integer partition;
    private Long offset_value;
    private String exception;
    private String status;

}
