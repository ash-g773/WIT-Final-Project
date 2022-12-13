package com.library.service;

import java.util.List;

import com.library.entity.Book;
import com.library.entity.Employee;
import com.library.entity.Library;

public interface LibraryService {
	
//	public Library borrowBook(Employee employee, Book book);
//	
//	public Library returnBook(Library library);
//	
//	public List<Library> getLibrariesByEmployeeId(int employeeId);
	
	//NAT HERE ---------------------------------------------------
	public List<Book> getBookList();
	
	public Library borrowBook2(int bookId, int copies, Employee employee);
	
	public List<Library> getBorrowedBooks();
	
	public Library returnBook2(String transactionId, int copies);
	
	public Library getRecord(String transactionId);

	Employee loginCheck(int id, String password);

	List<Library> getLibraryByEmployeeId(int employeeId);
	
	//they want us to take the type of book to be returned and issue date of that book 
	//how many copies of that book to return ? 
	
	
}