package org.example.springapi.publisher;

import org.example.springapi.job.Job;

import java.util.List;

public interface PublisherService {
    List<Publisher> findAll();
    Publisher getPublisherById(Long publisherId);
    boolean createPublisher(Publisher publisher);
    boolean deletePublisher(Long publisherId);
    boolean updatePublisher(Long publisherId, Publisher publisher);
}
