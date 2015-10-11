package com.raeffray.csv;

import java.util.Map;

/**
 * Handles the processing of a single line of a CSV file. The line itself is parsed as a {@code Map<String, String>}.
 */
public interface CSVRowHandler {
    /**
     * Callback for processing a single line of a CSV file.
     *
     * @param row the line represented as a map
     */
    void processLine(Map<String, String> row);
}
