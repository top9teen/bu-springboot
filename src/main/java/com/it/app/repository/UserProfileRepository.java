package com.it.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.it.app.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	
	List<UserProfile> findByCommunity(String community);
	
	@Query(value = "SELECT user_id FROM user_profile WHERE community = ?1", nativeQuery = true)
	List<String> findByUserIdCommunity(String community);
	
	@Query(value = "SELECT * FROM user_profile WHERE user_id = ?1", nativeQuery = true)
	Optional<UserProfile> findOneByUserId(Long userId);
	
	@Query(value = "SELECT * FROM user_profile WHERE profile_id = ?1", nativeQuery = true)
	Optional<UserProfile> findOneByProfileId(Long userId);
	
	@Query(value = "SELECT * FROM user_profile WHERE user_id = ?1 AND community = ?2", nativeQuery = true)
	Optional<UserProfile> findOneByUserIdAndCommunity(Long userId , String community);

	@Query(value = "SELECT * FROM user_profile WHERE community IS not NULL and community <> '' ", nativeQuery = true)
	List<UserProfile> findByIsNotNullCommunity();
	
	@Query(value = "SELECT * FROM user_profile WHERE user_id = ?1 limit 1", nativeQuery = true)
	UserProfile findOneByUserIdLimitOne(Long userId);
}
