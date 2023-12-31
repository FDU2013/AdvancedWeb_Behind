package com.example.behind.service;

import com.example.behind.common.MyPage;
import com.example.behind.domain.dto.UserCreateData;
import com.example.behind.domain.User;
import com.example.behind.domain.dto.UserUpdateData;
import org.springframework.data.domain.Page;

public interface UserAccountService {
    User getUser(Long id);
    User getUser(String userID);
    MyPage<User> getAllUser(Integer pageSize, Integer pageNum);
    boolean checkLogin(String userID, String password) throws Exception;
    User createUser(UserCreateData userData) throws Exception;
    boolean updateUser(UserUpdateData userData) throws Exception;
    boolean changePassword(String userID, String oldPassword, String newPassword) throws Exception;
}
