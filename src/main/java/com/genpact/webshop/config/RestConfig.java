package com.genpact.webshop.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.genpact.webshop.WebshopApplication;
import com.genpact.webshop.controller.BookController;
import com.genpact.webshop.controller.LibraryController;
import com.genpact.webshop.exception.BookNotFoundException;
import com.genpact.webshop.exception.LibraryNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
@ApplicationPath("/api")
public class RestConfig extends Application {
    public Set<Class<?>> getClasses() {
        return new HashSet<>(
                Arrays.asList(
                        BookController.class,
                        BookNotFoundException.class,
                        LibraryNotFoundException.class,
                        LibraryController.class));
    }
}

