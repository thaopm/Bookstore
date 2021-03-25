/**
 * Copyright(C) 2019 Luvina Software Company
 *
 * BookService.java, Oct 9, 2019 thaopm
 * 
 */
package com.pmt.adminportal.service;

import java.util.List;

import com.pmt.adminportal.domain.Book;

/**
 * @author thaopm
 *
 */
public interface BookService {
	Book save(Book book);
	
	List<Book> findAll();
	
	Book findOne(Long id);
}
