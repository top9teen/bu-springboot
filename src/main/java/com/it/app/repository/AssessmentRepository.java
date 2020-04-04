package com.it.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.app.entity.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, Long>{
	@Query(value = "SELECT * FROM assessment  WHERE user_id = ?1 and inspection_id = ?2 order by create_date DESC LIMIT 10 ", nativeQuery = true)
	List<Assessment> findListAssessmentByUserId(Long userId,Long inspectionId);
	
	@Query(value = "SELECT * FROM assessment  WHERE inspection_id = ?1  ", nativeQuery = true)
	List<Assessment> findListAssessmentByInspectionId(Long inspectionId);
	
	@Query(value = "SELECT * FROM assessment  WHERE user_id = ?1 and inspection_id = ?2 ORDER BY create_date limit 1", nativeQuery = true)
	Optional<Assessment> findAssessmentByInspectionIdAndUserId(Long userId,Long inspectionId);
	
	@Query(value = "SELECT * FROM assessment  WHERE assessment_id = ?1 ", nativeQuery = true)
	Optional<Assessment> findOneByAssessmentId(Long assessmentId);
	
}
