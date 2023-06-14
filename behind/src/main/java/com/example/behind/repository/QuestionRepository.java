package com.example.behind.repository;

import com.example.behind.domain.Question;
import com.example.behind.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findByTopic_NameAndIndex(String name, Integer index);
}
