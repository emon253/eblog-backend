package eblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eblog.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
