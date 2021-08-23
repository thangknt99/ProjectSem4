package ifi.librarian.authormanagement.model;

public class Author {
	
	private int authorId;

	private String nameAuthor;

	private String description;

	public Author(int authorId,String nameAuthor,String description){
		this.authorId = authorId;
		this.nameAuthor = nameAuthor;
		this.description = description;
	}

	public Author() {
	}

	public int getauthorId() {
		return authorId;
	}

	public void setauthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getnameAuthor() {
		return nameAuthor;
	}

	public void setnameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

}
