package com.it.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.app.entity.Choice;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {

	@Query(value = "SELECT * FROM choice  WHERE question_id = ?1 and status = 'A' order by choice_criterion desc ", nativeQuery = true)
	List<Choice> findListChoiceByQuestionId(Long questionId);
	
	@Query(value = "DELETE FROM choice  WHERE question_id = ?1 ", nativeQuery = true)
	void deleteByQuestionId(Long questionId);
}
