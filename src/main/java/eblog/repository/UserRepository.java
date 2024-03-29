package eblog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import eblog.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
}
