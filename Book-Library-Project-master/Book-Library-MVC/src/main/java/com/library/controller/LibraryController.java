package com.library.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
		return new ModelAndView("LoginPage", "library", new Library());
	}
	
	@RequestMapping("/login")
	public ModelAndView loginController(@ModelAttribute("employee") Employee employee, HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		
		if(libraryService.loginCheck(employee)) {
			modelAndView.addObject("employee", employee);  
			session.setAttribute("employee", employee);  
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
		List <Book> libList=libraryService.getBookList();
		
		modelAndView.addObject("libraries", libList);
		modelAndView.setViewName("LibraryCatalogue");
		return modelAndView;
	
	}

}
