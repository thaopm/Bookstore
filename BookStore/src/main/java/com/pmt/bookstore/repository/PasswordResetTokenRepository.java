/**
 *
 * PasswordResetTokenRepository.java, Sep 27, 2019 thaopm
 * 
 */
package com.pmt.bookstore.repository;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.pmt.bookstore.domain.User;
import com.pmt.bookstore.security.PasswordResetToken;

/**
 * @author thaopm
 *
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>{
	
	PasswordResetToken findByToken(String token);
	
	PasswordResetToken findByUser(User user);
	
	Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);
	
	@Modifying
	@Query("delete from PasswordResetToken t where t.expiryDate <= ?1")
	void deleteAllExpiredSince(Date now);
}
