package eblog.service;
import java.util.List;

import eblog.dto.UserDto;

public interface UserService {
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, int id);
	
	UserDto getUserById(int id);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(int id);
}
