package com.book.main;

import static org.mockito.Mockito.when;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.book.entity.Book;
import com.book.persistence.BookDao;
import com.book.service.BookServiceImpl;

@SpringBootTest
class BookServiceApplicationTests {

	//need to instantiate service layer as well as dao layer (dao will be mocked)
	private BookServiceImpl bookServiceImpl;
	private BookDao bookDao;
	
	@BeforeEach
	void setUp() throws Exception{
		
		bookServiceImpl = new BookServiceImpl();
		
		bookDao = Mockito.mock(BookDao.class);
		//this works thanks to the @Setter in the service layer impl
		bookServiceImpl.setBookDao(bookDao);	
	}
	
	@AfterEach
	void tearDown() throws Exception{
		
	}

	//get all books - negative instance (assert false - if possible)- no
	@Test
	void testGetAllBooks() {
		
		List<Book> bookList = null;
		
		when(bookDao.findAll()).thenReturn(bookList);
		
		assertEquals(bookList, bookServiceImpl.getAllBooks());
	}
	
	
	
	//search book by id (positive) -passed
	@Test
	void testSearchBookById() {
		when(bookDao.searchBookByBookId(111)).thenReturn(new Book(111, "The Founders", "Data Analytics", "Jimmy Soni", "Description for 111 book", 5 ));
		
		Book myBook = new Book(111, "The Founders", "Data Analytics", "Jimmy Soni", "Description for 111 book", 5 );
	
		assertEquals(myBook, bookServiceImpl.searchBookById(111));
	}

	//search book by id (negative) -passed
	@Test
	void testSearchBookByIdNegative() {
		when(bookDao.searchBookByBookId(0)).thenReturn(null);
		
		Book myBook = null;
	
		assertEquals(myBook, bookServiceImpl.searchBookById(0));
	}
	
	
	//update quantity of books (positive) - could I also test for if the quantity has been updated? TRY IT 
	@Test
	void testUpdateQuantityPositive() {
		when(bookDao.updateCopies(111, 5)).thenReturn(1);
		
		assertTrue(bookServiceImpl.updateQuantity(111, 5));
		
	}
	
	//update quantity of books (negative) 
	@Test
	void testUpdateQuantityNegative() {
		when(bookDao.updateCopies(0, 5)).thenReturn(0);
		
		assertFalse(bookServiceImpl.updateQuantity(0, 5));
		
	}

}