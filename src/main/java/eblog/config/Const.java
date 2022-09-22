package eblog.config;

import eblog.domain.Role;

public class Const {

	public static final String PAGE_NUMBER = "0";
	
	public static final String PAGE_SIZE = "10";
	
	public static final String SORT_BY = "postId";
	
	public static final String SORT_DIR = "asc";
	
	public static final Integer NORMAL_USER = 501;
	
	public static final Integer ADMIN_USER = 502;
	
	public static final Role ROLE_ADMIN = new Role(ADMIN_USER, "ROLE_ADMIN");
	
	public static final Role ROLE_NORMAL = new Role(NORMAL_USER, "ROLE_NORMAL");

}
