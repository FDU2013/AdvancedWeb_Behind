package com.example.behind.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateData {
    String userID;
    String email;
    String phone;
    String name;
    String school;
    String major;
    String headImgUrl;
}
