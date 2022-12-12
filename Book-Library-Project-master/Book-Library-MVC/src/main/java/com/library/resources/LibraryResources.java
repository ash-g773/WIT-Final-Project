package com.library.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.service.LibraryService;

@RestController
public class LibraryResources {

	@Autowired
	private LibraryService libraryService;
	
//	@GetMapping(path = "/books/employees/{eid}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public LibraryList getBooksBorrowedByEmployee
}
