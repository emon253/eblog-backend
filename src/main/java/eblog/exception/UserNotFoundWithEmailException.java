package eblog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserNotFoundWithEmailException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private String fieldValue;
	
	private String message;
	public UserNotFoundWithEmailException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s not found with %s : %d", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	public UserNotFoundWithEmailException(String message) {
		this.message = message;
	}
	
	
}
