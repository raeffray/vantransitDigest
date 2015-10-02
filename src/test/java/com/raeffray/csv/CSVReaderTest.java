package com.raeffray.csv;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.raeffray.raw.data.Routes;
import com.raeffray.raw.data.StopTimes;
import com.raeffray.raw.data.Trips;

public class CSVReaderTest {
	
	private CSVReader csvReader;
	
	@Before
	public void prepare(){
		csvReader = new CSVReader();
	}
	
	@Test
	public void testRoutes(){
		List<Map<String, String>> data = csvReader.readCSVForData(Routes.class);
		assertThat(data, notNullValue());
		assertThat(data.size(),greaterThan(0));
		assertThat(data.size(),greaterThan(0));
		assertThat(data.iterator().next().get("route_id"),is(equalTo("AAAA")));
		assertThat(data.iterator().next().get("route_text_color"),is(equalTo("IIII")));

	}
	
	@Test
	public void testTrips(){
		List<Map<String, String>> data = csvReader.readCSVForData(Trips.class);
		assertThat(data, notNullValue());
		assertThat(data.size(),greaterThan(0));
		assertThat(data.size(),greaterThan(0));
		assertThat(data.iterator().next().get("route_id"),is(equalTo("AAAA")));
		assertThat(data.iterator().next().get("shape_id"),is(equalTo("HHHH")));
	}

	@Test
	public void testTimes(){
		List<Map<String, String>> data = csvReader.readCSVForData(StopTimes.class);
		assertThat(data, notNullValue());
		assertThat(data.size(),greaterThan(0));
		assertThat(data.size(),greaterThan(0));
		assertThat(data.iterator().next().get("trip_id"),is(equalTo("AAAA")));
		assertThat(data.iterator().next().get("shape_dist_traveled"),is(equalTo("IIII")));
	}
}
