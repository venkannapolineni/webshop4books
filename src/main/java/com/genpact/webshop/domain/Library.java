package com.genpact.webshop.domain;

import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "LIBRARY")
@ComponentScan
public class Library implements java.io.Serializable {

    public Library(Long libraryId, String libraryName) {
        this.libraryId = libraryId;
        this.libraryName = libraryName;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    @Id
    @Column(name = "library_id", nullable = false)
    @GeneratedValue
    private Long libraryId;

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    @NotNull
    @Column(name = "library_name")
    @Size(min = 2, message = "Name should have atleast 2 characters")
    private String libraryName;

    public Library() {

    }

    public Library(long libraryId, String libraryName) {
        super();
        this.libraryId = libraryId;
        this. libraryName = libraryName;
    }
}
