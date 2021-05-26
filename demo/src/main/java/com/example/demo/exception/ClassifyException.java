package com.example.demo.exception;

/**
 * Classification exception (except persistence related).
 *
 * @author carguello
 */
public class ClassifyException extends Exception {
	
	private static final long serialVersionUID = -2487054300053884693L;

	public ClassifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassifyException(String message) {
        super(message);
    }

    public ClassifyException(Throwable cause) {
        super(cause);
    }
}
