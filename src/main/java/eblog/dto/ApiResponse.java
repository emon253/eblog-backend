package eblog.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ApiResponse {

	private String message;
	private boolean status;
	public ApiResponse(String message, boolean status) {
		this.message = message;
		this.status = status;
	}
	
	
}
