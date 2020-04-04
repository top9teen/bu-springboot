package com.it.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.app.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{
	@Query(value = "SELECT * FROM question  WHERE inspection_id = ?1 and status = 'A'", nativeQuery = true)
	List<Question> findListQuestionByInspectionId(Long inspectionId);
	
	@Query(value = "SELECT * FROM question and status = 'A' ", nativeQuery = true)
	List<Question> findAllQuestion();
	
	@Query(value = "SELECT * FROM question  WHERE question_id = ?1 and status = 'A'", nativeQuery = true)
	Optional<Question> findQuestionByQuestionId(Long questionId);
	/*@Sql(value = "DELETE \"assess\".\"question\" WHEWE \"question_id\" = ?1 ")
	void deleteQuestionById(Long QuestionId);*/
}
