package com.jovisco.spring6_ai.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jovisco.spring6_ai.model.Answer;
import com.jovisco.spring6_ai.model.CapitalRequest;
import com.jovisco.spring6_ai.model.Question;
import com.jovisco.spring6_ai.services.OpenAIService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class QuestionController {

    private final OpenAIService openAIService;

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }

    @PostMapping("/capital")
    public Answer getCapital(@RequestBody CapitalRequest capitalRequest) {
        return openAIService.getCapital(capitalRequest);
    }

    @PostMapping("/capitalDetails")
    public Answer getCapitalDetails(@RequestBody CapitalRequest capitalRequest) {
        return openAIService.getCapitalDetails(capitalRequest);
    }

}
