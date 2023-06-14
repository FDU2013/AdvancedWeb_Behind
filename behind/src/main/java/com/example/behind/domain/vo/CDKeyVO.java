package com.example.behind.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CDKeyVO {
    private Long tradeID;
    private String userID;
    private Long itemID;
    private String imageURL;
    private String itemName;
    private Integer cost;
    private String description;
    private String code;
    private String time;
    private String state;
}
