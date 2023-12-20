package com.kafkaapp.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaapp.producer.domain.LibraryEvent;
import com.kafkaapp.producer.producer.LibraryEventProducer;
import com.kafkaapp.util.TestUtil;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static com.kafkaapp.testutils.TestUtils.*;

@ExtendWith(MockitoExtension.class)
public class LibraryEventProducerUnitTest {

    @Mock
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Spy
    ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    LibraryEventProducer eventProducer;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(eventProducer, "topic", "library-events");
    }

    @Test
    
    void testSendLibraryEvent_failure() throws IOException {
try {
    	//given

        LibraryEvent libraryEvent = TestUtil.libraryEventRecord();
        String record = objectMapper.writeValueAsString(libraryEvent);

        ProducerRecord<Integer, String> producerRecord = new ProducerRecord("library-events", libraryEvent.getLibraryEventId(), record);
        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition("library-events", 1),
                1, 1, System.currentTimeMillis(), 1, 2);
        SendResult<Integer, String> sendResult = new SendResult<>(producerRecord, recordMetadata);

        var completableFuture = CompletableFuture.supplyAsync(() -> sendResult)
                .thenApply((sendResult1) -> {
                    throw new RuntimeException("Exception Calling Kafka");
                })
    ;
        when(kafkaTemplate.send(isA(ProducerRecord.class)))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        completableFuture));

        //when

        var completableFuture1 = eventProducer.sendLibraryEvent(TestUtil.libraryEventRecord());

        //eventProducer.sendLibraryEvent_Approach2(TestUtil.libraryEventRecord()).get();
        var exception = assertThrows(Exception.class, completableFuture1::get);
        
 yakshaAssert(currentTest(),exception.getMessage().equals("Exception Calling Kafka"), businessTestFile);
        
    }catch(Exception e) {
		yakshaAssert(currentTest(),false, businessTestFile);
	}

    }

    @Test
    void testSendLibraryEvent_success() throws IOException {
    	try {
        //given
        LibraryEvent libraryEvent = TestUtil.libraryEventRecord();
        String record = objectMapper.writeValueAsString(libraryEvent);


        ProducerRecord<Integer, String> producerRecord = new ProducerRecord("library-events", libraryEvent.getLibraryEventId(), record);
        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition("library-events", 1),
                1, 1, System.currentTimeMillis(), 1, 2);
        SendResult<Integer, String> sendResult = new SendResult<Integer, String>(producerRecord, recordMetadata);


        var future = CompletableFuture.supplyAsync(() -> sendResult);
        when(kafkaTemplate.send(isA(ProducerRecord.class))).thenReturn(future);
        //when

        var completableFuture = eventProducer.sendLibraryEvent_Approach2(libraryEvent);

        //then
        SendResult<Integer, String> sendResult1 = completableFuture.get();
        
 yakshaAssert(currentTest(),sendResult1.getRecordMetadata().partition() == 1, businessTestFile);
        
    }catch(Exception e) {
		yakshaAssert(currentTest(),false, businessTestFile);
	}

    }
}
