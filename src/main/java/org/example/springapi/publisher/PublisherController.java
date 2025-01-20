package org.example.springapi.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springapi.autor.Autor;
import org.example.springapi.autor.AutorDTO;
import org.example.springapi.autor.AutorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publisher")
public class PublisherController {
    private final PublisherService publisherService;
    private final ObjectMapper objectMapper;
    private final AutorRepository autorRepository;

    public PublisherController(PublisherService publisherService, ObjectMapper objectMapper, AutorRepository autorRepository) {
        this.publisherService = publisherService;
        this.objectMapper = objectMapper;
        this.autorRepository = autorRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Publisher>> findAll() {
        return ResponseEntity.ok(publisherService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createPublisher(@RequestBody Publisher publisher) {
        if(publisherService.createPublisher(publisher)) {
            return new ResponseEntity<>("Publisher created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{publisherId}", method = RequestMethod.GET)
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable Long publisherId) {
        Publisher publisher = publisherService.getPublisherById(publisherId);
        if(publisher != null) {
            PublisherDTO publisherDTO = objectMapper.convertValue(publisher, PublisherDTO.class);
            publisherDTO.getBooks().forEach(bookDTO -> {
                bookDTO.setAutor(objectMapper.convertValue(autorRepository.findByBookId(bookDTO.getId()), AutorDTO.class));
            });
            return new ResponseEntity<>(publisherDTO, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{publisherId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePublisher(@PathVariable Long publisherId) {
        if(publisherService.deletePublisher(publisherId)) {
            return new ResponseEntity<>("Publisher deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{publisherId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePublisher(@PathVariable Long publisherId, @RequestBody Publisher publisher) {
        if(publisherService.updatePublisher(publisherId, publisher)) {
            return new ResponseEntity<>("Publisher updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
