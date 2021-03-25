/**
 *
 * OrderService.java, Dec 6, 2019 thaopm
 * 
 */
package com.pmt.bookstore.service;

import com.pmt.bookstore.domain.Order;
import com.pmt.bookstore.domain.ShippingAddress;
import com.pmt.bookstore.domain.ShoppingCart;
import com.pmt.bookstore.domain.User;

/**
 * @author thaopm
 *
 */
public interface OrderService {
	Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, String shippingMethod, User user);
	
	Order findOne(Long id);
}
