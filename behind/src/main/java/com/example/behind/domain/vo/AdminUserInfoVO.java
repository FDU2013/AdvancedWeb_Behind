package com.example.behind.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserInfoVO {
    private String userID;
    private Integer score;
    private Integer todayScore;
    private String avatar;
}
