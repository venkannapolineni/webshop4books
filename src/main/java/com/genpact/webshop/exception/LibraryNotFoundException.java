package com.genpact.webshop.exception;

import javax.ws.rs.ext.Provider;

@Provider
public class LibraryNotFoundException extends java.lang.RuntimeException {

    private static final long serialVersionUID = 1L;

    public LibraryNotFoundException(String message) {
        super(message);
    }

    public LibraryNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
