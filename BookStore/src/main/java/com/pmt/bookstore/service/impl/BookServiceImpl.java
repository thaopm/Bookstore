/**
 *
 * BookServiceImpl.java, Oct 11, 2019 thaopm
 * 
 */
package com.pmt.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmt.bookstore.domain.Book;
import com.pmt.bookstore.repository.BookRepository;
import com.pmt.bookstore.service.BookService;

/**
 * @author thaopm
 *
 */
@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;

	/* (non-Javadoc)
	 * @see com.pmt.bookstore.service.BookService#findAll()
	 */
	@Override
	public List<Book> findAll() {
		return (List<Book>) bookRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.pmt.bookstore.service.BookService#findOne(java.lang.Long)
	 */
	@Override
	public Book findOne(Long id) {
		return bookRepository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.pmt.bookstore.service.BookService#findByCategory(java.lang.String)
	 */
	@Override
	public List<Book> findByCategory(String category) {
		List<Book> bookList = bookRepository.findByCategory(category);
		
		List<Book> activeBookList = new ArrayList<>();
		
		for(Book book : bookList) {
			if(book.isActive()) {
				activeBookList.add(book);
			}
		}
		return activeBookList;
	}
	
}
