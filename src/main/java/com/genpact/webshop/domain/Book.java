package com.genpact.webshop.domain;

import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BOOK")
@ComponentScan
public class Book implements java.io.Serializable {
    @Id
    @GeneratedValue
    @Column(name = "isbn", nullable = false)
    private long isbn;

    @NotNull
    @Column(name = "name")
    @Size(min = 2, message = "Name should have atleast 2 characters")
    private String name;

    @NotNull
    @ManyToOne(targetEntity = com.genpact.webshop.domain.Library.class)
    @JoinColumn(name="library_id")
    private Library library;

    public Book() {

    }

    public Book(long isbn) {
        super();
    }

    public Book(long isbn, String name) {
        super();

        this.isbn = isbn;
        this.name = name;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
