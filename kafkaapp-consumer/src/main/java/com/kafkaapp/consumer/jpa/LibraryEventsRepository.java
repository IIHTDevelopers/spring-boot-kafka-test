package com.kafkaapp.consumer.jpa;

import org.springframework.data.repository.CrudRepository;

import com.kafkaapp.consumer.entity.LibraryEvent;

public interface LibraryEventsRepository extends CrudRepository<LibraryEvent,Integer> {
}
