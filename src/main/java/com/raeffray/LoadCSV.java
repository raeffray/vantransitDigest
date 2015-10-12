package com.raeffray;

import com.raeffray.csv.loader.BatchLoader;
import com.raeffray.raw.data.Agency;
import com.raeffray.raw.data.Routes;

import static com.raeffray.csv.CSVReaderTemplateBuilder.readCSVForClass;

/**
 * Loads CSV file data into Neo4J.
 */
public class LoadCSV {

    public static void main(String[] args) throws Exception {
        final BatchLoader loader = new BatchLoader(100);
        readCSVForClass(Agency.class).processWith(loader);
        readCSVForClass(Routes.class).processWith(loader);
        loader.flush();
    }
}
