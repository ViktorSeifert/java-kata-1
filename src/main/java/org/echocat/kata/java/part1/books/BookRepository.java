package org.echocat.kata.java.part1.books;

import com.fasterxml.jackson.databind.MappingIterator;
import org.echocat.kata.java.part1.authors.*;
import org.echocat.kata.java.part1.csv.CsvHelper;
import org.slf4j.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class BookRepository {
    private static final Logger LOG = LoggerFactory.getLogger(BookRepository.class);

    private final CsvHelper helper;
    private final AuthorRepository authorRepository;

    @Inject
    public BookRepository(CsvHelper helper, AuthorRepository authorRepository) {
        this.helper = helper;
        this.authorRepository = authorRepository;
    }

    public List<Book> findAll() {
        try {
            File file = new ClassPathResource("org/echocat/kata/java/part1/data/books.csv").getFile();
            List<Book> books = helper.readAll(Book.class, file);
            List<Author> authors = authorRepository.findAll();

            for (Book book : books) {
                book.resolveAuthors(authors);
            }

            return books;
        } catch (IOException e) {
            LOG.error("Failed to read books", e);
            return Collections.emptyList();
        }
    }
}
