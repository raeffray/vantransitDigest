package com.raeffray.csv;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.raeffray.raw.data.Agency;
import com.raeffray.raw.data.Calendar;
import com.raeffray.raw.data.CalendarDates;
import com.raeffray.raw.data.Routes;
import com.raeffray.raw.data.Shapes;
import com.raeffray.raw.data.StopTimes;
import com.raeffray.raw.data.Stops;
import com.raeffray.raw.data.Transfers;
import com.raeffray.raw.data.Trips;

public class CSVReaderTest {
	
	private CSVReader csvReader;
	
	@Before
	public void prepare(){
		csvReader = new CSVReader();
	}
	
	@Test
	public void testRoutes() throws Exception{
		List<Map<String, String>> data = csvReader.readCSVForData(Routes.class);
		assertThat(data, notNullValue());
		assertThat(data.size(),greaterThan(0));
		assertThat(data.size(),greaterThan(0));
		assertThat(data.iterator().next().get("route_id"),is(equalTo("AAAA")));
		assertThat(data.iterator().next().get("route_text_color"),is(equalTo("IIII")));

	}
	
	@Test
	public void testTrips() throws Exception{
		List<Map<String, String>> data = csvReader.readCSVForData(Trips.class);
		assertThat(data, notNullValue());
		assertThat(data.size(),greaterThan(0));
		assertThat(data.size(),greaterThan(0));
		assertThat(data.iterator().next().get("route_id"),is(equalTo("AAAA")));
		assertThat(data.iterator().next().get("shape_id"),is(equalTo("HHHH")));
	}

	@Test
	public void testTimes() throws Exception{
		List<Map<String, String>> data = csvReader.readCSVForData(StopTimes.class);
		assertThat(data, notNullValue());
		assertThat(data.size(),greaterThan(0));
		assertThat(data.size(),greaterThan(0));
		assertThat(data.iterator().next().get("trip_id"),is(equalTo("AAAA")));
		assertThat(data.iterator().next().get("shape_dist_traveled"),is(equalTo("IIII")));
	}
	
	@Test
	public void testShapes() throws Exception{
		List<Map<String, String>> data = csvReader.readCSVForData(Shapes.class);
		assertThat(data, notNullValue());
		assertThat(data.size(),greaterThan(0));
		assertThat(data.size(),greaterThan(0));
		assertThat(data.iterator().next().get("shape_id"),is(equalTo("AAAA")));
		assertThat(data.iterator().next().get("shape_dist_traveled"),is(equalTo("EEEE")));
	}
	
	@Test
	public void testAgency() throws Exception{
		List<Map<String, String>> data = csvReader.readCSVForData(Agency.class);
		assertThat(data, notNullValue());
		assertThat(data.size(),greaterThan(0));
		assertThat(data.size(),greaterThan(0));
		assertThat(data.iterator().next().get("agency_id"),is(equalTo("AAAA")));
		assertThat(data.iterator().next().get("agency_lang"),is(equalTo("EEEE")));
	}
	
	@Test
	public void testStops() throws Exception{
		List<Map<String, String>> data = csvReader.readCSVForData(Stops.class);
		assertThat(data, notNullValue());
		assertThat(data.size(),greaterThan(0));
		assertThat(data.size(),greaterThan(0));
		assertThat(data.iterator().next().get("stop_id"),is(equalTo("AAAA")));
		assertThat(data.iterator().next().get("parent_station"),is(equalTo("JJJJ")));
	}
	@Test
	public void testCalendar() throws Exception{
		List<Map<String, String>> data = csvReader.readCSVForData(Calendar.class);
		assertThat(data, notNullValue());
		assertThat(data.size(),greaterThan(0));
		assertThat(data.size(),greaterThan(0));
		assertThat(data.iterator().next().get("service_id"),is(equalTo("AAAA")));
		assertThat(data.iterator().next().get("end_date"),is(equalTo("JJJJ")));
	}
	@Test
	public void testCalendarDates() throws Exception{
		List<Map<String, String>> data = csvReader.readCSVForData(CalendarDates.class);
		assertThat(data, notNullValue());
		assertThat(data.size(),greaterThan(0));
		assertThat(data.size(),greaterThan(0));
		assertThat(data.iterator().next().get("service_id"),is(equalTo("AAAA")));
		assertThat(data.iterator().next().get("exception_type"),is(equalTo("CCCC")));
	}
	@Test
	public void testCalendarTransfers() throws Exception{
		List<Map<String, String>> data = csvReader.readCSVForData(Transfers.class);
		assertThat(data, notNullValue());
		assertThat(data.size(),greaterThan(0));
		assertThat(data.size(),greaterThan(0));
		assertThat(data.iterator().next().get("from_stop_id"),is(equalTo("AAAA")));
		assertThat(data.iterator().next().get("min_transfer_time"),is(equalTo("DDDD")));
	}
}
