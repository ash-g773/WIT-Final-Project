package com.library.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.library.entity.Book;
import com.library.entity.Employee;
import com.library.entity.Library;
import com.library.service.LibraryService;

@Controller
public class LibraryController {

	@Autowired
	private LibraryService libraryService;
	
	
	@RequestMapping("/index")
	public ModelAndView indexPageController() {
		return new ModelAndView("index");
	}
	
	// =================Login Page Controller=======================
	
	@RequestMapping("/")
	public ModelAndView loginPageController() {
		return new ModelAndView("LoginPage", "employee", new Employee());
	}
	
	@RequestMapping("/login")
	public ModelAndView loginController(@ModelAttribute("employee") Employee employee, HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		
		Employee employeeDetails = libraryService.loginCheck(employee.getEmployeeId(), employee.getPassword());
		
		if(employeeDetails!=null) {
			modelAndView.addObject("employee", employeeDetails);  
			session.setAttribute("employee", employeeDetails);  
			modelAndView.setViewName("index");
		}
		else {
			modelAndView.addObject("message", "Invalid User Credentials, Please try again");
			modelAndView.addObject("employee", new Employee());
			modelAndView.setViewName("LoginPage");
		} 
			
		return modelAndView;
	}
	
	@RequestMapping("/viewCatalogue")
	public ModelAndView viewCatalogueController() {
		
		ModelAndView modelAndView=new ModelAndView();
		List<Book> libList=libraryService.getBookList();
		
		modelAndView.addObject("libraries", libList);
		modelAndView.setViewName("LibraryCatalogue");
		return modelAndView;
	}
	
	@RequestMapping("/viewBorrowedBooks")
	public ModelAndView viewBorrowedBooksController(HttpSession session) {
		
		ModelAndView modelAndView=new ModelAndView();
		
		Employee employee=(Employee)session.getAttribute("employee");
		List<Library> lib =  libraryService.getLibraryByEmployeeId(employee.getEmployeeId());
		
		if(lib.size() > 0)
		{
			modelAndView.addObject("libraries", lib);
			modelAndView.addObject("employeeId", employee.getEmployeeId());
			modelAndView.setViewName("BorrowedBooks");
		} else {
			modelAndView.addObject("message", "You have no current Borrowed Books");
			modelAndView.setViewName("BorrowedBooks");
		}
		return modelAndView;
	}
	
	@RequestMapping("/borrowBooks")
	public ModelAndView borrowBookController(HttpSession session) {
		
		ModelAndView modelAndView=new ModelAndView();
		
		Employee employee=(Employee)session.getAttribute("employee");
		
		
		
		
		return modelAndView;
	}
	
	
	
	

}


	

//@Override
//public List<Library> getBorrowedBooks() {
//	return libraryDao.findAll();
//}