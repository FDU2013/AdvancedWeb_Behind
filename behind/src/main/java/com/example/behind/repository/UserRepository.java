package com.example.behind.repository;

import com.example.behind.common.domain_attributes.RoleType;
import com.example.behind.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByAccount_UserID(String account_UserID);
    List<User> findByAccount_Role(RoleType role);
}
