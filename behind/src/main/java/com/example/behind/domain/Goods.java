package com.example.behind.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 36)
    private String name;

    @Lob
    @Column(name = "description", columnDefinition="TEXT")
    private String description;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "cost")
    private Integer cost;
}
