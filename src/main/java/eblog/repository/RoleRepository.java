package eblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eblog.domain.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {

}
