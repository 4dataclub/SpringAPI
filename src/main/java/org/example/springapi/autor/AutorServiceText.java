package org.example.springapi.autor;

import java.util.List;

public interface AutorServiceText {
    List<Autor> findAll();
    Autor getAutorById(Long autorId);
    Autor getAutorByBookId(Long bookId);
}
