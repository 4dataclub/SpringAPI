package org.example.springapi.publisher;

import org.example.springapi.book.BookDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PublisherDTO implements Serializable {
    private Long id;

    private String name;

    private List<BookDTO> books = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
