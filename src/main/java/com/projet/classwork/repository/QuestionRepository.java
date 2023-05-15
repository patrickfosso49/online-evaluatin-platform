package com.projet.classwork.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.classwork.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
   
    public List<Question> findByQuestionnaire(Long id);
}
