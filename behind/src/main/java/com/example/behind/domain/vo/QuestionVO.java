package com.example.behind.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionVO {
    private Long questionID;
    private String image;
    private Integer index;
    private String title;
    private String A;
    private String B;
    private String C;
}
