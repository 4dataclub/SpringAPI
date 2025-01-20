package org.example.springapi.publisher;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher getPublisherById(Long publisherId) {
        return publisherRepository.findById(publisherId).orElse(null);
    }

    @Override
    public boolean createPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
        return true;
    }

    @Override
    public boolean deletePublisher(Long publisherId) {
        try {
            publisherRepository.deleteById(publisherId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updatePublisher(Long publisherId, Publisher updatedPublisher) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(publisherId);
        if(publisherOptional.isPresent()) {
            Publisher publisher = publisherOptional.get();
            publisher.setName(updatedPublisher.getName());
            publisherRepository.save(publisher);
            return true;
        }

        return false;
    }
}
