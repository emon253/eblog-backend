package eblog.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CatagoryDto {

	private int catagoryId;
	
	@NotBlank
	@Size(min = 4)
	private String catagoryTitle;
	
	@NotBlank
	@Size(min = 4)
	private String catagoryDescription;
}
