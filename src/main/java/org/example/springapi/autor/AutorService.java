package org.example.springapi.autor;

import java.util.List;

public interface AutorService {
    List<Autor> findAll();
    Autor getAutorById(Long autorId);
    boolean createAutor(Autor autor);
    boolean deleteAutor(Long autorId);
    boolean updateAutor(Long autorId, Autor autor);
}
