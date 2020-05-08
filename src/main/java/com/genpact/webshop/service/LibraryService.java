package com.genpact.webshop.service;

import com.genpact.webshop.domain.Book;
import com.genpact.webshop.domain.Library;
import org.springframework.stereotype.Component;

import java.util.List;

public interface LibraryService {

    List<Library> getAllLibraries();

    Library getLibraryById(long libraryId);

    Library createLibrary(Library Library);

    Library updateLibrary(Library library, Long libraryId);

    void deleteLibrary(Long id);
}
