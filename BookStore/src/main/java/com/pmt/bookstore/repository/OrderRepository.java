/**
 *
 * OrderRepository.java, Dec 6, 2019 thaopm
 * 
 */
package com.pmt.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.pmt.bookstore.domain.Order;

/**
 * @author thaopm
 *
 */
public interface OrderRepository extends CrudRepository<Order, Long>{

}
