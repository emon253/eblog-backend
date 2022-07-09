package eblog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eblog.domain.Comment;
import eblog.domain.Post;
import eblog.dto.CommentDto;
import eblog.exception.ResourceNotFoundException;
import eblog.repository.CommentRepository;
import eblog.repository.PostRepository;
import eblog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto dto, int postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post ", " Id ", postId));
		Comment comment = this.modelMapper.map(dto, Comment.class);
		comment.setPosts(post);
		Comment save = this.commentRepository.save(comment);
		return this.modelMapper.map(save, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {

		Comment comment = this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", " ID ", commentId));
		this.commentRepository.delete(comment);
	}

}
