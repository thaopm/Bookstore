/**
 *
 * UserShippingServiceImpl.java, Dec 4, 2019 thaopm
 * 
 */
package com.pmt.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmt.bookstore.domain.UserShipping;
import com.pmt.bookstore.repository.UserShippingRepository;
import com.pmt.bookstore.service.UserShippingService;

/**
 * @author thaopm
 *
 */
@Service
public class UserShippingServiceImpl implements UserShippingService{
	
	@Autowired
	private UserShippingRepository userShippingRepository;

	@Override
	public UserShipping findById(Long id) {
		return userShippingRepository.findOne(id);
	}

	@Override
	public void removeById(Long id) {
		userShippingRepository.delete(id);	
	}

}
