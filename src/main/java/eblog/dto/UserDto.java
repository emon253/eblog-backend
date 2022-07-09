package eblog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;

	@NotEmpty
	@Size(min = 2, message = "Name must be minimum 2 characters")
	private String name;

	@Email(message = "Invalid email")
	@NotEmpty
	private String email;

	@NotBlank
	@Size(min = 2, message = "Password must be minimum 2 characters")
	private String password;

	@NotBlank
	@Size(min = 2)
	private String about;

}
