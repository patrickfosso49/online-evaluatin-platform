package com.projet.classwork.repository.evaluation;

import com.projet.classwork.model.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropositionRepository extends JpaRepository<Proposition, Long> {
    @Query(value = "SELECT p.question_id FROM Proposition p WHERE p.id =:id ", nativeQuery = true)
    Long getQuestionID(@Param("id") Long id);

    @Query(value = "SELECT p.submission_id FROM Proposition p WHERE p.id =:id ", nativeQuery = true)
    Long getSubmissionID(@Param("id") Long id);

}
