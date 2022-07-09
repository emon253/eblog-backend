package eblog.controller;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import eblog.config.Const;
import eblog.domain.Post;
import eblog.dto.ApiResponse;
import eblog.dto.PostDto;
import eblog.dto.PostResponse;
import eblog.service.FileService;
import eblog.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService service;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	

	// create post
	@PostMapping("/user/{userId}/category/{catagoryId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto, @PathVariable int userId,
			@PathVariable int catagoryId) {

		PostDto post = this.service.createPost(dto, userId, catagoryId);
		return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);

	}

	// get post by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId) {
		List<PostDto> postsDto = this.service.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postsDto, HttpStatus.OK);
	}
	// get posts by category

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryId) {
		List<PostDto> posts = this.service.getPostByCatagory(categoryId);

		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// get post by id
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostDto> getPostByid(@PathVariable int id) {
		return ResponseEntity.ok(this.service.getById(id));
	}

	// get all post
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost( 
			@RequestParam(value = "pageNumber",defaultValue = Const.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = Const.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = Const.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = Const.SORT_DIR, required = false) String sortDir

			) {
		  PostResponse allPost = this.service.getAllPost(pageNumber, pageSize,sortBy,sortDir );
		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
	}

	// delete post

	@DeleteMapping("/posts/{id}")
	public ApiResponse deletePost(@PathVariable int id) {
		this.service.deletePost(id);
		return new ApiResponse("Post Id "+id+" deleted!!", true);
	}

	// update posts
	@PutMapping("/posts/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto dto, @PathVariable int id) {
		PostDto postDto = this.service.getById(id);

		postDto.setTitle(dto.getTitle());
		postDto.setContent(dto.getContent());
		postDto.setImage(dto.getImage());
		
		return new ResponseEntity<PostDto>(this.service.updatePost(postDto, id), HttpStatus.CREATED);

	}
	
	// Search post
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> getPostByid(@PathVariable String keywords) {
		return ResponseEntity.ok(this.service.searchPosts(keywords)); 
	}
	
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage( @RequestParam("image") MultipartFile image, @PathVariable("postId") int postId) throws IOException{
		PostDto postDto = this.service.getById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImage(fileName);
		PostDto updatePost = this.service.updatePost(postDto, postId);
		return ResponseEntity.ok(updatePost);
		
	}
}
