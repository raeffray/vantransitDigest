package com.raeffray;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.raeffray.csv.CSVReader;
import com.raeffray.raw.data.Routes;
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
        	
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        	
        	List<Map<String, String>> csv1 = reader.readCSVForData(Routes.class);
        	List<Routes> list1 = ReflectionData.getInstance().buildList(Routes.class, csv1);
			System.out.println("Routes: ["+list1.size()+"] objects");
			
			for (Routes object : list1) {
				String json = ow.writeValueAsString(object);
				System.out.println(json);	
			}
			
//        	List<Map<String, String>> csv2 = reader.readCSVForData(Trips.class);
//        	List<Object> list2 = ReflectionData.getInstance().buildList(Trips.class, csv2);
//			System.out.println("Trips: ["+list2.size()+"] objects");
//			
//        	List<Map<String, String>> csv4 = reader.readCSVForData(Stops.class);
//        	List<Object> list4 = ReflectionData.getInstance().buildList(Stops.class, csv4);
//			System.out.println("Stops: ["+list4.size()+"] objects");
//			
//        	List<Map<String, String>> csv5 = reader.readCSVForData(Transfers.class);
//        	List<Object> list5 = ReflectionData.getInstance().buildList(Transfers.class, csv5);
//			System.out.println("Transfers: ["+list5.size()+"] objects");
//			
//        	List<Map<String, String>> csv6 = reader.readCSVForData(Calendar.class);
//        	List<Object> list6 = ReflectionData.getInstance().buildList(Calendar.class, csv6);
//			System.out.println("Calendar: ["+list6.size()+"] objects");
//			
//        	List<Map<String, String>> csv7 = reader.readCSVForData(CalendarDates.class);
//        	List<Object> list7 = ReflectionData.getInstance().buildList(CalendarDates.class, csv7);
//			System.out.println("CalendarDates: ["+list7.size()+"] objects");
//
//			List<Map<String, String>> csv8 = reader.readCSVForData(Shapes.class);
//        	List<Object> list8 = ReflectionData.getInstance().buildList(Shapes.class, csv8);
//			System.out.println("Shapes: ["+list8.size()+"] objects");
//			
//        	List<Map<String, String>> csv3 = reader.readCSVForData(StopTimes.class);
//        	List<StopTimes> list3 = ReflectionData.getInstance().buildList(StopTimes.class, csv3);
//			System.out.println("StopTimes: ["+list3.size()+"] objects");
//

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
