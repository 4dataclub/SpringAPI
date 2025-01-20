package org.example.springapi.autor;

import com.opencsv.exceptions.CsvException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/autor/text")
public class AutorControllerText {
    private final AutorServiceText autorServiceText;

    public AutorControllerText(AutorServiceText autorServiceText) {
        this.autorServiceText = autorServiceText;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Autor>> findAll() throws IOException, CsvException {
        return ResponseEntity.ok(autorServiceText.findAll());
    }

    @RequestMapping(value = "{autorId}", method = RequestMethod.GET)
    public ResponseEntity<Autor> getAutorById(@PathVariable Long autorId) {
        Autor autor = autorServiceText.getAutorById(autorId);
        if(autor != null) {
            return new ResponseEntity<>(autor, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
