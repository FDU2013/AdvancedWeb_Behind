package com.example.behind.service.impl;

import com.example.behind.domain.Question;
import com.example.behind.domain.User;
import com.example.behind.repository.QuestionRepository;
import com.example.behind.repository.UserRepository;
import com.example.behind.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
    QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Autowired
    QuestionServiceImpl(QuestionRepository questionRepository,
                        UserRepository userRepository){
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Question getNextQuestion(String topic, Integer index) {
        Question question = questionRepository.findByTopic_NameAndIndex(topic, index + 1);
        if(question == null){
            question = questionRepository.findByTopic_NameAndIndex(topic,  1);
        }
        return question;
    }

    @Override
    public boolean checkAnswer(Long questionID, String option) throws Exception {
        System.out.println("questionID = " + questionID);
        Question question = questionRepository.findById(questionID).orElse(null);
        System.out.println("question = " + question);
        if(question == null){
            throw new Exception("question不存在");
        }
        return question.getAnswer().equals(option);
    }

    @Override
    public boolean answerQuestion(Long questionID, String option, String userID) throws Exception {
        boolean res = checkAnswer(questionID, option);
        if(res) {
            User user = userRepository.findByAccount_UserID(userID);
            if(user == null) {
                throw new Exception("user不存在");
            }
            if(user.getToday() + Question.score > User.todayMax){
                user.setToday(User.todayMax);
            } else {
                user.addCredit(Question.score);
            }
            userRepository.save(user);
        }
        return res;
    }
}
