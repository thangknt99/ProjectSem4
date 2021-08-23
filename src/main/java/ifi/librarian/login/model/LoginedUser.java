package ifi.librarian.login.model;

public class LoginedUser {
	private String idUser;
	private String password;
	private int level;

	public LoginedUser() {

	}

	public LoginedUser(String idUser, String password, int level) {
		super();
		this.idUser = idUser;
		this.password = password;
		this.level = level;
	}

	public String getidUser() {
		return idUser;
	}

	public void setidUser(String idUser) {
		this.idUser = idUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
