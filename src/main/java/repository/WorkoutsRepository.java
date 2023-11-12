package repository;

import entity.WorkoutsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutsRepository extends JpaRepository<WorkoutsEntity, Long> {
}
