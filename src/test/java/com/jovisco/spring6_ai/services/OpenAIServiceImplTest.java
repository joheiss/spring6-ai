package com.jovisco.spring6_ai.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OpenAIServiceImplTest {

    @Autowired
    OpenAIService openAIService;

    @Test
    void testGetAnswer() {
        var result = openAIService.getAnswer("What is the heaviest animal on earth?");
        System.out.println(result);
        System.out.flush();
        assertTrue(result.length() > 0);
    }
}
