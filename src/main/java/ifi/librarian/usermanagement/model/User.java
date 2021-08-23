package ifi.librarian.usermanagement.model;

public class User {
	
	private String userId;

	private int level;

	public User(String userId, int level){
		this.userId = userId;
		this.level = level;
	}

	public User() {
	}

	public String getuserId() {
		return userId;
	}

	public void setuserId(String userId) {
		this.userId = userId;
	}

	public int getlevel() {
		return level;
	}

	public void setlevel(int level) {
		this.level = level;
	}

}
