/**
 * Copyright(C) 2019 Luvina Software Company
 *
 * BookRepository.java, Oct 9, 2019 thaopm
 * 
 */
package com.pmt.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.pmt.adminportal.domain.Book;

/**
 * @author thaopm
 *
 */
public interface BookRepository extends CrudRepository<Book, Long>{
	
}
