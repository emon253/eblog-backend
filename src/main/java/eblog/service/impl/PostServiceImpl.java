package eblog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import eblog.domain.Catagory;
import eblog.domain.Post;
import eblog.domain.User;
import eblog.dto.CatagoryDto;
import eblog.dto.PostDto;
import eblog.dto.PostResponse;
import eblog.dto.UserDto;
import eblog.exception.ResourceNotFoundException;
import eblog.repository.CatagoryRepository;
import eblog.repository.PostRepository;
import eblog.repository.UserRepository;
import eblog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CatagoryRepository catagoryRepository;

	@Override
	public PostDto createPost(PostDto dto, int userId, int catagoryId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		Catagory catagory = this.catagoryRepository.findById(catagoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Catagory", " Id ", catagoryId));

		dto.setAddedDate(new Date());
		dto.setImage("default.png");
		dto.setUser(this.modelMapper.map(user, UserDto.class));
		dto.setCatagory(this.modelMapper.map(catagory, CatagoryDto.class));
		return this.convertToDto(this.repository.save(convertToPost(dto)));
	}

	@Override
	public PostDto updatePost(PostDto dto, int postId) {
		Post post = this.repository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", " Id ", postId));

		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());
		post.setCatagory(this.modelMapper.map(dto.getCatagory(), Catagory.class));
		post.setImage(dto.getImage());

		return this.convertToDto(this.repository.save(post));
	}

	@Override
	public void deletePost(int postId) {
		Post post = this.repository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", " Id ", postId));
		this.repository.delete(post);
	}

	@Override
	public PostDto getById(int postId) {
		Post post = this.repository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", " Id ", postId));

		return this.convertToDto(post);
	}

	@Override
	public List<PostDto> getPostByCatagory(int catagoryId) {
		Catagory catagory = this.catagoryRepository.findById(catagoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Catagory", " Id ", catagoryId));
		List<Post> posts = this.repository.findByCatagory(catagory);
		List<PostDto> postDto = posts.stream().map(this::convertToDto).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getPostByUser(int userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
		List<Post> posts = this.repository.findByUser(user);
		List<PostDto> postsDto = posts.stream().map(this::convertToDto).collect(Collectors.toList());
		return postsDto;
	}

	@Override
	public PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = this.repository.findAll(pageable);

		List<Post> posts = pagePost.getContent();

		List<PostDto> postsDto = posts.stream().map(this::convertToDto).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();

		postResponse.setContent(postsDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.repository.findByTitleContaining(keyword);
		List<PostDto> postDto = posts.stream().map(this::convertToDto).collect(Collectors.toList());
		return postDto;
	}

	public PostDto convertToDto(Post post) {

		return modelMapper.map(post, PostDto.class);
	}

	public Post convertToPost(PostDto dto) {
		return modelMapper.map(dto, Post.class);
	}

}
