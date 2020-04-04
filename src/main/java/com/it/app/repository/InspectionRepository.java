package com.it.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.app.entity.Inspection;

public interface InspectionRepository extends JpaRepository<Inspection, Long>{
	@Query(value = "SELECT * FROM inspection WHERE status = 'A' ", nativeQuery = true)
	List<Inspection> findAllInspection();
	
	@Query(value = "SELECT * FROM inspection  WHERE inspection_id = ?1 and status = 'A' ", nativeQuery = true)
	Optional<Inspection> findOngById(Long inspectionId);
	
	@Query(value = "DELETE FROM inspection  WHERE inspection_id = ?1 ", nativeQuery = true)
	void deleteById(Long inspectionId);
}
