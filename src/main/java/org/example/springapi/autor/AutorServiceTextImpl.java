package org.example.springapi.autor;

import com.opencsv.exceptions.CsvException;
import org.example.springapi.CSVReaderImplementor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AutorServiceTextImpl implements AutorServiceText {
    private final CSVReaderImplementor cri = new CSVReaderImplementor();

    public AutorServiceTextImpl() throws IOException, CsvException {
    }

    @Override
    public List<Autor> findAll() {
        return cri.getAutors();
    }

    @Override
    public Autor getAutorById(Long autorId) {
         return cri.getAutors().stream().filter(autor -> autor.getId().equals(autorId)).findFirst().orElse(null);
    }

    @Override
    public Autor getAutorByBookId(Long bookId) {
        return cri.getAutors().stream().filter(autor -> autor.getBook().getId().equals(bookId)).findFirst().orElse(null);
    }
}
