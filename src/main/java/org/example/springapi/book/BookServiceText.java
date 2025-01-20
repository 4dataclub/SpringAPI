package org.example.springapi.book;

import com.opencsv.exceptions.CsvException;
import org.example.springapi.autor.Autor;

import java.io.IOException;
import java.util.List;

public interface BookServiceText {
    List<Book> findAll();
    Book getBookById(Long bookId);
}
