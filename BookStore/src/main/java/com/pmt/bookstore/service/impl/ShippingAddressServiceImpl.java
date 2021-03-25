/**
 *
 * ShippingAdressServiceImpl.java, Dec 3, 2019 thaopm
 * 
 */
package com.pmt.bookstore.service.impl;

import org.springframework.stereotype.Service;

import com.pmt.bookstore.domain.ShippingAddress;
import com.pmt.bookstore.domain.UserShipping;
import com.pmt.bookstore.service.ShippingAddressService;

/**
 * @author thaopm
 *
 */
@Service
public class ShippingAddressServiceImpl implements ShippingAddressService{

	@Override
	public ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) {
		shippingAddress.setShippingAddressName(userShipping.getUserShippingName());
		shippingAddress.setShippingAddressStreet(userShipping.getUserShippingStreet());
		shippingAddress.setShippingAddressCity(userShipping.getUserShippingCity());
		shippingAddress.setShippingAddressState(userShipping.getUserShippingState());
		shippingAddress.setShippingAddressCountry(userShipping.getUserShippingCountry());
		shippingAddress.setShippingAddressZipcode(userShipping.getUserShippingZipcode());
		
		return shippingAddress;
	}
	
}
