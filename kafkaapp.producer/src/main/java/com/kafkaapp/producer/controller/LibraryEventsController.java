package com.kafkaapp.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafkaapp.producer.domain.LibraryEvent;
import com.kafkaapp.producer.domain.LibraryEventType;
import com.kafkaapp.producer.producer.LibraryEventProducer;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LibraryEventsController {

    @Autowired
    LibraryEventProducer libraryEventProducer;

    @PostMapping("/v1/libraryevent")
    public ResponseEntity<?> postLibraryEvent(@RequestBody @Valid LibraryEvent libraryEvent) throws JsonProcessingException {

        return null;
    }

    //PUT
    @PutMapping("/v1/libraryevent")
    public ResponseEntity<?> putLibraryEvent(@RequestBody @Valid LibraryEvent libraryEvent) throws JsonProcessingException {


    	return null;
    }

    private static ResponseEntity<String> validateLibraryEvent(LibraryEvent libraryEvent) {
        
        return null;
    }


}
