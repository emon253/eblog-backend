package eblog.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import eblog.domain.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class PostDto {

	private int postId;
	
	private String title;

	private String content;

	private String image;

	private Date addedDate;

	private CatagoryDto catagory;

	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
}
