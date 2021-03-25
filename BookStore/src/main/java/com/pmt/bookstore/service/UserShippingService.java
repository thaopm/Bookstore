/**
 *
 * UserShippingService.java, Dec 4, 2019 thaopm
 * 
 */
package com.pmt.bookstore.service;

import com.pmt.bookstore.domain.UserShipping;

/**
 * @author thaopm
 *
 */
public interface UserShippingService {
	UserShipping findById(Long id);
	
	void removeById(Long id);
}
