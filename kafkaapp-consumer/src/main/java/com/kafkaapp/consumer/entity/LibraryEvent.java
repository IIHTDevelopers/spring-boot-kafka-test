package com.kafkaapp.consumer.entity;


import jakarta.persistence.*;
import lombok.*;


public class LibraryEvent {

   
    private Integer libraryEventId;
   
    private LibraryEventType libraryEventType;
   
    private Book book;

}
