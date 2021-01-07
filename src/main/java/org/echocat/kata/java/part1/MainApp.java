package org.echocat.kata.java.part1;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class MainApp implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    @Override
    public void run(String... args) {
        LOG.info("Application started with " + Arrays.toString(args));
    }
}
