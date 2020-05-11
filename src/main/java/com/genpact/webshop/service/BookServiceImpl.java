package com.genpact.webshop.service;

import com.genpact.webshop.domain.Book;
import com.genpact.webshop.domain.Library;
import com.genpact.webshop.exception.BookNotFoundException;
import com.genpact.webshop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
//@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {

        List<Book> bookList = this.bookRepository.findAll();

        if (null != bookList && !bookList.isEmpty()) {
            return bookList;
        } else {
            throw new BookNotFoundException("No Book records are found in DB");
        }
    }

    @Override
    public Book getBookById(long bookId) {

        Optional<Book> bookDb = this.bookRepository.findById(bookId);

        if (bookDb.isPresent()) {
            return bookDb.get();
        } else {
            throw new BookNotFoundException("Record not found with id : " + bookId);
        }
    }

    @Override
    public Book createBook(Book book) {
            /*book.setIsbn(book.getIsbn());
            book.setName(book.getName());*/
          /*  book.setLibrary(new Library(book.getLibrary().getLibraryId(),
                    book.getLibrary().getLibraryName()));*/
            return this.bookRepository.save(book);
     }

    @Override
    public Book updateBook(Book book, Long isbn) {

        Optional<Book> bookDb =this.bookRepository.findById(isbn);
        if (bookDb.isPresent() && isbn == book.getIsbn()) {
            Book bookUpdate = bookDb.get();
            bookUpdate.setIsbn(book.getIsbn());
            bookUpdate.setName(book.getName());
            bookUpdate.setLibrary(new Library(book.getLibrary().getLibraryId(),
                    book.getLibrary().getLibraryName()));
            this.bookRepository.save(bookUpdate);
            return bookUpdate;
        } else {
            throw new BookNotFoundException("Record not found with id : " + book.getIsbn());
        }
    }

    @Override
    public void deleteBook(long bookId) {
        Optional<Book> bookDb = this.bookRepository.findById(bookId);

        if (bookDb.isPresent()) {
            this.bookRepository.delete(bookDb.get());
        } else {
            throw new BookNotFoundException("Book Record not found with id : " + bookId);
        }
    }
}
