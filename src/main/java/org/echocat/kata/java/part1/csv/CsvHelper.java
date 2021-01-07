package org.echocat.kata.java.part1.csv;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.*;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class CsvHelper {
    @SuppressWarnings("unchecked")
    public <T> List<T> readAll(Class<T> clazz, File file) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            BOMInputStream stream = new BOMInputStream(fileInputStream);
            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);

            return (List<T>) readerFor(clazz).readValues(reader).readAll();
        }
    }

    private <T> ObjectReader readerFor(Class<T> clazz) {
        CsvSchema schema = defaultCsvSchema();
        CsvMapper mapper = new CsvMapper();
        return mapper.readerFor(clazz).with(schema);
    }

    private CsvSchema defaultCsvSchema() {
        return CsvSchema.emptySchema().withHeader().withColumnSeparator(';');
    }
}
