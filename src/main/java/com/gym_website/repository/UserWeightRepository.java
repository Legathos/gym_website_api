package com.gym_website.repository;

import com.gym_website.entity.UserWeightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserWeightRepository extends JpaRepository<UserWeightEntity, Long> {
    @Query("SELECT uw FROM UserWeightEntity uw WHERE uw.user_id = :userId")
    List<UserWeightEntity> findByUserId(@Param("userId") Long userId);

}
