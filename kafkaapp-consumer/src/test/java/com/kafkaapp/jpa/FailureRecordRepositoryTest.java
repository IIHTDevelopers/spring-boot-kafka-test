package com.kafkaapp.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.kafkaapp.consumer.config.LibraryEventsConsumerConfig;
import com.kafkaapp.consumer.entity.FailureRecord;
import com.kafkaapp.consumer.jpa.FailureRecordRepository;
import static com.kafkaapp.testutils.TestUtils.*;
@ActiveProfiles("test")
@DataJpaTest
class FailureRecordRepositoryTest {

    @Autowired
    FailureRecordRepository failureRecordRepository;

    @BeforeEach
    public void setUp(){
        var record = "{\"libraryEventId\":1,\"book\":{\"bookId\":456,\"bookName\":\"Kafka Using Spring Boot 2.X\",\"bookAuthor\":\"Yaksha\"}}";

        var failureRecord = new FailureRecord(null,"library-events", 123, record,1,0L, "exception occurred", LibraryEventsConsumerConfig.RETRY);
        var failureRecord1= new FailureRecord(null,"library-events", 123, record,1,1L, "exception occurred",LibraryEventsConsumerConfig.DEAD);

        failureRecordRepository.saveAll(List.of(failureRecord, failureRecord1));
    }

    @Test
    void testFindAllByStatus() {

        //when
        var failRecordList = failureRecordRepository.findAllByStatus(LibraryEventsConsumerConfig.RETRY);

        //then
        
        yakshaAssert(currentTest(),failRecordList.size() == 1, businessTestFile);
    }
}