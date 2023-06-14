package com.example.behind.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sequence")
    private Integer index;

    @Column(name = "title")
    private String title;

    @Column(name = "option_aa")
    private String optionA;

    @Column(name = "option_ba")
    private String optionB;

    @Column(name = "option_ca")
    private String optionC;

    @Column(name = "answer")
    private String answer;

    @Column(name = "img")
    private String img;

    @ManyToOne
    @JoinColumn(name = "topic", nullable = false)
    private Topic topic;

    public static final int score = 10;
}
