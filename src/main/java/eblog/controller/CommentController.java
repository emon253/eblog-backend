package eblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eblog.dto.ApiResponse;
import eblog.dto.CommentDto;
import eblog.service.CommentService;
import eblog.service.PostService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService service;


	@PostMapping("/post/{id}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int id) {
		CommentDto comment = this.service.createComment(commentDto, id);
	
		return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);

	}

	@DeleteMapping("/comments/{commentID}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId) {
		this.service.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted", true), HttpStatus.OK);

	}

}
