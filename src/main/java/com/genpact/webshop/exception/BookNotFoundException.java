package com.genpact.webshop.exception;

import javax.ws.rs.ext.Provider;

@Provider
public class BookNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
