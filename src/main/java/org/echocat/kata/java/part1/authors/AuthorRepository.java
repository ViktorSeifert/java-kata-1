package org.echocat.kata.java.part1.authors;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.*;
import org.echocat.kata.java.part1.csv.CsvHelper;
import org.slf4j.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.*;
import java.util.*;

@Service
public class AuthorRepository {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorRepository.class);

    private final CsvHelper helper;

    @Inject
    public AuthorRepository(CsvHelper helper) {
        this.helper = helper;
    }

    public List<Author> findAll() {
        try {
            File file = new ClassPathResource("org/echocat/kata/java/part1/data/authors.csv").getFile();
            return helper.readAll(Author.class, file);
        } catch (IOException e) {
            LOG.error("Failed to read authors", e);
            return Collections.emptyList();
        }
    }
}
