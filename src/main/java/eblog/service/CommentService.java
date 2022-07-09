package eblog.service;

import eblog.dto.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto dto, int postId);
	
	void deleteComment(int commentId);
	
}
