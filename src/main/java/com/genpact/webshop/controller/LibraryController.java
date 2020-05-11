package com.genpact.webshop.controller;

import com.genpact.webshop.domain.Library;
import com.genpact.webshop.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
public class LibraryController {
    @Autowired
    LibraryService libraryService;
    
    @GetMapping("/libraries/all")
    public List<Library> getAllLibraries() {
        return libraryService.getAllLibraries(); // queries database for all librarys
    }

    @GetMapping("/libraries/{id}")
    public Library getLibrarysById(@PathVariable(value = "id") Long libraryId) {
        return libraryService.getLibraryById(Long.valueOf(libraryId)); // queries database for all librarys;
    }

   @PostMapping("/libraries/post")
    public Library saveLibrary(@RequestBody Library library) {
        library = libraryService.createLibrary(library);
        return library;
    }

    @PutMapping("/libraries/{libraryId}")
    public Library updateLibrary(@RequestBody Library library, @PathVariable(value = "id") Long libraryId ) {
        return libraryService.updateLibrary(library, libraryId);
    }

    @DeleteMapping("/libraries/{libraryId}")
    public void deleteLibrary(@PathVariable("id") Long libraryId) {
        libraryService.deleteLibrary(Long.valueOf(libraryId));
    }
}