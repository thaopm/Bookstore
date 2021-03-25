/**
 *
 * UserRepository.java, Sep 27, 2019 thaopm
 * 
 */
package com.pmt.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.pmt.bookstore.domain.User;

/**
 * @author thaopm
 *
 */
public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String userName); 
	
	User findByEmail(String email);
}
