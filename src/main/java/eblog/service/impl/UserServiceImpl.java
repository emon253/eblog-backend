package eblog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import eblog.config.Const;
import eblog.domain.Role;
import eblog.domain.User;
import eblog.dto.UserDto;
import eblog.exception.ResourceNotFoundException;
import eblog.repository.RoleRepository;
import eblog.repository.UserRepository;
import eblog.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = convertToUser(userDto);
		
		this.repository.save(user);
		
		user.getRoles().add(Const.ROLE_NORMAL);
		
		return convertToUserDto(user);
	}
	
	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = convertToUser(userDto);
		
		user.getRoles().add(Const.ROLE_ADMIN);
		
		User newUser = this.repository.save(user);
		
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
		user.setPassword(this.passwordEncoder.encode(dto.getPassword()));

		return user;
	}

	private UserDto convertToUserDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		

		return userDto;
	}

	
}
