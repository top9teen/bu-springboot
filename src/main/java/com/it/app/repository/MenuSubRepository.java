package com.it.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.app.entity.MenuSub;

public interface MenuSubRepository  extends JpaRepository<MenuSub, Integer> {
	
	@Query(value = "SELECT * FROM menu_sub WHERE menu_id = ?1 order by order_no", nativeQuery = true)
	List<MenuSub> findAllByMenuId(Integer roue);
}
