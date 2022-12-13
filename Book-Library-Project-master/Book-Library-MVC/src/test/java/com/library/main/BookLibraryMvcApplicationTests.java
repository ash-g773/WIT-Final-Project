package com.library.main;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.library.entity.Book;
import com.library.entity.BookList;
import com.library.entity.Employee;
import com.library.entity.Library;
import com.library.service.LibraryServiceImpl;

@SpringBootTest
class BookLibraryMvcApplicationTests {
	
	@Autowired
	LibraryServiceImpl libraryServiceImpl;
	
	
	@Test
	void testGetBookList() {	
		assertTrue(libraryServiceImpl.getBookList().size()>0);
	}
	
	@Test
	void testBorrowBook2() {
		//pretending Bob is borrowing book 111
		//this is what should be returned from borrowBook2
		//transactionid = employeeIdBookIdIssueDate; say empId = 1, bookId=111, issueDate = 2022-12-01- need to add that to the database 
		Library borrowedBook = new Library("21112022-12-13", 2, "Martin", 111, "Data Analytics", LocalDate.now(), LocalDate.now().plusDays(7), LocalDate.now().plusDays(7), 0, 1);
		
		Employee emp = new Employee(2, "Martin", "password2", 0);
		
		assertEquals(borrowedBook, libraryServiceImpl.borrowBook2(111, 1, emp));
	}
	
	//put order here so that this is done before the testReturnBook()
	@Test
	void testGetBorrowedBooks() {	
		assertTrue(libraryServiceImpl.getBorrowedBooks().size()>0);
	}
	
	@Test
	void testReturnBook() {
		//pretending Bob is borrowing book 111
		//this is what should be returned from borrowBook2
		//transactionid = employeeIdBookIdIssueDate; say empId = 1, bookId=111, issueDate = 2022-12-01- need to add that to the database 
		Library returnBook = new Library("11112022-12-01", 1, "Bob", 111, "Data Analytics", LocalDate.of(2022, 12, 1) , LocalDate.of(2022, 12, 8), LocalDate.of(2022, 12, 13), 25, 1);
		
		assertEquals(returnBook, libraryServiceImpl.returnBook2("11112022-12-01", 1));
	}
	//check out the number of copies
	
	//check if dates need to be the same in borrowedBook2 and the library DB 

}