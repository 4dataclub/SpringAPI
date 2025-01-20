package org.example.springapi.book;

import com.opencsv.exceptions.CsvException;
import org.example.springapi.CSVReaderImplementor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BookServiceTextImpl implements BookServiceText {
    private final CSVReaderImplementor cri = new CSVReaderImplementor();

    public BookServiceTextImpl() throws IOException, CsvException {
    }

    @Override
    public List<Book> findAll() {
        return cri.getBooks();
    }

    @Override
    public Book getBookById(Long bookId) {
         return cri.getBooks().stream().filter(book -> book.getId().equals(bookId)).findFirst().orElse(null);
    }
}
