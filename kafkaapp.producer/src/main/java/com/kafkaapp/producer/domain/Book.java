package com.kafkaapp.producer.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book{
        @NotNull
        Integer bookId;
        @NotBlank
        String bookName;
        @NotBlank
        String bookAuthor; 
}
