package org.example.springapi.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        if(bookService.createBook(book)) {
            return new ResponseEntity<>("Book created", HttpStatus.CREATED);
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{bookId}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        Book book = bookService.getBookById(bookId);
        if(book != null) {
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        if(bookService.deleteBook(bookId)) {
            return new ResponseEntity<>("Book deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{bookId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateBook(@PathVariable Long bookId, @RequestBody Book book) {
        if(bookService.updateBook(bookId, book)) {
            return new ResponseEntity<>("Book updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
