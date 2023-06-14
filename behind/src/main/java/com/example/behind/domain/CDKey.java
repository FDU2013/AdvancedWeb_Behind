package com.example.behind.domain;

import com.example.behind.common.domain_attributes.CDKeyState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cd_key")
public class CDKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "goods")
    private Goods goods;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "get_time")
    private Date getTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private CDKeyState state;
}
