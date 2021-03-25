/**
 *
 * BookToCartItemRepository.java, Nov 15, 2019 thaopm
 * 
 */
package com.pmt.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pmt.bookstore.domain.BookToCartItem;
import com.pmt.bookstore.domain.CartItem;

/**
 * @author thaopm
 *
 */
@Transactional
public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long>{
	void deleteByCartItem(CartItem cartItem);
}
