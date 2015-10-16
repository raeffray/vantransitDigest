package com.raeffray.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

/**
 * Template methods for reading CSV files. This class is inspired on Spring Framework's JdbcTemplate.
 */
public class CSVReaderTemplate {

    /**
     * Reads the CSV file calling the handler for each line. Closes the reader after reading the entire file or if an
     * exception occurs.
     *
     * @param reader  reader pointing to the CSV file
     * @param handler row handler
     * @throws IOException exception while reading the file
     */
    public void read(final Reader reader, CSVRowHandler handler) throws IOException {
        try (final CSVParser records = CSVFormat.EXCEL.withHeader().withIgnoreSurroundingSpaces().parse(reader)) {
            for (CSVRecord record : records) {
                final Map<String, String> line = record.toMap();
                handler.processLine(line);
            }
            handler.notifyEOF();
        }
    }
    
    /**
     * Reads the CSV file calling the handler for each line. Closes the reader after reading the entire file or if an
     * exception occurs.
     *
     * @param reader  reader pointing to the CSV file
     * @param handler row handler
     * @param idToSearch na id to be used as criteria
     * @throws IOException exception while reading the file
     */
    public void read(final Reader reader, CSVRowHandler handler, String idToSearch) throws IOException {
        try (final CSVParser records = CSVFormat.EXCEL.withHeader().withIgnoreSurroundingSpaces().parse(reader)) {
            for (CSVRecord record : records) {
                final Map<String, String> line = record.toMap();
                handler.processLine(line,idToSearch);
            }
            handler.notifyEOF();
        }
    }
}
