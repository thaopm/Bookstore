/**
 *
 * ShoppingCartService.java, Nov 15, 2019 thaopm
 * 
 */
package com.pmt.bookstore.service;

import com.pmt.bookstore.domain.ShoppingCart;

/**
 * @author thaopm
 *
 */
public interface ShoppingCartService {
	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
	
	void clearShoppingCart(ShoppingCart shoppingCart);
}
