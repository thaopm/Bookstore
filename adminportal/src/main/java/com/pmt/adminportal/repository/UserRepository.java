/**
 *
 * UserRepository.java, Sep 27, 2019 thaopm
 * 
 */
package com.pmt.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreFilter;

import com.pmt.adminportal.domain.User;

/**
 * @author thaopm
 *
 */
public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String userName); 
	
	User findByEmail(String email);
}
