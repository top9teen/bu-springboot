package com.it.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.app.entity.MenuMain;

public interface MenuMainRepository extends JpaRepository<MenuMain, Integer> {
@Query(value = "SELECT * FROM menu_main WHERE role = ?1 order by order_no", nativeQuery = true)
	List<MenuMain> findAllByRoue(String roue);
	
}
