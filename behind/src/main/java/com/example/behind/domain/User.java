package com.example.behind.domain;


import com.example.behind.common.domain_attributes.RoleType;
import com.example.behind.domain.dto.UserCreateData;
import javax.persistence.*;

import com.example.behind.domain.dto.UserUpdateData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 36)
    private String name;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "phone", length = 36)
    private String phone;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account", nullable = false)
    private Account account;

    @Column(name = "head_img_url")
    private String headImgUrl;

    @Column(name = "credit")
    private Integer credit;

    @Column(name = "today")
    private Integer today;

    public static final int todayMax = 50;

    public static final int defaultCredit = 0;

    public User(UserCreateData userData) {
        name = userData.getName();
        phone = userData.getPhone();
        email = userData.getEmail();
//        campus = userData.getCampus();
//        school = userData.getSchool();
//        major = userData.getMajor();
        Account account1 = new Account();
        account1.setUserID(userData.getUserID());
        account1.setPassword(userData.getPassword());
        account1.setStuNum(userData.getStuNum());
        account1.setRole(RoleType.User);
        account = account1;
        credit = defaultCredit;
    }

    public void update(UserUpdateData userData){
        if(userData.getEmail() != null) email = userData.getEmail();
        if(userData.getPhone() != null) phone = userData.getPhone();
        if(userData.getName() != null) name = userData.getName();
        if(userData.getHeadImgUrl() != null) headImgUrl = userData.getHeadImgUrl();
    }
    public void addCredit(Integer val){
        credit += val;
        today+=val;
    }
    public void subtractCredit(Integer val){
            credit -= val;
    }
}
