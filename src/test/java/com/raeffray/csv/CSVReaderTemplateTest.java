package com.raeffray.csv;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * {@link CSVReaderTemplate} unit tests.
 */
public class CSVReaderTemplateTest {

    @Test
    public void testRead() throws IOException {
        final InputStream stream = getClass().getResourceAsStream("/dummy.csv");
        final InputStreamReader reader = new InputStreamReader(stream);
        final CSVReaderTemplate csvReaderTemplate = new CSVReaderTemplate();
        final List<Map<String, String>> routeInfo = new LinkedList<>();
        csvReaderTemplate.read(reader, new CSVRowHandler() {
            @Override
            public void processLine(Map<String, String> line) {
                System.out.println(line);
                routeInfo.add(line);
            }
        });
        assertThat(routeInfo.size(), is(equalTo(2)));
        assertThat(routeInfo.get(0).get("field_0"), is(equalTo("value_0.0")));
        assertThat(routeInfo.get(0).get("field_1"), is(equalTo("value_1.0")));
        assertThat(routeInfo.get(0).get("field_2"), is(equalTo("value_2.0")));
        assertThat(routeInfo.get(1).get("field_0"), is(equalTo("value_0.1")));
        assertThat(routeInfo.get(1).get("field_1"), is(equalTo("value_1.1")));
        assertThat(routeInfo.get(1).get("field_2"), is(equalTo("value_2.1")));
    }
}
