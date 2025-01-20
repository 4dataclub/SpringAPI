package org.example.springapi.book;

import org.example.springapi.autor.AutorDTO;
import org.example.springapi.publisher.PublisherDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookDTO implements Serializable {
    private Long id;

    private String title;
    private Double price;
    private String isbn;

    private AutorDTO autor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public AutorDTO getAutor() {
        return autor;
    }

    public void setAutor(AutorDTO autor) {
        this.autor = autor;
    }
}
