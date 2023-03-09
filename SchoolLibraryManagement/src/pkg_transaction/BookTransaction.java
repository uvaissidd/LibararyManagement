package pkg_transaction;

public class BookTransaction {
	private int isbn,rollno;
	
	private String issueDate, returnDate;

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public int getRollno() {
		return rollno;
	}

	public void setRollno(int rollno) {
		this.rollno = rollno;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public BookTransaction(int isbn, int rollno, String issueDate, String returnDate) {
		super();
		this.isbn = isbn;
		this.rollno = rollno;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}

	public BookTransaction() {
		super();
	}

	@Override
	public String toString() {
		return "BookTransaction [isbn=" + isbn + ", rollno=" + rollno + ", issueDate=" + issueDate + ", returnDate="
				+ returnDate + "]";
	}
	
}
