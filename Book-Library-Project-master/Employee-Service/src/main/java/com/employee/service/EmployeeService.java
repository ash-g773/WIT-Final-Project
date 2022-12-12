package com.employee.service;

import java.util.List;

import com.employee.entity.Employee;

public interface EmployeeService {
	
	public Employee checkLoginIdAndPassword(int id, String password);
	
	public boolean changeBookQuantity(int id, int changeQuantity);

	Employee searchById(int id);

	List<Employee> getAllEmployees();
}
