package com.employee.main;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*; //i had to import this manually? not sure why -- because mockito comes from spring so theres other methods in spring that might override these or something

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.employee.entity.Employee;
import com.employee.persistence.EmployeeDao;
import com.employee.service.EmployeeServiceImpl;

@SpringBootTest
class EmployeeServiceApplicationTests {
	//please refer to TCER excel file (TCER for Employee Service)

	//adding a copy of the service, the thing the mocks are injected into
	@InjectMocks
	private EmployeeServiceImpl service;
	
	//creating copies of the things we are mocking
	@Mock
	private EmployeeDao dao;
	private AutoCloseable autoCloseable; //what is this
	
	//stuff to do before each test
	@BeforeEach
	void setUp() throws Exception {
		//tells mockito to scan the test class and get everything marked as @Mock + initialize them as mocks
		autoCloseable = MockitoAnnotations.openMocks(this);
	}
	
	//closes i.e. gets rid of mocks
	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}
	
//	//this was here in the class already
//	@Test
//	void contextLoads() {
//	}
			
	
	//checkLoginIdAndPassword test
	
	//test case#1: positive - employee exists and password is correct - PASSED
	@Test
	void testValidLogin() {
		//simulating what the dao would do when an employee exists
		when(dao.findByEmployeeIdAndPassword(101, "password")).thenReturn(new Employee(101, "Test Name", "password", 2));
		
		Employee testEmp = new Employee(101, "Test Name", "password", 2);
		
		assertEquals(testEmp, service.checkLoginIdAndPassword(101, "password"));
		
	}
	
	//test case #2: negative - employee does not exist - PASSED
	@Test
	void testInvalidEmployeeLogin() {
		//simulating what the dao would do when an employee does not exist
		when(dao.findByEmployeeIdAndPassword(99, "password")).thenReturn(null);
		
		assertNull(service.checkLoginIdAndPassword(99, "password"));
	}
	
	//test case #3 negative - employee does exist but password is wrong (is this one just the same as test case#2 because the dao method still returns null?) - PASSED
	@Test
	void testInvalidPasswordLogin() {
		//simulating what the dao would do when an employee exists but the password is wrong
		when(dao.findByEmployeeIdAndPassword(101, "pass")).thenReturn(null);
		
		assertNull(service.checkLoginIdAndPassword(101, "pass"));
	}
	
	//changeBookQuantity test
	
	//test case #4: positive - employee exists and book quantity can be increased - PASSED
	@Test
	void testValidUpdateBookPositiveQuantity() {
		//simulating successful book quantity update on valid employee
		when(dao.updateBookQuantity(1, 101)).thenReturn(1);
		
		assertTrue(service.changeBookQuantity(101, 1));
	}
	
	//test case #5: negative - employee does not exist - PASSED
	@Test
	void testInvalidEmpForUpdateBookQuantity() {
		//simulating successful book quantity update on valid employee
			when(dao.updateBookQuantity(1, 99)).thenReturn(0);
			
			assertFalse(service.changeBookQuantity(99, 1));
		}

	//test case #5: negative - employee exists and book quantity can be decreased - PASSED
	@Test
	void testValidUpdateBookNegativeQuantity() {
		//simulating successful book quantity update on valid employee
			when(dao.updateBookQuantity(-1, 99)).thenReturn(1);
			
			assertTrue(service.changeBookQuantity(99, -1));
		}

	
}
