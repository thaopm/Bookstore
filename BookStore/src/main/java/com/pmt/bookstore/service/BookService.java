/**
 *
 * BookService.java, Oct 11, 2019 thaopm
 * 
 */
package com.pmt.bookstore.service;

import java.util.List;

import com.pmt.bookstore.domain.Book;

/**
 * @author thaopm
 *
 */
public interface BookService {
	List<Book> findAll();
	
	Book findOne(Long id);
	
	List<Book> findByCategory(String category);
}
