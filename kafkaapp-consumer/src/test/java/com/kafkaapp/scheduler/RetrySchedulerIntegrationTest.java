package com.kafkaapp.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafkaapp.consumer.config.LibraryEventsConsumerConfig;
import com.kafkaapp.consumer.entity.FailureRecord;
import com.kafkaapp.consumer.jpa.FailureRecordRepository;
import com.kafkaapp.consumer.jpa.LibraryEventsRepository;
import com.kafkaapp.consumer.service.LibraryEventsService;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import static org.mockito.Mockito.times;
import static com.kafkaapp.testutils.TestUtils.*;

@SpringBootTest
@ActiveProfiles("test")
@Disabled
public class RetrySchedulerIntegrationTest {

    @SpyBean
    LibraryEventsService libraryEventsServiceSpy;

    @Autowired
    RetryScheduler retryScheduler;

    @Autowired
    FailureRecordRepository failureRecordRepository;

    @BeforeEach
    public void setUp(){

        failureRecordRepository.deleteAll();

        var record = "{\"libraryEventId\":1,\"book\":{\"bookId\":456,\"bookName\":\"Kafka Using Spring Boot 2.X\",\"bookAuthor\":\"Yaksha\"}}";

        var failureRecord = new FailureRecord(null,"library-events", 123, record,1,0L, "exception occurred", LibraryEventsConsumerConfig.RETRY);
        var failureRecord1= new FailureRecord(null,"library-events", 123, record,1,1L, "exception occurred",LibraryEventsConsumerConfig.DEAD);

        failureRecordRepository.saveAll(List.of(failureRecord, failureRecord1));
    }



    @Test
    @Disabled
    public void retryFailedRecords() throws JsonProcessingException {

        retryScheduler.retryFailedRecords();

        Mockito.verify(libraryEventsServiceSpy, times(1) ).processLibraryEvent(Mockito.isA(ConsumerRecord.class));
    }
}