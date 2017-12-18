package Coupon;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageWebElementBox.infoScreen;

public class CreateCouponForToursOnly_Steps {
	
	Admin admin = new Admin();
	User user = new User();
	Coupon coupon = new Coupon();
	WebDriver driver = null;
	
	static infoScreen infoScreen;
	Hotel hotel = new Hotel();
	Tour tour = new Tour();
	Car car = new Car();
	static String couponDiscount;	
	
	@Before("@createCouponsForToursOnly")
	public void startDriver() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		
	}	
	
	//Background: 
	@Given ("^I log in as an admin user$")
	public void I_log_in_as_an_admin_user(DataTable adminCredentials) {
		//System.out.println("Starting background...");
		driver.navigate().to("http://www.phptravels.net/admin");
		driver.manage().window().maximize();
		List<List<String>> adminData = adminCredentials.raw();
		String user = adminData.get(1).get(0);
		String password = adminData.get(1).get(1);
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.LogInButtonAdmin));
		admin.logIn(driver, user, password);		
		
	}
	
	/*@When("^I go to the Coupon Manager screen$")
	public void I_go_to_the_Coupon_Manager_screen() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.couponpage));
		admin.openCouponManagerScreen(driver);
	}*/
	
	@And("^I create a coupon for all tours only$")
	public void I_create_a_coupon_for_all_tours_only(DataTable couponInfo) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.couponpage));
		admin.openCouponManagerScreen(driver);
		List<List<String>> couponData = couponInfo.raw();
		String discount = couponData.get(1).get(0);
		String maxUses = couponData.get(1).get(1);
		String code = couponData.get(1).get(2);
		couponDiscount = discount;
		coupon.createACoupon(driver, discount, maxUses, code);
		WebDriverWait wait2 = new WebDriverWait(driver,10);
		wait2.until(ExpectedConditions.elementToBeClickable(infoScreen.addButton));
		coupon.checkIfCouponIsCreated(driver);
		
	}
	
	@Then("^A coupon for all Tours should be created$")
	public void A_coupon_for_all_hotels_should_be_created() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.addButton));
		coupon.checkIfCouponIsCreated(driver);
		driver.quit();
	}
	
	//Scenario: Coupon is valid for tour booking
	@Given("^I log in as a user$")
	public void I_log_in_as_a_user(DataTable userCredentials) {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("http://www.phptravels.net/login");
		driver.manage().window().maximize();
		List<List<String>> userData = userCredentials.raw();
		String userName = userData.get(1).get(0);
		String password = userData.get(1).get(1);
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.LogInButtonUser));
		user.logIn(driver, userName, password);		
	}
	
	@When("^I start booking a tour$")
	public void I_start_booking_a_tour() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"body-section\"]/div/div[1]/div/div[1]/h3")));
		tour.bookATour(driver);
		
	}
	
	@And("^I use a coupon as \"(.*)\" for tour$")
	public void I_use_a_coupon_as_for_tour(String coupon) {
		String couponCode = coupon;
		WebDriverWait wait = new WebDriverWait(driver,10); 
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"bookingdetails\"]/div[5]/div[2]/div[2]/div[3]/span"))));
		tour.useACoupon(driver, couponCode);		
	}
	
	
	@Then("^The price should have a discount$")
	public void The_price_should_have_a_discount(){
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"bookingdetails\"]/div[5]/div[2]/div[4]")));
		tour.checkDiscount(driver, couponDiscount);		
	}
	
	
	//Scenario: Coupon is not valid for tours booking
	@When("^I start booking a hotel$")
	public void I_start_booking_a_hotel() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"body-section\"]/div/div[1]/div/div[1]/h3")));
		hotel.bookAHotel(driver);		
	}
	
	@And("^I use a coupon for tour as \"(.*)\" in a hotel$")
	public void I_use_a_coupon_for_tour_as_in_a_hotel(String coupon) {
		String couponCode = coupon;
		WebDriverWait wait = new WebDriverWait(driver,10); 
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"bookingdetails\"]/div[5]/div[2]/div[2]/div[3]/span"))));
		hotel.useACoupon(driver, couponCode);	
	}
	
	@Then("^The coupon should not be valid for hotel$")
	public void The_coupon_should_not_be_valid_for_hotel() {
		hotel.validateCoupon(driver);
	}
	
	
	//Scenario: Coupon is not valid for cars booking
	@Then("^I start booking a car$")
	public void I_start_booking_a_car() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"body-section\"]/div/div[1]/div/div[1]/h3")));
		car.bookACar(driver);
		
	}
	
	@And("^I use a coupon for hotel as \"(.*)\" in a car$")
	public void I_use_a_coupone_as_for_hotel_in_a_car(String coupon) {
		String couponCode = coupon;
		WebDriverWait wait = new WebDriverWait(driver,10); 
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"bookingdetails\"]/div[5]/div[2]/div[2]/div[3]/span"))));
		car.useACoupon(driver, couponCode);
	}
	
	@Then("^The coupon should not be valid for car$")
	public void The_coupon_should_not_be_valid_for_car() {
		car.validateCoupon(driver);		
	}

	
	@After("@createCouponsForToursOnly")
	public void stopDriver() {
		//coupon.deleteCoupon();
		driver.quit();
	}

}
