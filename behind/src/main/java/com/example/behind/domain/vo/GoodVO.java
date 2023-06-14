package com.example.behind.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodVO {
    private Long itemID;
    private String imageURL;
    private String itemName;
    private Integer cost;
    private String description;
}
