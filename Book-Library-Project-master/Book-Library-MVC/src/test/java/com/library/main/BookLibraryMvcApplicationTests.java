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
import com.library.entity.Library;
import com.library.service.LibraryServiceImpl;

@SpringBootTest
class BookLibraryMvcApplicationTests {
	
	@Autowired
	LibraryServiceImpl libraryServiceImpl;
	
	
	@Test //runs + works
	void testGetBookList() {	
		assertTrue(libraryServiceImpl.getBookList().size()>0);
	}
	
	@Test
	void testBorrowBook2() {
		//pretending Bob is borrowing book 111
		//this is what should be returned from borrowBook2
		Library borrowedBook = new Library("11112022-12-12", 1, "Bob", 111, "Data Analytics", LocalDate.now(), LocalDate.now().plusDays(7));
		
		assertEquals(borrowedBook, libraryServiceImpl.borrowBook2(111, 1, 1, "password1"));
	}


}