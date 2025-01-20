package org.example.springapi.publisher;

import com.opencsv.exceptions.CsvException;
import org.example.springapi.CSVReaderImplementor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PublisherServiceTextImpl implements PublisherServiceText {
    private final CSVReaderImplementor cri = new CSVReaderImplementor();

    public PublisherServiceTextImpl() throws IOException, CsvException {
    }

    @Override
    public List<Publisher> findAll() {
        return cri.getPublishers();
    }

    @Override
    public Publisher getPublisherById(Long publisherId) {
         return cri.getPublishers().stream().filter(publisher -> publisher.getId().equals(publisherId)).findFirst().orElse(null);
    }
}
