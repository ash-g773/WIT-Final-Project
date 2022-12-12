package com.library.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.entity.Library;

@Repository
public interface LibraryDao extends JpaRepository<Library, Integer>{
	
	List<Library> findByEmployeeId(int employeeId);

//	Book findBookByBookId(int bookId);
////	int updateBooks(int bookId, int numberOfCopies);
	
	
//	Library getDate(LocalDate now);
//	
	//THIS HERE- it throws an exception so need to catch it when used in the service layer
	//also there may be a problem with the other which hibernate uses- can use the save fucntion
	//instead in the service layer but this saves and updates (instead of just inserting)
//	//borrow a book - insert new row into library table
//	@Modifying
//	@Transactional
//	@Query("INSERT into Library values(transactionId:trId, employeeId:empId,  employeeName:empName, bookId:bkId, bookType:bookType, issueDate:issueDate, expectedReturnDate:expRet, returnDate:retDate, lateFee:lateFee)")
//	int insertBorrwedBook(@Param("trId") int trId, @Param("empId") int empId, @Param("empName") String empName, @Param("bkId") int bookId, @Param("bookType") String bookType, @Param("issueDate") LocalDate issueDate, @Param("expRet") LocalDate expRet, @Param("retDate") LocalDate retDate, @Param("lateFee") double lateFee);
//	
//	//leave this for returning
//	@Modifying
//	@Transactional
//	@Query("update Book set numberOfCopies = numberOfCopies - :numb, issueDate = :date where bookId = :bookId")
//	int updateBorrowedBoook(@Param("numb") int bookId, @Param("bookId") int changeInCopies, LocalDate issueDate);
	

//	Book findBookByBookId(int bookId);
////	int updateBooks(int bookId, int numberOfCopies);
	
	
//	Library getDate(LocalDate now);
//	
//	//borrow a book - insert new row into library table
//	@Modifying
//	@Transactional
//	@Query("INSERT into Library values(transactionId:trId, employeeId:empId,  employeeName:empName, bookId:bkId, bookType:bookType, issueDate:issueDate, expectedReturnDate:expRet, returnDate:retDate, lateFee:lateFee)")
//	int insertBorrwedBook(@Param("trId") int trId, @Param("empId") int empId, @Param("empName") String empName, @Param("bkId") int bookId, @Param("bookType") String bookType, @Param("issueDate") LocalDate issueDate, @Param("expRet") LocalDate expRet, @Param("retDate") LocalDate retDate, @Param("lateFee") double lateFee);
//	
//	//leave this for returning
//	@Modifying
//	@Transactional
//	@Query("update Book set numberOfCopies = numberOfCopies - :numb, issueDate = :date where bookId = :bookId")
//	int updateBorrowedBoook(@Param("numb") int bookId, @Param("bookId") int changeInCopies, LocalDate issueDate);
	
}