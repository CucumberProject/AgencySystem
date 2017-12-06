package Coupon;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

// Prueba parar GitHub
	@RunWith(Cucumber.class) 
	@CucumberOptions(
			features = "Features//Coupon//use_coupon.feature"
			)
	public class useCoupon {
}
