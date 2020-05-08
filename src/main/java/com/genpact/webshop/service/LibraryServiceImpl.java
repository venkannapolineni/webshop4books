package com.genpact.webshop.service;

import com.genpact.webshop.domain.Library;
import com.genpact.webshop.exception.BookNotFoundException;
import com.genpact.webshop.exception.LibraryNotFoundException;
import com.genpact.webshop.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;


    @Override
    public List<Library> getAllLibraries() {
        return this.libraryRepository.findAll();
    }

    @Override
    public Library getLibraryById(long libraryId) {

        Optional<Library> libraryDb = this.libraryRepository.findById(libraryId);

        if (libraryDb.isPresent()) {
            return libraryDb.get();
        } else {
            throw new BookNotFoundException("Record not found with id : " + libraryId);
        }
    }

    @Override
    public Library createLibrary(Library library) {
        return libraryRepository.save(library);
    }

    @Override
    public Library updateLibrary(Library library, Long libraryId) {
        Optional<Library> libraryDb = this.libraryRepository.findById(library.getLibraryId());

        if (libraryDb.isPresent() && libraryId == library.getLibraryId()) {

            Library libraryUpdate = libraryDb.get();
            libraryUpdate.setLibraryId(libraryUpdate.getLibraryId());
            libraryUpdate.setLibraryName(libraryUpdate.getLibraryName());
            libraryRepository.save(libraryUpdate);
            return libraryUpdate;
        } else {
            throw new LibraryNotFoundException("No Library record is found with :" + library.getLibraryId());
        }
    }

    @Override
    public void deleteLibrary(Long libraryId) {
        Optional<Library> libraryDb = this.libraryRepository.findById(libraryId);

        if (libraryDb.isPresent()) {
            this.libraryRepository.delete(libraryDb.get());
        } else {
            throw new LibraryNotFoundException("No Library record is found with : " + libraryId);
        }
    }
}
