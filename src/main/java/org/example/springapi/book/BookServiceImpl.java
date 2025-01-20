package org.example.springapi.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springapi.publisher.Publisher;
import org.example.springapi.publisher.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final ObjectMapper objectMapper;

    public BookServiceImpl(BookRepository bookRepository, PublisherRepository publisherRepository, ObjectMapper objectMapper) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public boolean createBook(Book book) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(book.getPublisher().getId());
        if(publisherOptional.isPresent()) {
            Publisher publisher = publisherOptional.get();
            book.setPublisher(publisher);

            bookRepository.save(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteBook(Long bookId) {
        try {
            bookRepository.deleteById(bookId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateBook(Long bookId, Book updatedBook) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setTitle(updatedBook.getTitle());
            book.setIsbn(updatedBook.getIsbn());
            book.setPrice(updatedBook.getPrice());
            book.setPublisher(updatedBook.getPublisher());
            bookRepository.save(book);
            return true;
        }

        return false;
    }
}
