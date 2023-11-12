package repository;

import entity.UserWeightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWeightRepository extends JpaRepository<UserWeightEntity, Long> {
}
