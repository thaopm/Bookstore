/**
 *
 * BookRepository.java, Oct 11, 2019 thaopm
 * 
 */
package com.pmt.bookstore.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pmt.bookstore.domain.Book;

/**
 * @author thaopm
 *
 */
public interface BookRepository extends CrudRepository<Book, Long>{

	/**
	 * @param category
	 * @return
	 */
	List<Book> findByCategory(String category);

}
