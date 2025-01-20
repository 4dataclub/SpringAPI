package org.example.springapi.autor;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorServiceImpl implements AutorService {
    private final AutorRepository autorRepository;
    private final ObjectMapper objectMapper;

    public AutorServiceImpl(AutorRepository autorRepository, ObjectMapper objectMapper) {
        this.autorRepository = autorRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    @Override
    public Autor getAutorById(Long autorId) {
        return autorRepository.findById(autorId).orElse(null);
    }

    @Override
    public boolean createAutor(Autor autor) {
        autorRepository.save(autor);
        return true;
    }

    @Override
    public boolean deleteAutor(Long autorId) {
        try {
            autorRepository.deleteById(autorId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateAutor(Long autorId, Autor updatedAutor) {
        Optional<Autor> bookOptional = autorRepository.findById(autorId);
        if(bookOptional.isPresent()) {
            Autor book = bookOptional.get();
            book.setFirstName(updatedAutor.getFirstName());
            book.setLastName(updatedAutor.getLastName());
            book.setBook(updatedAutor.getBook());
            autorRepository.save(book);
            return true;
        }

        return false;
    }
}
