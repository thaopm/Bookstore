/**
 *
 * UserService.java, Sep 27, 2019 thaopm
 * 
 */
package com.pmt.adminportal.service;

import java.util.List;
import java.util.Set;

import com.pmt.adminportal.domain.User;
import com.pmt.adminportal.security.UserRole;

/**
 * @author thaopm
 *
 */
public interface UserService {

	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);

	List<User> findAll();

}
