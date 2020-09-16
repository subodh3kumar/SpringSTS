package workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import workshop.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
