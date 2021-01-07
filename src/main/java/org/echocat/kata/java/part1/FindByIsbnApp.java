package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.books.*;
import org.echocat.kata.java.part1.magazines.*;
import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.inject.Inject;
import java.util.Arrays;

@SpringBootApplication
public class FindByIsbnApp implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(FindByIsbnApp.class);

    private final MagazineRepository magazineRepository;
    private final BookRepository bookRepository;

    @Inject
    public FindByIsbnApp(MagazineRepository magazineRepository, BookRepository bookRepository) {
        this.magazineRepository = magazineRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        LOG.info("Application started with " + Arrays.toString(args));
        if (args.length != 1)
            return;

        for (Book book : bookRepository.findAll()) {
            if (args[0].equals(book.getIsbn()))
                System.out.println(book);
        }

        for (Magazine magazine : magazineRepository.findAll()) {
            if (args[0].equals(magazine.getIsbn()))
                System.out.println(magazine);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(FindByIsbnApp.class, args);
    }
}
