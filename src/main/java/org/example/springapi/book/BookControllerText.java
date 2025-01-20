package org.example.springapi.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book/text")
public class BookControllerText {
    private final BookServiceText bookServiceText;

    public BookControllerText(BookServiceText bookServiceText) {
        this.bookServiceText = bookServiceText;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookServiceText.findAll());
    }

    @RequestMapping(value = "{bookId}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        Book book = bookServiceText.getBookById(bookId);
        if(book != null) {
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
