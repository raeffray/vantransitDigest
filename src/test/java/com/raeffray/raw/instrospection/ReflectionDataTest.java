package com.raeffray.raw.instrospection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.raeffray.csv.CSVReader;
import com.raeffray.raw.data.Agency;
import com.raeffray.raw.data.Calendar;
import com.raeffray.raw.data.CalendarDates;
import com.raeffray.raw.data.Routes;
import com.raeffray.raw.data.Shapes;
import com.raeffray.raw.data.StopTimes;
import com.raeffray.raw.data.Stops;
import com.raeffray.raw.data.Transfers;
import com.raeffray.raw.data.Trips;

public class ReflectionDataTest {
	
	private CSVReader csvReader;
	
	private List<Map<String, String>> data1=null;
	private List<Map<String, String>> data2=null;
	private List<Map<String, String>> data3=null;
	private List<Map<String, String>> data4=null;
	private List<Map<String, String>> data5=null;
	private List<Map<String, String>> data6=null;
	private List<Map<String, String>> data7=null;
	private List<Map<String, String>> data8=null;
	private List<Map<String, String>> data9=null;
	
	@Before
	public void prepare() throws Exception{
		csvReader = new CSVReader();
		data1 = csvReader.readCSVForData(Routes.class);
		data2 = csvReader.readCSVForData(Trips.class);
		data3 = csvReader.readCSVForData(StopTimes.class);
		data4 = csvReader.readCSVForData(Shapes.class);
		data5 = csvReader.readCSVForData(Agency.class);
		data6 = csvReader.readCSVForData(Stops.class);
		data7 = csvReader.readCSVForData(Calendar.class);
		data8 = csvReader.readCSVForData(CalendarDates.class);
		data9 = csvReader.readCSVForData(Transfers.class);
	}
	
	@Test
	public void testExtractFieldsNamesRoutes(){
		String[] fieldsNames = ReflectionData.getInstance().extractFieldsNames(Routes.class);
		assertThat(fieldsNames, notNullValue());
		assertThat(fieldsNames.length, is(equalTo(9)));
	}
	
	@Test
	public void testExtractFieldsNamesTrips(){
		String[] fieldsNames = ReflectionData.getInstance().extractFieldsNames(Trips.class);
		assertThat(fieldsNames, notNullValue());
		assertThat(fieldsNames.length, is(equalTo(8)));
	}

	@Test
	public void testExtractFieldsNamesStopTimes(){
		String[] fieldsNames = ReflectionData.getInstance().extractFieldsNames(StopTimes.class);
		assertThat(fieldsNames, notNullValue());
		assertThat(fieldsNames.length, is(equalTo(9)));
	}
	
	@Test
	public void testExtractFieldsNamesShapes(){
		String[] fieldsNames = ReflectionData.getInstance().extractFieldsNames(Shapes.class);
		assertThat(fieldsNames, notNullValue());
		assertThat(fieldsNames.length, is(equalTo(5)));
	}
	
	@Test
	public void testExtractFieldsNamesAgency(){
		String[] fieldsNames = ReflectionData.getInstance().extractFieldsNames(Agency.class);
		assertThat(fieldsNames, notNullValue());
		assertThat(fieldsNames.length, is(equalTo(5)));
	}
	
	@Test
	public void testExtractFieldsNamesStops(){
		String[] fieldsNames = ReflectionData.getInstance().extractFieldsNames(Stops.class);
		assertThat(fieldsNames, notNullValue());
		assertThat(fieldsNames.length, is(equalTo(10)));
	}
	@Test
	public void testExtractFieldsNamesCalendar(){
		String[] fieldsNames = ReflectionData.getInstance().extractFieldsNames(Calendar.class);
		assertThat(fieldsNames, notNullValue());
		assertThat(fieldsNames.length, is(equalTo(10)));
	}
	@Test
	public void testExtractFieldsNamesCalendarDates(){
		String[] fieldsNames = ReflectionData.getInstance().extractFieldsNames(CalendarDates.class);
		assertThat(fieldsNames, notNullValue());
		assertThat(fieldsNames.length, is(equalTo(3)));
	}
	
	@Test
	public void testExtractFieldsNamesCalendarTransfers(){
		String[] fieldsNames = ReflectionData.getInstance().extractFieldsNames(Transfers.class);
		assertThat(fieldsNames, notNullValue());
		assertThat(fieldsNames.length, is(equalTo(4)));
	}
	
	@Test
	public void testBuildListRoutes() throws Exception{
		List<Routes> buildList = ReflectionData.getInstance().buildList(Routes.class, data1);
		assertThat(buildList, notNullValue());
		assertThat(buildList.size(), is(equalTo(1)));
		assertThat(buildList.iterator().next().getRoute_id(), is(equalTo("AAAA")));
		
	}
	@Test
	public void testBuildListTrips() throws Exception{
		List<Trips> buildList = ReflectionData.getInstance().buildList(Trips.class, data2);
		assertThat(buildList, notNullValue());
		assertThat(buildList.size(), is(equalTo(1)));
		assertThat(buildList.iterator().next().getRoute_id(), is(equalTo("AAAA")));

	}
	@Test
	public void testBuildListStopTines() throws Exception{
		List<StopTimes> buildList = ReflectionData.getInstance().buildList(StopTimes.class, data3);
		assertThat(buildList, notNullValue());
		assertThat(buildList.size(), is(equalTo(1)));
		assertThat(buildList.iterator().next().getTrip_id(), is(equalTo("AAAA")));
	}
	@Test
	public void testBuildListShapes() throws Exception{
		List<Shapes> buildList = ReflectionData.getInstance().buildList(Shapes.class, data4);
		assertThat(buildList, notNullValue());
		assertThat(buildList.size(), is(equalTo(1)));
		assertThat(buildList.iterator().next().getShape_id(), is(equalTo("AAAA")));
	}
	@Test
	public void testBuildListAgency() throws Exception{
		List<Agency> buildList = ReflectionData.getInstance().buildList(Agency.class, data5);
		assertThat(buildList, notNullValue());
		assertThat(buildList.size(), is(equalTo(1)));
		assertThat(buildList.iterator().next().getAgency_id(), is(equalTo("AAAA")));
	}
	@Test
	public void testBuildListStops() throws Exception{
		List<Stops> buildList = ReflectionData.getInstance().buildList(Stops.class, data6);
		assertThat(buildList, notNullValue());
		assertThat(buildList.size(), is(equalTo(1)));
		assertThat(buildList.iterator().next().getStop_id(), is(equalTo("AAAA")));
	}
	@Test
	public void testBuildListCalendar() throws Exception{
		List<Calendar> buildList = ReflectionData.getInstance().buildList(Calendar.class, data7);
		assertThat(buildList, notNullValue());
		assertThat(buildList.size(), is(equalTo(1)));
		assertThat(buildList.iterator().next().getService_id(), is(equalTo("AAAA")));
	}
	@Test
	public void testBuildListCalendarDates() throws Exception{
		List<CalendarDates> buildList = ReflectionData.getInstance().buildList(CalendarDates.class, data8);
		assertThat(buildList, notNullValue());
		assertThat(buildList.size(), is(equalTo(1)));
		assertThat(buildList.iterator().next().getService_id(), is(equalTo("AAAA")));
	}
	@Test
	public void testBuildListCalendarTransfers() throws Exception{
		List<Transfers> buildList = ReflectionData.getInstance().buildList(Transfers.class, data9);
		assertThat(buildList, notNullValue());
		assertThat(buildList.size(), is(equalTo(1)));
		assertThat(buildList.iterator().next().getFrom_stop_id(), is(equalTo("AAAA")));
	}
}
