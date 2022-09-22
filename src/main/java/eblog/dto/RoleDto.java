package eblog.dto;

import javax.persistence.Id;

import lombok.Data;

@Data
public class RoleDto {
	
	private int id;
	
	private String roleName;
}
