package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.authors.AuthorRepository;
import org.echocat.kata.java.part1.books.*;
import org.echocat.kata.java.part1.magazines.*;
import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.inject.Inject;
import java.util.*;

@SpringBootApplication
public class MainApp implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    private final MagazineRepository magazineRepository;
    private final BookRepository bookRepository;

    @Inject
    public MainApp(MagazineRepository magazineRepository, BookRepository bookRepository) {
        this.magazineRepository = magazineRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        LOG.info("Application started with " + Arrays.toString(args));

        for (Book book : bookRepository.findAll()) {
            System.out.println(book);
        }

        for (Magazine magazine : magazineRepository.findAll()) {
            System.out.println(magazine);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
