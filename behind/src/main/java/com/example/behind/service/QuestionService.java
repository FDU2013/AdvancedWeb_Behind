package com.example.behind.service;

import com.example.behind.domain.Question;

public interface QuestionService {
    Question getNextQuestion(String topic, Integer index);
    boolean checkAnswer(Long questionID, String option) throws Exception;
    boolean answerQuestion(Long questionID, String option, String userID) throws Exception;

}
