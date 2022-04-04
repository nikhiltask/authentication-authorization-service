package com.authenticationauthorizationservice.exception;

import org.apache.http.auth.AuthenticationException;


public class JwtTokenMalformedException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public JwtTokenMalformedException(String msg) {
		super(msg);
	}

}
