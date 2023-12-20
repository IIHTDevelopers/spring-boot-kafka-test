package com.kafkaapp.producer.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class LibraryEvent{
        Integer libraryEventId;
        LibraryEventType libraryEventType;
        @NotNull
        @Valid
        Book book;

	}
