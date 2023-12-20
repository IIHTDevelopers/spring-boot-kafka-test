package com.kafkaapp.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaapp.producer.controller.LibraryEventsController;
import com.kafkaapp.producer.domain.LibraryEvent;
import com.kafkaapp.producer.producer.LibraryEventProducer;
import com.kafkaapp.util.TestUtil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.kafkaapp.testutils.TestUtils.*;


@WebMvcTest(LibraryEventsController.class)
@AutoConfigureMockMvc
public class LibraryEventControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    LibraryEventProducer libraryEventProducer;

    @Test
    void testPostLibraryEventController() throws Exception {
    	try {
        //given

        LibraryEvent libraryEvent = TestUtil.libraryEventRecord();

        String json = objectMapper.writeValueAsString(libraryEvent);
        when(libraryEventProducer.sendLibraryEvent(isA(LibraryEvent.class))).thenReturn(null);

        //expect
        MvcResult result = mockMvc.perform(post("/v1/libraryevent")
        .content(json)
        .contentType(MediaType.APPLICATION_JSON)).andReturn();
                
yakshaAssert(currentTest(),result.getResponse().getStatus() == MockHttpServletResponse.SC_CREATED, businessTestFile);
        
    }catch(Exception e) {
		yakshaAssert(currentTest(),false, businessTestFile);
	}

    }

    
    @Test
    void testUpdateLibraryEventController() throws Exception {

        //given

try {
        String json = objectMapper.writeValueAsString(TestUtil.libraryEventRecordUpdate());
        when(libraryEventProducer.sendLibraryEvent(isA(LibraryEvent.class))).thenReturn(null);

        //expect
        MvcResult result = mockMvc.perform(
                put("/v1/libraryevent")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)).andReturn();
                
yakshaAssert(currentTest(),result.getResponse().getStatus() == MockHttpServletResponse.SC_OK, businessTestFile);
        
    }catch(Exception e) {
		yakshaAssert(currentTest(),false, businessTestFile);
	}

    }

   
}
