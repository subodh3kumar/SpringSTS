package workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workshop.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
