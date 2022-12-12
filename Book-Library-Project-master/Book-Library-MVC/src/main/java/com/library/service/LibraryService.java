package com.library.service;

import java.util.List;

import com.library.entity.Book;
import com.library.entity.Employee;
import com.library.entity.Library;

public interface LibraryService {
	
	public Library borrowBook(Employee employee, Book book);
	
	public Library returnBook(Library library);
	
	public List<Library> getLibrariesByEmployeeId(int employeeId);
	
	//NAT HERE ---------------------------------------------------
	public List<Book> getBookList();
	
	public Library borrowBook2(int bookId, int copies, int employeeId, String password);

//	boolean loginCheck(Library library);

	Employee loginCheck2(int id, String password);
}