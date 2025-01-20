package org.example.springapi;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.springapi.autor.Autor;
import org.example.springapi.book.Book;
import org.example.springapi.publisher.Publisher;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CSVReaderImplementor {
    private final List<String[]> entitiesAutor;
    private final List<String[]> entitiesBook;
    private final List<String[]> entitiesPublisher;

    private final List<Autor> autors;
    private final List<Book> books;
    private final List<Publisher> publishers;

    String autorFile = "/Users/aycan61/IdeaProjects/SpringAPI/src/main/java/org/example/springapi/Autor_1.txt";
    String bookFile = "/Users/aycan61/IdeaProjects/SpringAPI/src/main/java/org/example/springapi/Buch_2.txt";
    String publisherFile = "/Users/aycan61/IdeaProjects/SpringAPI/src/main/java/org/example/springapi/Verlag_3.txt";

    public CSVReaderImplementor() throws IOException, CsvException {
        CSVReader readerAutor = new CSVReader(new FileReader(this.autorFile));
        CSVReader readerBook = new CSVReader(new FileReader(this.bookFile));
        CSVReader readerPublisher = new CSVReader(new FileReader(this.publisherFile));

        entitiesAutor = readerAutor.readAll();
        entitiesBook = readerBook.readAll();
        entitiesPublisher = readerPublisher.readAll();

        List<Publisher> publishersTmp = entitiesPublisher.stream().map(row -> {
            String[] items = row[0].split(";");
            Publisher publisher = new Publisher();
            publisher.setId(Long.parseLong(items[0]));
            publisher.setName(items[1]);
            return publisher;
        }).toList();

        this.books = entitiesBook.stream().map(row -> {
            String[] items = row[0].split(";");
            Book book = new Book();
            book.setId(Long.parseLong(items[0]));
            book.setTitle(items[1]);
            book.setPrice(Double.parseDouble(items[2]));
            book.setIsbn(items[3]);
            Optional<Publisher> publisherOptional = publishersTmp.stream().filter(publisher -> publisher.getId().equals(Long.parseLong(items[4]))).findFirst();
            publisherOptional.ifPresent(book::setPublisher);

            return book;
        }).toList();

        this.publishers = publishersTmp.stream().map(publisher -> {
            publisher.getBooks().addAll(this.books.stream().filter(book -> book.getPublisher().getId().equals(publisher.getId())).toList());
            return publisher;
        }).toList();

        this.autors = entitiesAutor.stream().map(row -> {
            String[] items = row[0].split(";");
            Autor autor = new Autor();
            autor.setId(Long.parseLong(items[0]));
            autor.setFirstName(items[1]);
            autor.setLastName(items[2]);
            Optional<Book> bookOptional = books.stream().filter(book -> book.getId().equals(Long.parseLong(items[3]))).findFirst();
            bookOptional.ifPresent(autor::setBook);


            return autor;
        }).toList();
    }
    public List<Autor> getAutors() {
        return autors;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }
}