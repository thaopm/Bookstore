package com.pmt.bookstore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pmt.bookstore.domain.Book;
import com.pmt.bookstore.domain.User;
import com.pmt.bookstore.security.Role;
import com.pmt.bookstore.security.UserRole;
import com.pmt.bookstore.service.UserService;
import com.pmt.bookstore.utility.SecurityUtility;

@SpringBootApplication
public class BookStoreApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... args) throws Exception {
		User user1  = new User();
		user1.setFirstName("John");
		user1.setLastName("Adams");
		user1.setUsername("user");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("user"));
		user1.setEmail("JAdams@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
	}

}
