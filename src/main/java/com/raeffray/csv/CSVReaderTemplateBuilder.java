package com.raeffray.csv;

import com.raeffray.commons.Configuration;
import org.apache.commons.io.input.BOMInputStream;

import java.io.*;

/**
 * Fluent builder for {@link CSVReaderTemplate}.
 */
public class CSVReaderTemplateBuilder<T> {

    private static CSVReaderTemplate template = new CSVReaderTemplate();

    private Class<T> clazz;

    /**
     * Sets the class whose configuration points to the CSV file to be read.
     *
     * @param clazz class whose configuration points to the CSV file to be read
     * @param <R>   generic type of the class
     * @return builder for the referred class
     */
    public static <R> CSVReaderTemplateBuilder<R> readCSVForClass(Class<R> clazz) {
        return new CSVReaderTemplateBuilder(clazz);
    }

    /**
     * Reads the CSV and calls the {@code handler}.
     *
     * @param handler handler to be called on each line read
     * @throws IOException error reading the CSV file
     */
    public void processWith(InstanceHandler<? super T> handler) throws IOException {
        String csvPath = Configuration.getConfigurationForClass(clazz)
                .getString("csv.path");

        InputStream stream = getClass().getResourceAsStream(csvPath);
        if (stream != null) {
            stream = new BOMInputStream(stream);
        } else {
            stream = new BOMInputStream(new FileInputStream(csvPath));
        }
        template.read(new InputStreamReader(stream), new InstanceConverter<>(clazz, handler));
    }

    private CSVReaderTemplateBuilder(final Class<T> clazz) {
        this.clazz = clazz;
    }
}
