/**
 *
 * UserServiceImpl.java, Sep 27, 2019 thaopm
 * 
 */
package com.pmt.bookstore.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pmt.bookstore.domain.ShoppingCart;
import com.pmt.bookstore.domain.User;
import com.pmt.bookstore.domain.UserShipping;
import com.pmt.bookstore.repository.PasswordResetTokenRepository;
import com.pmt.bookstore.repository.RoleRepository;
import com.pmt.bookstore.repository.UserRepository;
import com.pmt.bookstore.repository.UserShippingRepository;
import com.pmt.bookstore.security.PasswordResetToken;
import com.pmt.bookstore.security.UserRole;
import com.pmt.bookstore.service.UserService;

/**
 * @author thaopm
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG  = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
	private UserShippingRepository userShippingRepository;

	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(myToken);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public User createUser(User user, Set<UserRole> userRoles) throws Exception{
		User localUser = userRepository.findByUsername(user.getUsername());
		
		if(localUser != null) {
			LOG.info("User {} already exists.", user.getUsername());
		}else {
			for(UserRole ur:userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setUser(user);
			user.setShoppingCart(shoppingCart);
			
			
			
			localUser = userRepository.save(user);
		}
		return null;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void updateUserShipping(UserShipping userShipping, User user) {
		userShipping.setUser(user);
		userShipping.setUserShippingDefault(true);
		user.getUserShippingList().add(userShipping);
		save(user);		
	}


	@Override
	public void setUserDefaultShipping(Long userShippingId, User user) {
		List<UserShipping> userShippingList = (List<UserShipping>) userShippingRepository.findAll();
		
		for (UserShipping userShipping : userShippingList) {
			if(userShipping.getId() == userShippingId) {
				userShipping.setUserShippingDefault(true);
				userShippingRepository.save(userShipping);
			} else {
				userShipping.setUserShippingDefault(false);
				userShippingRepository.save(userShipping);
			}
		}
	}

	@Override
	public User findById(Long id) {
		return userRepository.findOne(id);
	}
	
}
