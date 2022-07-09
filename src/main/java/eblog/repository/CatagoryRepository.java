package eblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eblog.domain.Catagory;

public interface CatagoryRepository extends JpaRepository<Catagory, Integer>{

}
