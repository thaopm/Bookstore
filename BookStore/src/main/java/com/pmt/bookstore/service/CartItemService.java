/**
 *
 * CartItemService.java, Nov 15, 2019 thaopm
 * 
 */
package com.pmt.bookstore.service;

import java.util.List;

import com.pmt.bookstore.domain.Book;
import com.pmt.bookstore.domain.CartItem;
import com.pmt.bookstore.domain.Order;
import com.pmt.bookstore.domain.ShoppingCart;
import com.pmt.bookstore.domain.User;

/**
 * @author thaopm
 *
 */
public interface CartItemService {
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addBookToCartItem(Book book, User user, int qty);
	
	CartItem findById(Long id);
	
	void removeCartItem(CartItem cartItem);
	
	CartItem save(CartItem cartItem);
	
	List<CartItem> findByOrder(Order order);
}
