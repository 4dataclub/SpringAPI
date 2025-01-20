package org.example.springapi.publisher;

import java.util.List;

public interface PublisherServiceText {
    List<Publisher> findAll();
    Publisher getPublisherById(Long publisherId);
}
