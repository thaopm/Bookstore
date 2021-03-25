/**
 *
 * CartItemRepository.java, Nov 15, 2019 thaopm
 * 
 */
package com.pmt.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pmt.bookstore.domain.CartItem;
import com.pmt.bookstore.domain.Order;
import com.pmt.bookstore.domain.ShoppingCart;

/**
 * @author thaopm
 *
 */
@Transactional
public interface CartItemRepository extends CrudRepository<CartItem, Long>{
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	List<CartItem> findByOrder(Order order);
}
