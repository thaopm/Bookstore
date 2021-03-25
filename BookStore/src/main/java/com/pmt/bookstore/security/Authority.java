/**
 *
 * Authority.java, Sep 27, 2019 thaopm
 * 
 */
package com.pmt.bookstore.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author thaopm
 *
 */
public class Authority implements GrantedAuthority{
	private final String authority;
	
	public Authority(String authority) {
		this.authority = authority;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority() {
		return authority;
	}

}
