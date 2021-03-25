/**
 *
 * UserService.java, Sep 27, 2019 thaopm
 * 
 */
package com.pmt.bookstore.service;

import java.util.Set;

import com.pmt.bookstore.domain.User;
import com.pmt.bookstore.domain.UserShipping;
import com.pmt.bookstore.security.PasswordResetToken;
import com.pmt.bookstore.security.UserRole;

/**
 * @author thaopm
 *
 */
public interface UserService {
	PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	User findById(Long id);

	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
	
	void updateUserShipping(UserShipping userShipping, User user);
	
	void setUserDefaultShipping(Long userShippingId, User user);
}
