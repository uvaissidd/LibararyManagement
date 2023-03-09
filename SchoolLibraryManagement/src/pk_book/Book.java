package pk_book;

public class Book {
	private int isbn,edition, available_quantity;
	private String title , author, publisher,subject;
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public int getEdition() {
		return edition;
	}
	public void setEdition(int edition) {
		this.edition = edition;
	}
	public int getAvailable_quantity() {
		return available_quantity;
	}
	public void setAvailable_quantity(int available_quantity) {
		this.available_quantity = available_quantity;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Book(int isbn, int edition, int available_quantity, String title, String author, String publisher,
			String subject) {
		super();
		this.isbn = isbn;
		this.edition = edition;
		this.available_quantity = available_quantity;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.subject = subject;
	}
	public Book() {
		super();
	}
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", edition=" + edition + ", available_quantity=" + available_quantity + ", title="
				+ title + ", author=" + author + ", publisher=" + publisher + ", subject=" + subject + "]";
	}
}
