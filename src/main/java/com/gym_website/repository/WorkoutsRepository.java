package com.gym_website.repository;

import com.gym_website.entity.WorkoutsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutsRepository extends JpaRepository<WorkoutsEntity, Long> {
}
