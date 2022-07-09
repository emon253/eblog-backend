package eblog.service;

import java.util.List;

import eblog.dto.PostDto;
import eblog.dto.PostResponse;

public interface PostService {

	PostDto createPost(PostDto dto, int userId, int catagoryId);

	//update posts
	PostDto updatePost(PostDto dto, int postId);

	//	delete post
	void deletePost(int postId);
	
	//	get post by id
	PostDto getById(int postId);

	//	get post by catagory
	List<PostDto> getPostByCatagory(int catagoryId);
	
	//	get post by user
	List<PostDto> getPostByUser(int userId);
	
	//	get all post
	PostResponse getAllPost(int pageNumber, int pageSize, String sortBy,String sortDir);
	
	// search posts
	List<PostDto> searchPosts(String keyword);

}
