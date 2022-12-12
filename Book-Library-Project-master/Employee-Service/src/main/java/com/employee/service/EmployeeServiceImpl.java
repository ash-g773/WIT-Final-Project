package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.entity.EmployeeList;
import com.employee.persistence.EmployeeDao;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	//maybe we wont need this
	@Override
	public List<Employee> getAllEmployees() {
		return employeeDao.findAll();
	}
	
	@Override
	public Employee searchById(int id) {
		return employeeDao.findById(id).orElse(null);
	}

	@Override
	public Employee checkLoginIdAndPassword(int id, String password) {
		return employeeDao.findByEmployeeIdAndPassword(id, password);
	}

	@Override
	public boolean changeBookQuantity(int id, int changeQuantity) {
		if (employeeDao.updateBookQuantity(changeQuantity, id) > 0) return true;
		else return false;
	}

}
