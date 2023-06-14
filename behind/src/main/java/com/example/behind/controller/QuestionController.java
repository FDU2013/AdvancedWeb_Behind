package com.example.behind.controller;

import com.example.behind.common.Result;
import com.example.behind.domain.Question;
import com.example.behind.domain.User;
import com.example.behind.domain.vo.AnswerData;
import com.example.behind.domain.vo.GetQuestionData;
import com.example.behind.service.QuestionService;
import com.example.behind.service.UserAccountService;
import com.example.behind.utils.DSTransTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserAccountService userService;

    @PostMapping("/getOne")
    public Result getOne(HttpServletRequest request, @RequestBody GetQuestionData data) {
        try {
            Question question = questionService.getNextQuestion(data.getTopic(), data.getPreviousIndex() );
            return Result.succ(DSTransTool.Question2VO(question));
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(623,"获取可兑换物信息失败");
        }
    }

    @PostMapping("/commitAnswer")
    public Result commitAnswer(HttpServletRequest request,@RequestBody AnswerData data) {
        try{
            System.out.println("data = " +data);
            boolean right = questionService.checkAnswer(data.getQuestionID(), data.getChoose());
            String userID = request.getHeader("userID");
            if(right) questionService.answerQuestion(data.getQuestionID(), data.getChoose(),userID);
            User user = userService.getUser(userID);
            Map<String,Object> map = new HashMap<>();
            map.put("score",user.getCredit());
            map.put("todayScore",user.getToday());
            map.put("right",right);
            if(right)
                return Result.succ(map,"回答正确");
            else
                return Result.succ(map,"回答错误");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(623,"回答信息有误");
        }
    }
}
