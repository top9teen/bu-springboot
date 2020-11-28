package com.it.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.app.entity.Question8Q;

public interface Question8QRepository extends JpaRepository<Question8Q, Long> {

	@Query(value = "SELECT qq.* FROM question8q qq WHERE qq.inspection_id = ?1 ", nativeQuery = true)
	List<Question8Q> findByInspectionId(String inspectionId);

}
