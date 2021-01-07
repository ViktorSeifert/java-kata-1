package org.echocat.kata.java.part1.magazines;

import org.echocat.kata.java.part1.authors.*;
import org.echocat.kata.java.part1.csv.CsvHelper;
import org.slf4j.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.*;
import java.util.*;

@Service
public class MagazineRepository {
    private static final Logger LOG = LoggerFactory.getLogger(MagazineRepository.class);

    private final CsvHelper helper;
    private final AuthorRepository authorRepository;

    @Inject
    public MagazineRepository(CsvHelper helper, AuthorRepository authorRepository) {
        this.helper = helper;
        this.authorRepository = authorRepository;
    }

    public List<Magazine> findAll() {
        try {
            File file = new ClassPathResource("org/echocat/kata/java/part1/data/magazines.csv").getFile();
            List<Magazine> books = helper.readAll(Magazine.class, file);
            List<Author> authors = authorRepository.findAll();

            for (Magazine book : books) {
                book.resolveAuthors(authors);
            }

            return books;
        } catch (IOException e) {
            LOG.error("Failed to read magazines", e);
            return Collections.emptyList();
        }
    }
}
