package com.book.persistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.book.entity.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {

	//search book by book id is already included in JpaRepository (by naming convention I believe)
	Book searchBookByBookId(int id);
	
	
	//get all books- not in here - service layer using .findall()
	
	
	//updateQuantity using JPQL
	//change in copies = +ve when want to burrow a book, negative when want to return a book
	@Modifying
	@Transactional
	@Query("update Book set numberOfCopies = numberOfCopies - :numb where bookId = :bookId")
	int updateCopies(@Param("numb") int bookId, @Param("bookId") int changeInCopies);
	
	
}