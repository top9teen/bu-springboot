package com.it.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.it.app.entity.UserAcount;

@Repository
public interface  UserAcountRepository extends JpaRepository<UserAcount, Long>{
	
	@Query(value = "SELECT * FROM user_acount WHERE user_id = ?1 AND status = 'A' ", nativeQuery = true)
	Optional<UserAcount> findOneByUserId(Long userId);
	
	@Query(value = "SELECT * FROM user_acount WHERE username = ?1 AND password = ?2 AND status = 'A' ", nativeQuery = true)
	Optional<UserAcount> findOneByUserNameAndPassWord(String userName,String passWord);
	
	@Query(value = "SELECT * FROM user_acount WHERE role = ?1 AND status = 'A' ", nativeQuery = true)
	List<UserAcount> findAllByRole(String role);
}
