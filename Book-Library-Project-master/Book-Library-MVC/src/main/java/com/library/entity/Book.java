package com.library.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

	@Id
	private int bookId;
	private String bookName;
	private String bookType;
	private String bookAuthor;
	private String bookDescription;
	private int numberOfCopies;
	
}
