package Coupon;

import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import pageWebElementBox.infoScreen;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class couponDates_Steps {
	WebDriver driver;
	
	@Before("@CouponDates")
	public void StartDiver() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@After("@CouponDates")
	public void StopDriver(){
		driver.quit();
	}
	
	@Given("^User entar to admin page$")
	public void user_entar_to_admin_page() throws Throwable {
		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
//	    throw new PendingException();
	}

	@And("^username and password are provideted$")
	public void username_and_password_are_provideted(DataTable adminCredentials) throws Throwable {
		List<List<String>> data = adminCredentials.raw();
		driver.findElement(infoScreen.usernameAdmin).sendKeys(data.get(0).get(0));
		driver.findElement(infoScreen.passwordAdmin).sendKeys(data.get(0).get(1));
		driver.findElement(infoScreen.LogInButtonAdmin).click();
//	    throw new PendingException();
	}

	@When("^user enter to coupon page$")
	public void user_enter_to_coupon_page() throws Throwable {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(infoScreen.couponpage));
		driver.findElement(infoScreen.couponpage).click();
//	    throw new PendingException();
	}

	@When("^user add two coupos one with valid dates and another with invalid dates$")
	public void user_add_two_coupos_one_with_valid_dates_and_another_with_invalid_dates(DataTable coupons) throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Valid coupon
		List<List<String>> data = coupons.raw();
		driver.findElement(infoScreen.addButton).click();
		driver.findElement(infoScreen.discount).sendKeys(data.get(0).get(0));
		driver.findElement(infoScreen.maxUse).sendKeys(data.get(0).get(1));
		driver.findElement(infoScreen.startDate).sendKeys(data.get(0).get(2));
		driver.findElement(infoScreen.expDate).sendKeys(data.get(0).get(3));
		driver.findElement(infoScreen.codeText).sendKeys(data.get(0).get(4));
		driver.findElement(infoScreen.AllHotels).click();
		driver.findElement(infoScreen.addCoupon).click();
		
		// Invalid coupon
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBe(infoScreen.coupon1, "Coupon1"));
		driver.findElement(infoScreen.addButton).click();
		driver.findElement(infoScreen.discount).sendKeys(data.get(1).get(0));
		driver.findElement(infoScreen.maxUse).sendKeys(data.get(1).get(1));
		driver.findElement(infoScreen.startDate).sendKeys(data.get(1).get(2));
		driver.findElement(infoScreen.expDate).sendKeys(data.get(1).get(3));
		driver.findElement(infoScreen.codeText).sendKeys(data.get(1).get(4));
		driver.findElement(infoScreen.AllHotels).click();
		driver.findElement(infoScreen.addCoupon).click();
//	    throw new PendingException();
	}

	@Then("^both coupons are created successfuly$")
	public void both_coupons_are_created_successfuly() throws Throwable {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBe(infoScreen.coupon1, "Coupon2"));
		String coupon1 = driver.findElement(infoScreen.coupon1).getText();
		String coupon2 = driver.findElement(infoScreen.coupon2).getText();
		if(coupon1.equals("Coupon2") && coupon2.equals("Coupon1")) {
			System.out.println("Coupons created successfully");
		}
		else {
			System.out.println("Coupons were not created");
		}
//	    throw new PendingException();
	}

	@Given("^user enter tu user page$")
	public void user_enter_tu_user_page(DataTable usercredentials) throws Throwable {
		driver.navigate().to(" http://www.phptravels.net/");
		driver.manage().window().maximize();
		driver.findElement(infoScreen.accountButton).click();
	    driver.findElement(infoScreen.singinButton).click();
		List<List<String>> data = usercredentials.raw();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,250)", "");
        driver.findElement(infoScreen.usernameUser).sendKeys(data.get(0).get(0));
	    driver.findElement(infoScreen.passwordUser).sendKeys(data.get(0).get(1)); 
	    (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(infoScreen.LogInButtonUser));
	    driver.findElement(infoScreen.LogInButtonUser).click();
//	    throw new PendingException();
	}

	@And("^user book a hotel with the valid coupon$")
	public void user_book_a_hotel_with_the_valid_coupon(DataTable Coupon1) throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<List<String>> data = Coupon1.raw();

		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.validation1));
		driver.findElement(infoScreen.HotelsList).click();
    	driver.findElement(infoScreen.Hotel).click();
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,750)", "");
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.Room));
    	driver.findElement(infoScreen.Room).click();
    	
    	JavascriptExecutor jse2 = (JavascriptExecutor)driver;
    	jse2.executeScript("window.scrollBy(0,850)", "");
    	driver.findElement(infoScreen.couponcode).sendKeys(data.get(0).get(0));
    	driver.findElement(infoScreen.couponSubmit).click();
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.validation2));
    	driver.findElement(infoScreen.bookHotel).click();
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.validation3));
    	driver.findElement(infoScreen.payOnArrive).click();
    	driver.switchTo().alert().accept();
//	    throw new PendingException();
	}

	@And("^user try to use the invalid coupon$")
	public void user_try_to_use_the_invalid_coupon(DataTable Coupon2) throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<List<String>> data = Coupon2.raw();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBe(infoScreen.reservedValidation, "Reserved"));
    	driver.findElement(infoScreen.HotelsList).click();
    	driver.findElement(infoScreen.Hotel).click();
    	JavascriptExecutor jse3 = (JavascriptExecutor)driver;
    	jse3.executeScript("window.scrollBy(0,750)", "");
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.Room));
    	driver.findElement(infoScreen.Room).click();
    	
    	JavascriptExecutor jse4 = (JavascriptExecutor)driver;
    	jse4.executeScript("window.scrollBy(0,850)", "");
    	driver.findElement(infoScreen.couponcode).sendKeys(data.get(0).get(0));
    	driver.findElement(infoScreen.couponSubmit).click();
//	    throw new PendingException();
	}

	@Then("^user receives a message$")
	public void user_receives_a_message() throws Throwable {
		new WebDriverWait(driver, 10)
        .ignoring(NoAlertPresentException.class)
        .until(ExpectedConditions.alertIsPresent());
			Alert al = driver.switchTo().alert();
			String message = driver.switchTo().alert().getText();
			al.accept();
			Assert.assertEquals(message, "Invalid Coupon");
			JOptionPane.showMessageDialog(null,"Test Passed"); 
//	    throw new PendingException();
	}

}
