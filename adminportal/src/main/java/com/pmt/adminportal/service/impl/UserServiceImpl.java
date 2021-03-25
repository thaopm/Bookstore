/**
 *
 * UserServiceImpl.java, Sep 27, 2019 thaopm
 * 
 */
package com.pmt.adminportal.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmt.adminportal.domain.Book;
import com.pmt.adminportal.domain.User;
import com.pmt.adminportal.repository.RoleRepository;
import com.pmt.adminportal.repository.UserRepository;
import com.pmt.adminportal.security.UserRole;
import com.pmt.adminportal.service.UserService;

/**
 * @author thaopm
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG  = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	private UserRepository userRepository;

	/* (non-Javadoc)
	 * @see com.pmt.bookstore.service.UserService#createUser(com.pmt.bookstore.domain.User, java.util.Set)
	 */
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception{
		User localUser = userRepository.findByUsername(user.getUsername());
		
		if(localUser != null) {
			LOG.info("User {} already exists.", user.getUsername());
		}else {
			for(UserRole ur:userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
			localUser = userRepository.save(user);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.pmt.bookstore.service.UserService#save(com.pmt.bookstore.domain.User)
	 */
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}


	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}


	
	
	
}
