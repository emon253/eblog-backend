package eblog.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "catagories")
@NoArgsConstructor
@Getter
@Setter
public class Catagory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int catagoryId;

	@Column(name = "title", length = 100, nullable = false)
	private String catagoryTitle;
	
	@Column(name = "description")
	private String catagoryDescription;

	@OneToMany(mappedBy = "catagory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts;
}
