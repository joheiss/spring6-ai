package com.jovisco.spring6_ai.services;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.jovisco.spring6_ai.model.Answer;
import com.jovisco.spring6_ai.model.CapitalRequest;
import com.jovisco.spring6_ai.model.Question;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatClient chatClient;

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource capitalPrompt;

    @Value("classpath:templates/get-capital-details-prompt.st")
    private Resource capitalDetailsPrompt;


    public OpenAIServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public String getAnswer(String question) {

        var response = chatClient.prompt()
                .user(question)
                .call()
                .content();

        log.info("Answer: " + response);

        return response;
    }

    @Override
    public Answer getAnswer(Question question) {

        log.info("Asking: " + question);
        return new Answer(getAnswer(question.question()));
    }

    @Override
    public Answer getCapital(CapitalRequest capitalRequest) {
        log.info("Asking: for capital of" + capitalRequest);

        var prompt = buildCapitalPrompt(capitalPrompt, capitalRequest);

        return new Answer(getAnswer(prompt.getContents()));
    }

    @Override
    public Answer getCapitalDetails(CapitalRequest capitalRequest) {
        log.info("Asking: for capital details of" + capitalRequest);

        var prompt = buildCapitalPrompt(capitalDetailsPrompt, capitalRequest);

        return new Answer(getAnswer(prompt.getContents()));
    }
        
    private Prompt buildCapitalPrompt(Resource template, CapitalRequest capitalRequest) {

        var promptTemplate = new PromptTemplate(template);
        
        return promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry()));
    }
}
