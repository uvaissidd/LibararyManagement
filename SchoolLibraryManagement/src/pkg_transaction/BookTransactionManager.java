package pkg_transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookTransactionManager {
	ObjectOutputStream oos_book_transaction = null;
	ObjectInputStream ois_book_transaction = null;
	
	File book_transaction_file= null;
	
	ArrayList<BookTransaction> book_transaction_list = null;
	
	@SuppressWarnings("unchecked")
	public BookTransactionManager() {
		book_transaction_file = new File("Book_Transaction.dat");
		book_transaction_list = new ArrayList<BookTransaction>();
		if(book_transaction_file.exists()) {
			try {
				ois_book_transaction = new ObjectInputStream(new FileInputStream(book_transaction_file));
				book_transaction_list = (ArrayList<BookTransaction>) ois_book_transaction.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean issueOfBook(int rollno , int isbn) {
		int total_book_issued=0;
		
		for(BookTransaction book_transaction: book_transaction_list) { 
			if(book_transaction.getRollno() == rollno && (book_transaction.getReturnDate() == null))
				total_book_issued +=1;
			if(total_book_issued>=3)
				return false;
		}
		String issue_date = new SimpleDateFormat("dd-mm-yyyy").format(new Date());
		BookTransaction book_transaction = new BookTransaction(isbn,rollno,issue_date,null);
		book_transaction_list.add(book_transaction);
		return true;
	}
	public boolean returnOfBook(int rollno , int isbn) {
		int total_book_issued=0;
		
		for(BookTransaction book_transaction: book_transaction_list) { 
			if((book_transaction.getRollno() == rollno)  && (book_transaction.getIsbn()==isbn) && (book_transaction.getReturnDate() == null)) {
				String return_date = new SimpleDateFormat("dd-mm-yyyy").format(new Date());
				book_transaction.setReturnDate(return_date);
				return true;
			}
		}
		return false;
	}
	
	public void showAll() {
		for (BookTransaction book_transaction : book_transaction_list)
			System.out.println(book_transaction);
	}
	public void writeToFile()
	{
		try {
			oos_book_transaction = new ObjectOutputStream(new FileOutputStream(book_transaction_file));
			oos_book_transaction.writeObject(book_transaction_list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}