package com.genpact.webshop.controller;

import static org.junit.Assert.assertTrue;
import java.util.Arrays;

import com.genpact.webshop.WebshopApplication;
import com.genpact.webshop.domain.Book;
import com.genpact.webshop.domain.Library;
import org.aspectj.lang.annotation.Before;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebshopApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIntegrationTesting {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Before(value = "")
    public void before() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/api" + uri;
    }

    @Test
    public void testRetrieveBookLibrary() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/books/Book1/libraries/Library1"),
                HttpMethod.GET, entity, String.class);

        String expected = "{isbn:12345,name:Book1,library: {libraryId: 12345, libraryName: Library1 }";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testAddBook() {
        Library library = new Library(123456, "Library1");
        Book book = new Book(12345, "Spring");
        book.setLibrary(library);

        HttpEntity<Book> entity = new HttpEntity<Book>(book, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/books/98765/libraries"),
                HttpMethod.POST, entity, String.class);

        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        assertTrue(actual.contains("/books/12345/libraries/"));

    }
}
