package com.sdemir.demo.car.charging.session.exception;

/**
 * This class is to hold resource not found exception
 * 
 * @author sertacdemir
 * @since 1.0
 *
 */
public class ResourceNotFoundException  extends RuntimeException{
	
	public ResourceNotFoundException(String message) {
        super(message);
    }
}
