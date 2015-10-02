package com.raeffray;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.raeffray.csv.CSVReader;
import com.raeffray.raw.data.Routes;
import com.raeffray.raw.data.StopTimes;
import com.raeffray.raw.data.Trips;
import com.raeffray.raw.instrospection.ReflectionData;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException
    {
        CSVReader reader = new CSVReader();
        
        try {
        	
        	List<Map<String, String>> csv1 = reader.readCSVForData(Routes.class);
        	List<Object> list1 = ReflectionData.getInstance().buildList(Routes.class, csv1);
			System.out.println("Routes: ["+list1.size()+"] objects");
			
        	List<Map<String, String>> csv2 = reader.readCSVForData(Trips.class);
        	List<Object> list2 = ReflectionData.getInstance().buildList(Trips.class, csv2);
			System.out.println("Trips: ["+list2.size()+"] objects");

        	List<Map<String, String>> csv3 = reader.readCSVForData(StopTimes.class);
        	List<Object> list3 = ReflectionData.getInstance().buildList(StopTimes.class, csv3);
			System.out.println("StopTimes: ["+list3.size()+"] objects");

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
