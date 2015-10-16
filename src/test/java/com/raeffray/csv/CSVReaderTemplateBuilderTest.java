package com.raeffray.csv;

import com.raeffray.raw.data.Routes;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

import java.io.IOException;

import static com.raeffray.csv.CSVReaderTemplateBuilder.readCSVForClass;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * {@link CSVReaderTemplateBuilder} unit tests.
 */
public class CSVReaderTemplateBuilderTest {

    private int counter = 0;
    private Routes routes;

    @Test
    public void test() throws IOException {
        readCSVForClass(Routes.class).processWith(new InstanceHandler<Routes>() {
            @Override
            public void processInstance(Routes instance) {
                routes = instance;
                System.out.println(ToStringBuilder.reflectionToString(instance));
                assertThat(counter++, is(equalTo(0)));
            }
			@Override
			public void endProcess() {
				// TODO Auto-generated method stub
				
			}
        });
        // AAAA,BBBB,CCC,DDDE,EEEE,FFFF,GGGG,HHHH,IIII
        assertThat(routes.getRoute_id(), is(equalTo("AAAA")));
        assertThat(routes.getAgency_id(), is(equalTo("BBBB")));
        assertThat(routes.getRoute_short_name(), is(equalTo("CCC")));
        assertThat(routes.getRoute_long_name(), is(equalTo("DDDE")));
        assertThat(routes.getRoute_desc(), is(equalTo("EEEE")));
        assertThat(routes.getRoute_type(), is(equalTo("FFFF")));
        assertThat(routes.getRoute_url(), is(equalTo("GGGG")));
        assertThat(routes.getRoute_color(), is(equalTo("HHHH")));
        assertThat(routes.getRoute_text_color(), is(equalTo("IIII")));
    }
}
