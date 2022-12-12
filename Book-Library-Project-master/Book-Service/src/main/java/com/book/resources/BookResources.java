package com.book.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.book.entity.Book;
import com.book.service.BookService;

@RestController
public class BookResources {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping(path="books/{bid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Book searchBookByIdresource(@PathVariable("bid") int id) {
		return bookService.searchBookById(id);
	}
	
	@RequestMapping(path = "/books/",method = RequestMethod.GET,produces =MediaType.APPLICATION_JSON_VALUE)
	public List<Book> getAllBooksResource(){
		return bookService.getAllBooks();
	}
	
	@RequestMapping(path = "/books/{bId}/{copies}",method = RequestMethod.PUT,produces = MediaType.TEXT_PLAIN_VALUE)
	public String updateQuantityResource(@PathVariable("bId") int id,@PathVariable("copies") int noOfCopies) {
		if(bookService.updateQuantity(id, noOfCopies))
			return "Number of copies Updated!";
		else
			return "Number of copies not updated!";
	}

	
	
	
	
}