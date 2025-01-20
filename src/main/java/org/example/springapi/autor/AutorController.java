package org.example.springapi.autor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {
    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Autor>> findAll() {
        return ResponseEntity.ok(autorService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createAutor(@RequestBody Autor autor) {
        if(autorService.createAutor(autor)) {
            return new ResponseEntity<>("Autor created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{autorId}", method = RequestMethod.GET)
    public ResponseEntity<Autor> getAutorById(@PathVariable Long autorId) {
        Autor autor = autorService.getAutorById(autorId);
        if(autor != null) {
            return new ResponseEntity<>(autor, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{autorId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAutor(@PathVariable Long autorId) {
        if(autorService.deleteAutor(autorId)) {
            return new ResponseEntity<>("Autor deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{autorId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateAutor(@PathVariable Long autorId, @RequestBody Autor autor) {
        if(autorService.updateAutor(autorId, autor)) {
            return new ResponseEntity<>("Autor updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
