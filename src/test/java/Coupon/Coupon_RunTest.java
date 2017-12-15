package Coupon;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

	@RunWith(Cucumber.class) 
	@CucumberOptions(
			features = "Features//Coupon//coupon_dates.feature"
			//features = "Features//Coupon//CreateCouponforHotelsOnly.feature"

			)
	public class Coupon_RunTest {
}
