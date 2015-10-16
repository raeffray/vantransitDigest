package com.raeffray.csv;

import com.raeffray.raw.data.Routes;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * {@link InstanceConverter} unit tests.
 */
public class InstanceConverterTest {

    private Routes routes;

    @Test
    public void test() {
        final InstanceConverter<Routes> converter = new InstanceConverter<>(Routes.class, new InstanceHandler<Routes>() {
            @Override
            public void processInstance(Routes instance) {
                routes = instance;
            }

			@Override
			public void endProcess() {
				// foo
				
			}
        });
        converter.processLine(fakeRow());
        assertThat(routes.getRoute_id(), is(equalTo("aaa")));
        assertThat(routes.getRoute_short_name(), is(equalTo("bbb")));
        assertThat(routes.getRoute_long_name(), is(equalTo("ccc")));
        assertThat(routes.getRoute_desc(), is(equalTo("ddd")));
        assertThat(routes.getRoute_type(), is(equalTo("eee")));
        assertThat(routes.getRoute_url(), is(equalTo("fff")));
    }

    private Map<String, String> fakeRow() {
        Map<String, String> row = new HashMap<>();
        row.put("route_id", "aaa");
        row.put("route_short_name", "bbb");
        row.put("route_long_name", "ccc");
        row.put("route_desc", "ddd");
        row.put("route_type", "eee");
        row.put("route_url", "fff");
        return row;
    }
}
