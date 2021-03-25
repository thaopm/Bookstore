/**
 *
 * UserShippingRepository.java, Dec 4, 2019 thaopm
 * 
 */
package com.pmt.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.pmt.bookstore.domain.UserShipping;

/**
 * @author thaopm
 *
 */
public interface UserShippingRepository extends CrudRepository<UserShipping, Long> {

}
