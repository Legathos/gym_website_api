package com.gym_website.repository;

import com.gym_website.entity.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoggerRepository extends JpaRepository<LoggerEntity, Long> {

    @Query("SELECT le FROM LoggerEntity le WHERE le.user_id = :userId and le.date = :date")
    List<LoggerEntity> findByUserIdAndDate(@Param("userId") Long userId, @Param("date") Date date);

}
