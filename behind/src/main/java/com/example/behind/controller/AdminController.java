package com.example.behind.controller;

import com.example.behind.common.MyPage;
import com.example.behind.common.Result;
import com.example.behind.domain.User;
import com.example.behind.domain.vo.AdminUserInfoVO;
import com.example.behind.domain.vo.PageData;
import com.example.behind.domain.vo.UserInfoRetVO;
import com.example.behind.service.UserAccountService;
import com.example.behind.utils.DSTransTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserAccountService userAccountService;

    @PostMapping("/getAllUserInfo")
    public Result getAllUserInfo(@RequestBody PageData pageData) {
        try {
            MyPage<User> userPage=userAccountService.getAllUser(pageData.getPageSize(), pageData.getPageNum());
            //System.out.println(userPage);
            MyPage<AdminUserInfoVO> ret = new MyPage<>();
            ret.setRecords(new ArrayList<>());
            ret.setTotal(userPage.getTotal());
            for (User user : userPage.getRecords()) {
                ret.getRecords().add(DSTransTool.user2AdminVO(user));
            }
            return Result.succ(ret);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(662, e.getMessage());
        }

    }


}
