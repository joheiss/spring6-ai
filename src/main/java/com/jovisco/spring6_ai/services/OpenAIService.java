package com.jovisco.spring6_ai.services;

import com.jovisco.spring6_ai.model.Answer;
import com.jovisco.spring6_ai.model.CapitalRequest;
import com.jovisco.spring6_ai.model.Question;

public interface OpenAIService {

    String getAnswer(String question);
    Answer getAnswer(Question question);
    Answer getCapital(CapitalRequest capitalRequest);
    Answer getCapitalDetails(CapitalRequest capitalRequest);
}
