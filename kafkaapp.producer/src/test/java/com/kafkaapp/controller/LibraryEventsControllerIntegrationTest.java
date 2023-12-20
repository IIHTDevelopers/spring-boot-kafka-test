package com.kafkaapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaapp.producer.domain.LibraryEvent;
import com.kafkaapp.util.TestUtil;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static com.kafkaapp.testutils.TestUtils.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class LibraryEventsControllerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    KafkaTemplate<Integer, String > kafkaTemplate;

    @MockBean
    KafkaAdmin kafkaAdmin;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testPostLibraryEvent() throws IOException {

    	try {

        LibraryEvent libraryEvent = TestUtil.libraryEventRecord();

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<LibraryEvent> request = new HttpEntity<>(libraryEvent, headers);

        mockProducerCall(libraryEvent, objectMapper.writeValueAsString(libraryEvent));

        //when
        ResponseEntity<LibraryEvent> responseEntity = restTemplate.exchange("/v1/libraryevent", HttpMethod.POST, request, LibraryEvent.class);

        


        yakshaAssert(currentTest(),responseEntity.getStatusCode().equals(HttpStatus.CREATED), businessTestFile);
        
    }catch(Exception e) {
		yakshaAssert(currentTest(),false, businessTestFile);
	}

    }


    @Test
    void testPutLibraryEvent() throws IOException {
    	try {
        //given
        var libraryEventUpdate = TestUtil.libraryEventRecordUpdate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<LibraryEvent> request = new HttpEntity<>(libraryEventUpdate, headers);

        mockProducerCall(libraryEventUpdate, objectMapper.writeValueAsString(libraryEventUpdate));

        //when
        ResponseEntity<LibraryEvent> responseEntity = restTemplate.exchange("/v1/libraryevent", HttpMethod.PUT, request, LibraryEvent.class);

        

        yakshaAssert(currentTest(),responseEntity.getStatusCode().equals(HttpStatus.OK), businessTestFile);
        
    }catch(Exception e) {
		yakshaAssert(currentTest(),false, businessTestFile);
	}

    }

    private void mockProducerCall(LibraryEvent libraryEvent, String record) {
        //mock behavior
        ProducerRecord<Integer, String> producerRecord = new ProducerRecord("library-events", libraryEvent.getLibraryEventId(), record);
        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition("library-events", 1),
                1,1,System.currentTimeMillis(), 1, 2);
        SendResult<Integer, String> sendResult = new SendResult<Integer, String>(producerRecord,recordMetadata);
        var future = CompletableFuture.supplyAsync(()-> sendResult);
        when(kafkaTemplate.send(isA(ProducerRecord.class))).thenReturn(future);
    }

}