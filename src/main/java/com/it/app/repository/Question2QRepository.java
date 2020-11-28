package com.it.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.app.entity.Question2Q;

public interface Question2QRepository extends JpaRepository<Question2Q, String> {

	@Query(value = "SELECT qq.* FROM question2q qq WHERE qq.inspection_id = ?1 ", nativeQuery = true)
	List<Question2Q> findByInspectionId(int inspectionId);

}
