package com.example.behind.repository;

import com.example.behind.domain.CDKey;
import com.example.behind.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CDKeyRepository extends JpaRepository<CDKey, Long> {
    List<CDKey> findByUser(User user);
}
