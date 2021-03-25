/**
 *
 * RoleRepository.java, Oct 4, 2019 thaopm
 * 
 */
package com.pmt.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.pmt.bookstore.security.Role;

/**
 * @author thaopm
 *
 */
public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByName(String name);
}
