package ru.t1consulting.letterscounter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.t1consulting.letterscounter.model.TextRequest;
import ru.t1consulting.letterscounter.service.LetterCounterService;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = LetterCounterController.class)
@ExtendWith(MockitoExtension.class)
class LetterCounterControllerTest {
    @Autowired
    ObjectMapper mapper;
    @MockBean
    private LetterCounterService letterCounterService;
    @Autowired
    private MockMvc mvc;


    @Test
    void countLetters_whenTextNotEmpty_thenResponseStatusOkWithMapInBody() throws Exception {
        TextRequest request = new TextRequest("аааббббвв");
        Map<Character, Integer> expectedResult = Map.of('б', 4, 'а', 3, 'в', 2);

        when(letterCounterService.countLetters(any(TextRequest.class)))
                .thenReturn(expectedResult);

        mvc.perform(post("/api/countLetters", request)
                        .content(mapper.writeValueAsString(request))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));

        verify(letterCounterService, times(1)).countLetters(any(TextRequest.class));
        verifyNoMoreInteractions(letterCounterService);
    }

    @Test
    void countLetters_whenTextEmpty_thenResponseClientError() throws Exception {
        TextRequest request = new TextRequest("");

        mvc.perform(post("/api/countLetters", request)
                        .content(mapper.writeValueAsString(request))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        verifyNoInteractions(letterCounterService);
    }
}