/**
 *
 * SearchController.java, Oct 18, 2019 thaopm
 * 
 */
package com.pmt.bookstore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmt.bookstore.domain.Book;
import com.pmt.bookstore.domain.User;
import com.pmt.bookstore.service.BookService;
import com.pmt.bookstore.service.UserService;

/**
 * @author thaopm
 *
 */
@Controller
public class SearchController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/searchByCategory")
	public String searchByCategory(
			@RequestParam("category") String category,
			Model model, Principal principal
			) {
		if(principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		String classActiveCategory = "active"+category;
		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		classActiveCategory = classActiveCategory.replaceAll("&", "");
		model.addAttribute("classActiveCategory", true);
		
		List<Book> bookList = bookService.findByCategory(category);
		
		if(bookList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "bookshelf";
		}
		
		model.addAttribute("bookList", bookList);
		
		return "bookshelf";
	}
}
