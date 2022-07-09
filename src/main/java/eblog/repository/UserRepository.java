package eblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eblog.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
