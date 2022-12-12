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
	
	//this is method get because we are getting the info from the database
	@GetMapping(path= "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	//login check - a get method again because we are getting stuff from the 
	@GetMapping(path = "/checks/{empId}/{password}")
	public Employee checkLogin(@PathVariable("empId") int id, @PathVariable("password") String password) {
		Employee returnEmp = employeeService.checkLoginIdAndPassword(id, password);
		return returnEmp;
	}
	
	//update book quantity
	@RequestMapping(path = "/updates/{empId}/{quantity}", method = RequestMethod.PUT, produces = MediaType.TEXT_PLAIN_VALUE)
	public String updateBookQuantity(@PathVariable("empId") int id, @PathVariable("quantity") int quantity) {
		if(employeeService.changeBookQuantity(id, quantity))
			return "Number of books borrowed updated";
		else return "Something went wrong...";
	}
}
