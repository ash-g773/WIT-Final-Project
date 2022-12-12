package com.employee.persistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>{

	public Employee findByEmployeeIdAndPassword(int employeeId, String password);
	
	@Modifying
	@Transactional
	@Query("UPDATE Employee SET bookQuantity=bookQuantity+:change where employeeId=:id")
	public int updateBookQuantity(@Param("change") int change, @Param("id") int id);
	//to use this function to increase book quantity, give positive value
	//to decrease book quantity, give negative value
}
