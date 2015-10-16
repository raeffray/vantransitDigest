package com.raeffray;

import static com.raeffray.csv.CSVReaderTemplateBuilder.readCSVForClass;

import java.util.ArrayList;
import java.util.Collection;

import com.raeffray.csv.loader.BatchLoader;
import com.raeffray.csv.loader.ConnectedNodesLoader;
import com.raeffray.graph.RelationshipDescriber;
import com.raeffray.raw.data.Agency;
import com.raeffray.raw.data.StopTimes;
import com.raeffray.raw.data.Trips;

/**
 * Loads CSV file data into Neo4J.
 */
public class LoadCSV {

    public static void main(String[] args) throws Exception {
    	
    	Collection<RelationshipDescriber> describers = new ArrayList<RelationshipDescriber>();    	
    	RelationshipDescriber relationshipDescriber = new RelationshipDescriber("OPERATES");
    	describers.add(relationshipDescriber);
    	
    	final BatchLoader loader = new BatchLoader(1);
    	
    	final ConnectedNodesLoader relLoader = new ConnectedNodesLoader(describers);
    	
        readCSVForClass(Agency.class).processWith(loader);
        
        loader.flush();
        
    	readCSVForClass(Trips.class).processWith(relLoader,"7056155");
                        
        
    }
}
