/**
 *
 * ShippingAddressService.java, Dec 3, 2019 thaopm
 * 
 */
package com.pmt.bookstore.service;

import com.pmt.bookstore.domain.ShippingAddress;
import com.pmt.bookstore.domain.UserShipping;

/**
 * @author thaopm
 *
 */
public interface ShippingAddressService {
	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
