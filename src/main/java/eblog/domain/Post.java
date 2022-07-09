package eblog.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="posts")
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int postId;
	
	@Column(name = "post_titile", length = 100, nullable = false)
	private String title;
	
	@Column(length = 1000)
	private String content;
	
	private String image;
	
	private Date addedDate;
	@ManyToOne
	@JoinColumn(name = "catagory_id")
	private Catagory catagory;
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
	private Set<Comment> comments;
	
	
	
	
	
}
