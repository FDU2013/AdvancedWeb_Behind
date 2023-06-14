package com.example.behind.utils;

import com.example.behind.domain.CDKey;
import com.example.behind.domain.Goods;
import com.example.behind.domain.Question;
import com.example.behind.domain.User;
import com.example.behind.domain.vo.AdminUserInfoVO;
import com.example.behind.domain.vo.CDKeyVO;
import com.example.behind.domain.vo.GoodVO;
import com.example.behind.domain.vo.QuestionVO;

public class DSTransTool {
    public static GoodVO Good2VO(Goods good){
        return new GoodVO(
                good.getId(),
                good.getImgUrl(),
                good.getName(),
                good.getCost(),
                good.getDescription()
        );
    }

    public static CDKeyVO CDKey2VO(CDKey cdKey){
        return new CDKeyVO(
                cdKey.getId(),
                cdKey.getUser().getAccount().getUserID(),
                cdKey.getGoods().getId(),
                cdKey.getGoods().getImgUrl(),
                cdKey.getGoods().getName(),
                cdKey.getGoods().getCost(),
                cdKey.getGoods().getDescription(),
                cdKey.getCode(),
                cdKey.getGetTime().toString(),
                EnumTool.CDKeyState2Str(cdKey.getState())
        );
    }

    public static QuestionVO Question2VO(Question question){
        return new QuestionVO(
                question.getId(),
                question.getImg(),
                question.getIndex(),
                question.getTitle(),
                question.getOptionA(),
                question.getOptionB(),
                question.getOptionC()
        );
    }

    public static AdminUserInfoVO user2AdminVO(User user){
        return new AdminUserInfoVO(
                user.getAccount().getUserID(),
                user.getCredit(),
                user.getToday(),
                user.getHeadImgUrl()
        );
    }

}
