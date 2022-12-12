package com.library.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadLocalRandom;

import com.library.entity.Book;
import com.library.entity.BookList;
import com.library.entity.Employee;
import com.library.entity.Library;
import com.library.persistence.LibraryDao;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryDao libraryDao;

	//NAT HERE----------------------------------------------
	@Autowired
	private RestTemplate restTemplate;
	
	//getting the list of books for the employee to pick to borrow
	@Override
	public List<Book> getBookList() {
		
		ArrayList<Book> wholeBookList = new ArrayList<Book>();
		
		//calling book-service and storing books in bookList object
		BookList bookList = restTemplate.getForObject("http://localhost:8082/books", BookList.class);
		
		//need to use getter to get the list of books from object BookList
		for(Book book:bookList.getBooksList()) {
			wholeBookList.add(book);
		}
		
		return wholeBookList;
	}
	
	//borrow a book- will return a non-empty library object if can be borrowed;
	//will return null if too many copies taken or unable to update the number of copies in the book DB 
	//positive copies to be inputted if using my (Nats) impl, negative copies if using Roxanas impl 
	@Override
	public Library borrowBook2(int bookId, int copies, int employeeId, String password) {
		
		//dealing with number of copies is greater than the available number of copies
		//gets the book by bookId (rest API)
		Book bookToBorrow = restTemplate.getForObject("http://localhost:8082/books/" + bookId, Book.class);
		if(bookToBorrow.getNumberOfCopies()<copies)
			return null;
		
		//updates number of book copies available and outputs the updated message- CHECK WITH SUJATA 
		String updated = restTemplate.getForObject("http://localhost:8082/books/" + bookId + "/" + copies, String.class);
		//if copies not updated (i.e. book not borrowed then return null)
		if(updated != "Number of copies Updated!")
			return null;
		
		//getting the employees info to add to library 
		Employee myEmp = restTemplate.getForObject("http://localhost:8081/checks/" + employeeId +"/" + password, Employee.class);
		
		//todays date (format- YYYY-MM-DD)
		LocalDate issueDate = LocalDate.now();
		//todays date plus 7 days
		LocalDate expectedReturnDate = LocalDate.now().plusDays(7);
		
		//transaction id makes it complicated- for now just input 1
		//idea for transaction id- use rest API to access it from sql (resources then in here)- get all the transaction Id's as a collection 
		// and use lambdas to get the maximum transaction ID, then add 1 to it when inserting a new record into library- when borrowing a book
		//creating transaction id from employee id and book id
		String transId = Integer.toString(myEmp.getEmployeeId() + bookToBorrow.getBookId()) + issueDate.toString();
		//removed a null, not sure what its for ----- ?
		Library borrowedBook = new Library("string", myEmp.getEmployeeId(), myEmp.getEmployeeName(), bookToBorrow.getBookId(), bookToBorrow.getBookType(), issueDate, expectedReturnDate);
		
		//need to then add this borrowed book to the library database- im doing the save and update way to not deal with the exceptions
		//we can change later if needed - SAVE = SAVE AND UPDATE so if same transaction Id is being entered then will override i think? yes- 
		//if same transaction id then will override that id with new record- this is fine
		libraryDao.save(borrowedBook);
		
		return borrowedBook;
	
	}
	
	//--------------------------------------------------------
	
	@Override
	public Library borrowBook(Employee employee, Book book) {
		if (book.getNumberOfCopies() <= 0) {
			return null;
		}
		
		Library library = new Library();
//		library.setTransactionId(ThreadLocalRandom.current().nextInt(0, 2000000000));
		library.setEmployeeId(employee.getEmployeeId());
		library.setEmployeeName(employee.getEmployeeName());
		library.setBookId(book.getBookId());
		library.setBookType(book.getBookType());
		library.setReturnDate(null);


		return libraryDao.save(library);
	}
	
	@Override
	public Library returnBook(Library library) {
		library.setReturnDate(LocalDate.now());
		
		return libraryDao.save(library);
	}
		
		
	@Override
	public List<Library> getLibrariesByEmployeeId(int employeeId) {
		List<Library> libraries = libraryDao.findByEmployeeId(employeeId);
		return libraries;
	}
	
	@Override
	public boolean loginCheck(Employee employee) {
		try {
			Employee myEmp = restTemplate.getForObject("http://localhost:8081/checks/" + employee.getEmployeeId() +"/" + employee.getPassword(), Employee.class);

//			Library emp =libraryDao.findByEmployeeIdAndPassword(employee.getEmployeeId(), employee.getPassword());
			if(myEmp!=null)
				return true;
			return false;
		}
		catch(Exception ex) {
			return false;
		}
	
	}

}