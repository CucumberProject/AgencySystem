package Coupon;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import org.junit.Assert;
import pageWebElementBox.infoScreen;

public class availableCoupon_Steps {
	
	WebDriver driver;
	
	@Before("@AvailableCoupon")
	public void StartDiver() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@After("@AvailableCoupon")
	public void StopDriver(){
		driver.quit();
	}
	
	@Given("^user is on admin page$")
	public void user_is_on_admin_page() throws Throwable {
		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
//	    throw new PendingException();
	}

	@When("^username as \"([^\"]*)\" And I provide password as \"([^\"]*)\" to login$")
	public void username_as_And_I_provide_password_as_to_login(String arg1, String arg2) throws Throwable {
		driver.findElement(infoScreen.usernameAdmin).sendKeys(arg1);
		driver.findElement(infoScreen.passwordAdmin).sendKeys(arg2);
		driver.findElement(infoScreen.LogInButtonAdmin).click();
//	    throw new PendingException();
	}
	
	@When("^user enter coupon page$")
	public void user_enter_coupon_page() throws Throwable{
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(infoScreen.couponpage));
		driver.findElement(infoScreen.couponpage).click();
//	    throw new PendingException();
	}
	
	@And("^user add a coupon available$")
	public void user_add_a_coupon_available(DataTable coupon1) throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<List<String>> data = coupon1.raw();
		driver.findElement(infoScreen.addButton).click();
		driver.findElement(infoScreen.discount).sendKeys(data.get(0).get(0));
		driver.findElement(infoScreen.maxUse).sendKeys(data.get(0).get(1));
		driver.findElement(infoScreen.codeText).sendKeys(data.get(0).get(2));
		driver.findElement(infoScreen.AllHotels).click();
		driver.findElement(infoScreen.addCoupon).click();
//	    throw new PendingException();
	}
	
	@Given("^user log on initial page$")
	public void user_log_on_initial_page(DataTable usercredentials) throws Throwable {
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

	@When("^user book a room$")
	public void user_book_a_room(DataTable coupon1) throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<List<String>> data = coupon1.raw();
		
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.validation1));
		driver.findElement(infoScreen.HotelsList).click();
    	driver.findElement(infoScreen.Hotel).click();
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,750)", "");
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

	@Then("^user see the reserve booked$")
	public void user_see_the_reserve_booked() throws Throwable {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBe(infoScreen.reservedValidation, "Reserved"));
		String check = driver.findElement(infoScreen.reservedValidation).getText();
		Assert.assertEquals(check, "Reserved");
		System.out.println("Coupon enable used"); 
//	    throw new PendingException();
	}

	@Given("^user disable the coupon used$")
	public void user_disable_the_coupon_used(DataTable adminCredentials) throws Throwable {
		List<List<String>> data = adminCredentials.raw();
		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
		
		driver.findElement(infoScreen.usernameAdmin).sendKeys(data.get(0).get(0));
		driver.findElement(infoScreen.passwordAdmin).sendKeys(data.get(0).get(1));
		driver.findElement(infoScreen.LogInButtonAdmin).click();
		
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(infoScreen.couponpage));
		driver.findElement(infoScreen.couponpage).click();
		driver.findElement(infoScreen.editButton).click();
		Select dropdown = new Select(driver.findElement(infoScreen.enableDropdown));
		dropdown.selectByVisibleText(data.get(0).get(2));
		driver.findElement(infoScreen.submitEditButton).click();
//	    throw new PendingException();
	}

	@Given("^user book a new room with the coupon disable$")
	public void user_book_a_new_room_with_the_coupon_disable(DataTable user) throws Throwable {
		driver.navigate().to(" http://www.phptravels.net/");
    	driver.navigate().to("http://www.phptravels.net/");
    	driver.manage().window().maximize();
    	
    	driver.findElement(infoScreen.accountButton).click();
	    driver.findElement(infoScreen.singinButton).click();
	    
	    List<List<String>> data = user.raw();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,250)", "");
        driver.findElement(infoScreen.usernameUser).sendKeys(data.get(0).get(0));
	    driver.findElement(infoScreen.passwordUser).sendKeys(data.get(0).get(1)); 
	    (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(infoScreen.LogInButtonUser));
	    driver.findElement(infoScreen.LogInButtonUser).click();	 
	    
	    (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.validation1));
		driver.findElement(infoScreen.HotelsList).click();
    	driver.findElement(infoScreen.Hotel).click();
    	JavascriptExecutor jse1 = (JavascriptExecutor)driver;
    	jse1.executeScript("window.scrollBy(0,750)", "");
    	driver.findElement(infoScreen.Room).click();
    	
    	JavascriptExecutor jse2 = (JavascriptExecutor)driver;
    	jse2.executeScript("window.scrollBy(0,850)", "");
    	driver.findElement(infoScreen.couponcode).sendKeys(data.get(0).get(2));
    	driver.findElement(infoScreen.couponSubmit).click();
//	    throw new PendingException();
	}

	@Then("^invalid coupon message is displaying$")
	public void invalid_coupon_message_is_displaying() throws Throwable {
		new WebDriverWait(driver, 60)
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
