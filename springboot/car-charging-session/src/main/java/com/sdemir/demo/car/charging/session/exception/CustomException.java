package com.sdemir.demo.car.charging.session.exception;


/**
 * This class is to hold general business exceptions
 * 
 * @author sertacdemir
 * @since 1.0
 *
 */
public class CustomException extends RuntimeException{
	public CustomException(String message) {
		super(message);
	}
}
