package com.it.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.app.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

	@Query(value = "SELECT * FROM user_profile WHERE user_id = ?1", nativeQuery = true)
	Optional<UserProfile> findOneByUserId(Long userId);
	
	@Query(value = "SELECT * FROM user_profile WHERE profile_id = ?1", nativeQuery = true)
	Optional<UserProfile> findOneByProfileId(Long userId);
	
	@Query(value = "SELECT * FROM user_profile WHERE user_id = ?1 AND community = ?2", nativeQuery = true)
	Optional<UserProfile> findOneByUserIdAndCommunity(Long userId , String community);
}
