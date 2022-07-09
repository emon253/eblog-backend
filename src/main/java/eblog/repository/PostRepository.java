package eblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eblog.domain.Catagory;
import eblog.domain.Post;
import eblog.domain.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	public List<Post> findByUser(User user);
	
	public List<Post> findByCatagory(Catagory catagory);
	
	public List<Post> findByTitleContaining(String title);

	
	

}
