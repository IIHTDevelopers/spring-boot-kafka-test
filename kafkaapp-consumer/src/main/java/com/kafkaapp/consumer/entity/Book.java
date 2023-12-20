package com.kafkaapp.consumer.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



public class Book {
   
    private Integer bookId;
    private String bookName;
    private String bookAuthor;
    private LibraryEvent libraryEvent;
}
