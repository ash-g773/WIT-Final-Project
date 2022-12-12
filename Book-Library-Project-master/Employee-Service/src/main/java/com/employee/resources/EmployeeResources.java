package com.employee.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

@RestController
public class EmployeeResources {
	
	//we dont really need to test the dao because its done by jpa, but these test the functions we made using postman

	@Autowired
	private EmployeeService employeeService;
	
	//get employee by id
	@GetMapping(path = "/employees/{eid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee searchEmployeeByEmployeeId (@PathVariable("eid") int employeeId) {
		return employeeService.searchById(employeeId);
	}
	
	@RequestMapping(path= "/employees", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	//login check
	@GetMapping(path = "/checks/{empId}/{password}")
	public Employee checkLogin(@PathVariable("empId") int id, @PathVariable("password") String password) {
		Employee returnEmp = employeeService.checkLoginIdAndPassword(id, password);
		return returnEmp;
	}
	
	//update book quantity
	@GetMapping(path = "/updates/{empId}/{quantity}")
	public boolean updateBookQuantity(@PathVariable("empId") int id, @PathVariable("quantity") int quantity) {
		return employeeService.changeBookQuantity(id, quantity);
	}
}
