package com.genpact.webshop.controller;

import com.genpact.webshop.domain.Book;
import com.genpact.webshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



//@CrossOrigin("http://localhost:8080/api/books/all")
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World RESTful with Spring Boot";
    }

    @GetMapping("/books/all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks(); // queries database for all books
    }

    @GetMapping("/books/{isbn}")
    public Book getBooksById(@PathVariable(value = "isbn") String isbn) {
        return bookService.getBookById(Long.valueOf(isbn)); // queries database for all books;
    }

    @PostMapping("/books/post")
    public Book saveBook(@RequestBody Book book) {
       return bookService.createBook(book);
    }

   @PutMapping("/books/{isbn}")
    public Book updateBook(@RequestBody Book book, @PathVariable("isbn") Long isbn) {
        return bookService.updateBook(book, isbn);
    }

    @DeleteMapping("/books/{isbn}")
    public void deleteBook(@PathVariable("isbn") Long isbn) {
        bookService.deleteBook(isbn);
    }
}
