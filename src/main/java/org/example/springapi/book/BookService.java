package org.example.springapi.book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book getBookById(Long bookId);
    boolean createBook(Book book);
    boolean deleteBook(Long bookId);
    boolean updateBook(Long bookId, Book book);
}
