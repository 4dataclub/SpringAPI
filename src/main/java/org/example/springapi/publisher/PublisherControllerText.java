package org.example.springapi.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springapi.autor.AutorDTO;
import org.example.springapi.autor.AutorServiceText;
import org.example.springapi.autor.AutorServiceTextImpl;
import org.example.springapi.book.Book;
import org.example.springapi.book.BookServiceText;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publisher/text")
public class PublisherControllerText {
    private final PublisherServiceText publisherServiceText;
    private final ObjectMapper objectMapper;
    private final AutorServiceText autorServiceText;

    public PublisherControllerText(PublisherServiceText publisherServiceText, ObjectMapper objectMapper, AutorServiceText autorServiceText) {
        this.publisherServiceText = publisherServiceText;
        this.objectMapper = objectMapper;
        this.autorServiceText = autorServiceText;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PublisherDTO>> findAll() {
        List<Publisher> publishers = publisherServiceText.findAll();
        List<PublisherDTO> publisherDTOList = publishers.stream()
                .map(publisher -> objectMapper.convertValue(publisher, PublisherDTO.class))
                .collect(Collectors.toList());
        publisherDTOList = publisherDTOList.stream().map(publisherDTO -> {
            publisherDTO.getBooks().forEach(bookDTO -> {
                bookDTO.setAutor(objectMapper.convertValue(autorServiceText.getAutorByBookId(bookDTO.getId()), AutorDTO.class));
            });
            return publisherDTO;
        }).toList();
        return ResponseEntity.ok(publisherDTOList);
    }

    @RequestMapping(value = "{publisherId}", method = RequestMethod.GET)
    public ResponseEntity<PublisherDTO> getBookById(@PathVariable Long publisherId) {
        Publisher publisher = publisherServiceText.getPublisherById(publisherId);
        if(publisher != null) {
            PublisherDTO publisherDTO = objectMapper.convertValue(publisher, PublisherDTO.class);
            publisherDTO.getBooks().forEach(bookDTO -> {
                bookDTO.setAutor(objectMapper.convertValue(autorServiceText.getAutorByBookId(bookDTO.getId()), AutorDTO.class));
            });
            return new ResponseEntity<>(publisherDTO, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
