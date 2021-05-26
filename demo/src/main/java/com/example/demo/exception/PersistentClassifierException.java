package com.example.demo.exception;

/**
 * Exception raised by the KeyValue backend
 * @author carguello
 */

public class PersistentClassifierException extends ClassifyException{

	private static final long serialVersionUID = 667957709808310621L;

	public PersistentClassifierException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistentClassifierException(String message) {
        super(message);
    }

    public PersistentClassifierException(Throwable cause) {
        super(cause);
    }

}
