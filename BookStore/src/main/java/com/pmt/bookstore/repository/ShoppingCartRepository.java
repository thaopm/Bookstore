/**
 *
 * ShoppingCartRepository.java, Nov 15, 2019 thaopm
 * 
 */
package com.pmt.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.pmt.bookstore.domain.ShoppingCart;

/**
 * @author thaopm
 *
 */
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long>{
	
}
