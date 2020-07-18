package com.it.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.app.entity.Criterion;

public interface CriterionRepository extends JpaRepository<Criterion, Long>{

	@Query(value = "DELETE FROM criterion  WHERE inspection_id = ?1 ", nativeQuery = true)
	void deleteByInspectionId(Long questionId);
	
	@Query(value = "SELECT * FROM criterion  WHERE inspection_id = ?1 and status = 'A' ORDER BY  criterion_start DESC ", nativeQuery = true)
	List<Criterion> findListCriterionByInspectionId(Long inspectionId);
	
	@Query(value = "SELECT * FROM criterion  WHERE status = 'A' ORDER BY  criterion_start DESC ", nativeQuery = true)
	List<Criterion> findListCriterionByInspectionIdALL();
	
}
