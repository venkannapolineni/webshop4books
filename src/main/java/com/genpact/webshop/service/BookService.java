package com.genpact.webshop.service;

import com.genpact.webshop.domain.Book;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

public interface BookService {
    Book createBook(Book book);

    Book updateBook(Book book, Long bookId);

    List<Book> getAllBooks();

    Book getBookById(long bookId);

    void deleteBook(long id);
}
