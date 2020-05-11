package com.genpact.webshop.controller;

import com.genpact.webshop.domain.Book;
import com.genpact.webshop.domain.Library;
import com.genpact.webshop.repository.LibraryRepository;
import com.genpact.webshop.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired(required = true)
    private LibraryRepository libraryRepository;

    @LocalServerPort
    private int port;


    Book mockBook = new Book(12345, "Spring");

    String exampleBookJson = ""; //TODO""{\"isbn\":${isbn}, \"name\":\"${name}\", \"library\": { \"libraryId\":${libraryid}, \"libraryName\":\"${libraryname}\"}}";

    HttpHeaders headers = new HttpHeaders();



    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/api" + uri;
    }

    public void before() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void retrieveDetailsForBook() throws Exception {

        Mockito.when(
                bookService.getAllBooks()).thenReturn((List<Book>) mockBook);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                createURLWithPort("/books/all")).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{id:12345,name:Spring, library: {libraryId: Library1: libraryName: Science LIb }}";

        assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void createBookLibrary() throws Exception {
        Book mockBook = new Book(12345, "Book1");
        Library mockLibrary = new Library(12345, "Library1");
        mockBook.setLibrary(mockLibrary);

        Mockito.when(bookService.createBook(mockBook)).thenReturn(mockBook);

        //Send course as body to /books/Book1/libraries
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                createURLWithPort("/books/post"))
                .accept(MediaType.APPLICATION_JSON).content(exampleBookJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("/books/post", response
                .getHeader(HttpHeaders.LOCATION));
    }

}