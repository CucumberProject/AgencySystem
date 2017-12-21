package Hotel;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		//features = {"Features//Hotel//amenitiesroom.feature"},
		//features = {"Features//Hotel//bookinghotel.feature"},
		//features = {"Features//Hotel//checkinfo.feature"},
		//features = {"Features//Hotel//hotelfacilities.feature"},
		//features = {"Features//Hotel//newhotel.feature"},
		features = {"Features//Hotel//roombooking.feature"},
		//features = {"Features//Hotel//supplierbooking.feature"},
		glue = {"Hotel"}
		,tags = {"@unavailable"}
		)


public class HotelTest {

}

