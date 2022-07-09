package eblog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eblog.domain.User;
import eblog.dto.UserDto;
import eblog.exception.ResourceNotFoundException;
import eblog.repository.UserRepository;
import eblog.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = convertToUser(userDto);
		this.repository.save(user);
		return convertToUserDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int id) {

		User user = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		return convertToUserDto(this.repository.save(user));
	}

	@Override
	public UserDto getUserById(int id) {
		User user = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		return convertToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> userList = this.repository.findAll();
		List<UserDto> userDtoList = userList.stream().map(this::convertToUserDto)
				.collect(Collectors.toList());
		return userDtoList;
	}

	@Override
	public void deleteUser(int id) {
		User user = this.repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User", " Id ", id));
		this.repository.delete(user);

	}

	private User convertToUser(UserDto dto) {
		User user = modelMapper.map(dto, User.class);
		
//		user.setName(dto.getName());
//		user.setEmail(dto.getEmail());
//		user.setAbout(dto.getAbout());
//		user.setPassword(dto.getPassword());
//		user.setId(dto.getId());

		return user;
	}

	private UserDto convertToUserDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		

		return userDto;
	}
}
