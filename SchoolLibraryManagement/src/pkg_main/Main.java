package pkg_main;

import java.util.Scanner;

import pk_book.Book;
import pk_book.BookManager;
import pkg_exception.BookNotFoundException;
import pkg_exception.StudentNotFoundException;
import pkg_person.Student;
import pkg_person.StudentManager;
import pkg_transaction.BookTransactionManager;

public class Main {

	public static void main(String[] args) throws StudentNotFoundException {
		int choice;
		Scanner sc = new Scanner(System.in);
		BookManager bm = new BookManager();
		StudentManager sm = new StudentManager();
		BookTransactionManager btm = new BookTransactionManager();
		do {
			System.out.println("Enter 1 is Student\nEnter 2 if Librarian \nEnter 3 if you want to exit ");
			choice = sc.nextInt();
			if(choice == 1 ) {
				System.out.println("Enter Your roll Number");
				int rollno = sc.nextInt();
				try {
					Student s= sm.get(rollno);
					if(s==null)
						throw new StudentNotFoundException();
					int stud_choice;
					do {
						System.out.println("Enter 1 is To view all Books \nEnter 2 to search Book by INBT \nEnter 3 to List Books by Subjects \n Enter 4 to Issue a Book \nEnter 5 to Return a Book \nEnter 99 to Exit ");
						stud_choice = sc.nextInt();
						switch(stud_choice) {
						case 1:
							System.out.println("all books");
							bm.viewAllBooks();
							break;
						case 2: 
							System.out.println("isbn of the book");
							int search_isbn = sc.nextInt();
							Book book = bm.searchBookByIsbn(search_isbn);
							if (book == null ) {
								System.out.println("no book available");
							}
							else
								System.out.println(book);
							break;
						case 3: 
							System.out.println("enter a subject");
							sc.nextLine();
							String search_subject = sc.nextLine();
							bm.listBooksBySubject(search_subject);
							break;
						case 4 :
							System.out.println("enter isbn for issue a book");
							int issue_isbn =sc.nextInt();
							book = bm.searchBookByIsbn(issue_isbn);
							try {
							if(book !=null) {
								throw new BookNotFoundException();
							}
							if(book.getAvailable_quantity()>0) {
								if(btm.issueOfBook(rollno , issue_isbn)) {
									book.setAvailable_quantity(book.getAvailable_quantity()-1);
									System.out.println("book issued");
								}
							}
							else {
								System.out.println("the book has been issued");
							}
							
							}catch(BookNotFoundException bnfe){
								System.out.println(bnfe);
							}
								
							break;
						case 5 :
							System.out.println("enter isbn return a book");
							int return_isbn = sc.nextInt();
							book = bm.searchBookByIsbn(return_isbn);
							if(book !=null) {
								if(btm.returnOfBook(rollno, return_isbn)) {
									book.setAvailable_quantity(book.getAvailable_quantity()+1);
									System.out.println("Book returned");
								}
								else 
									System.out.println("Not return the book");
							}
							else
								System.out.println("Book does not exist");
							break;
						case 99 :
							System.out.println();
							break;
						default:
							System.out.println("Invalid Choice");
						}
					}while(stud_choice != 99);
				}catch(StudentNotFoundException snfe){
					System.out.println(snfe);
				}
			}
			else if(choice == 2) {
				int lib_choice;
				do {
				System.out.println("Enter 11 is To view all Students \nEnter 12 to to Print a Student by Roll Number \nEnter 13 to Register a Student \n Enter 14 to update a Student \nEnter 15 to Delete a Student");
				System.out.println("Enter 21 is To view all Books \nEnter 22 to search Book by INBT \nEnter 33 to add a New Book \n Enter 24 to Update a Book \nEnter 25 to Delete a Book ");
				System.out.println("Enter 31 is To view all Transactions \nEnter 99 to Exit ");
				lib_choice = sc.nextInt();
				switch(lib_choice) {
				case 11:  //view all students
					System.out.println("All Students record");
					sm.viewAllStudents();
					break;
				case 12: // search a student based on roll no
					System.out.println("Enter roll no to fetch student record");
					int get_rollno = sc.nextInt();
					Student student = sm.get(get_rollno);
					if(student == null) {
						System.out.println("No record found");
					}else {
						System.out.println(student);
					}
					break;
				case 13: // add student
					System.out.println("Enter Student details to add");
					String name;
					String emailId;
					String phoneNumber;
					String address;
					String dob;
					int rollno;
					int std;
					String division;
					sc.nextLine();
					System.out.println("Name");
					name = sc.nextLine();
					System.out.println("Email");
					emailId = sc.nextLine();
					System.out.println("Phone Number");
					phoneNumber = sc.nextLine();
					System.out.println("Address");
					address = sc.nextLine();
					System.out.println("Date Of Birth");
					dob = sc.nextLine();
					System.out.println("Roll Number");
					rollno = sc.nextInt();
					System.out.println("Standard");
					std = sc.nextInt();
					sc.nextLine();
					System.out.println("Division");
					division = sc.nextLine();
					student = new Student (name,emailId, phoneNumber, address, dob,rollno, std , division);
					sm.addAStudent(student);
					System.out.println("Student Details Added");
					break;
				case 14 :
					System.out.println("Enter the Roll Number to Update the Record");
					int modify_rollno = sc.nextInt();
					student = sm.get(modify_rollno);
					try {
					if(student == null) {
						throw new StudentNotFoundException();
					}
					sc.nextLine();
					System.out.println("Name");
					name = sc.nextLine();
					System.out.println("Email");
					emailId = sc.nextLine();
					System.out.println("Phone Number");
					phoneNumber = sc.nextLine();
					System.out.println("Address");
					address = sc.nextLine();
					System.out.println("Date Of Birth");
					dob = sc.nextLine();
					System.out.println("Standard");
					std = sc.nextInt();
					sc.nextLine();
					System.out.println("Division");
					division = sc.nextLine();
					sm.updateStudent(modify_rollno, name, emailId, phoneNumber, address, dob, std, division);
					System.out.println("updated");
					}catch (StudentNotFoundException snfe) {
						System.out.println(snfe);
					}
					break;
				case 15 :// to delete a student
					System.out.println("Enter the Roll Number to Update the Record");
					int delete_rollno = sc.nextInt();
					if(sm.deleteStudent(delete_rollno))
						System.out.println("Student Record is delete successfull");
					else
						System.out.println("No Record Exist");
					break;
				case 21: // view all books
					System.out.println("all books view");
					bm.viewAllBooks();
					break;
				case 22:// search book by isbn
					int search_isbn;
					System.out.println("isbn of the book");
					search_isbn = sc.nextInt();
					Book book = bm.searchBookByIsbn(search_isbn);
					if (book == null ) {
						System.out.println("no book available");
					}
					else
						System.out.println(book);
					break;
				case 23: // add new book 
					System.out.println("enter book details ");
					int isbn,edition, available_quantity;
					String title , author, publisher,subject;
					System.out.println("ISBN");
					isbn = sc.nextInt();
					
					sc.nextLine();
					
					System.out.println("ISBN");
					isbn = sc.nextInt();
					
					System.out.println("Title");
					title = sc.nextLine();
					
					System.out.println("Author");
					author = sc.nextLine();
					
					System.out.println("Publisher");
					publisher = sc.nextLine();
					
					System.out.println("Edition");
					edition = sc.nextInt();
					
					sc.nextLine();
					
					System.out.println("Subject");
					subject = sc.nextLine();
					
					System.out.println("Quantity");
					available_quantity = sc.nextInt();
					
					book = new Book(isbn,edition, available_quantity,title,author,publisher,subject);
					bm.addABook(book);
					System.out.println("book adeed ");
				
					break;
				case 24:
					System.out.println("enter isbn  update record");
					int update_isbn = sc.nextInt();
					try {
						book = bm.searchBookByIsbn(update_isbn);
						if(book==null)
							throw new BookNotFoundException();
						System.out.println("Title");
						title = sc.nextLine();
						
						System.out.println("Author");
						author = sc.nextLine();
						
						System.out.println("Publisher");
						publisher = sc.nextLine();
						
						System.out.println("Edition");
						edition = sc.nextInt();
						
						sc.nextLine();
						
						System.out.println("Subject");
						subject = sc.nextLine();
						
						System.out.println("Quantity");
						available_quantity = sc.nextInt();
						
						bm.updateBook(update_isbn,edition, available_quantity,title,author,publisher,subject);
						System.out.println("book adeed ");
					
					}catch(BookNotFoundException bnfe) {
						System.out.println(bnfe);
					}
					
					break;
				case 25:
					System.out.println("enter isbn  delete record");
					int delete_isbn = sc.nextInt();
					try {
						book = bm.searchBookByIsbn(delete_isbn);
						if(book==null)
							throw new BookNotFoundException();
						bm.deleteBook(delete_isbn);
						System.out.println("delete record");
					}catch(BookNotFoundException bnfe) {
						System.out.println(bnfe);
					}
					break;
				case 31:
					System.out.println("All the transaction");
					btm.showAll();
					break;
				case 99 :
					System.out.println();
					break;
				default:
					System.out.println("Invalid Choice");
				}
				}while(lib_choice == 99);
			}
		}while(choice != 3);
		sm.writeToFile();
		bm.writeToFile();
		btm.writeToFile();
		sc.close();
	}
	
}
























