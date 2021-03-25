/**
 * Copyright(C) 2019 Luvina Software Company
 *
 * BookServiceImpl.java, Oct 9, 2019 thaopm
 * 
 */
package com.pmt.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmt.adminportal.domain.Book;
import com.pmt.adminportal.repository.BookRepository;
import com.pmt.adminportal.service.BookService;

/**
 * @author thaopm
 *
 */
@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;

	/* (non-Javadoc)
	 * @see com.pmt.adminportal.service.BookService#save(com.pmt.adminportal.domain.Book)
	 */
	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	/* (non-Javadoc)
	 * @see com.pmt.adminportal.service.BookService#findAll()
	 */
	@Override
	public List<Book> findAll() {
		return (List<Book>) bookRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.pmt.adminportal.service.BookService#findOne(java.lang.Long)
	 */
	@Override
	public Book findOne(Long id) {
		return bookRepository.findOne(id);
	}

}
