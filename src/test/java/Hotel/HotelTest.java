package Hotel;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"Features//Hotel//newHotel_coupon.feature"},
		glue = {"Hotel"}
		//,tags = {"@update"}
		)


public class HotelTest {

}

