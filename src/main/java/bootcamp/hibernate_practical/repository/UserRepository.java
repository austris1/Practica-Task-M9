package bootcamp.hibernate_practical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import bootcamp.hibernate_practical.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
