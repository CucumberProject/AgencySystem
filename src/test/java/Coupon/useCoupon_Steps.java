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


public class useCoupon_Steps {
	
	static WebDriver driver;
	
	@Before
	public void StartDiver() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@After
	public void StopDriver(){
		driver.quit();
	}

	@Given("^I am on admin login home page$")
	public void i_am_on_admin_login_home_page() throws Throwable {
		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
//	    throw new PendingException();
	}
	
	@When("^I provide username and password$")
	public void I_provide_username_and_password(DataTable admincredentials) throws Throwable {
		//Login
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<List<String>> data = admincredentials.raw();
		driver.findElement(infoScreen.usernameAdmin).sendKeys(data.get(0).get(0));
		driver.findElement(infoScreen.passwordAdmin).sendKeys(data.get(0).get(1));
		driver.findElement(infoScreen.LogInButtonAdmin).click();
//	    throw new PendingException();
	}
		
	@And("^User go to the coupon page$")
	public void User_go_to_the_coupon_page() throws Throwable {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(infoScreen.couponpage));
		driver.findElement(infoScreen.couponpage).click();
//		    throw new PendingException();
	}
	
	@When("^User add a new coupon$")
	public void User_add_a_new_coupon(DataTable coupon1) throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<List<String>> data = coupon1.raw();
		driver.findElement(infoScreen.addButton).click();
		driver.findElement(infoScreen.discount).sendKeys(data.get(0).get(0));
		driver.findElement(infoScreen.maxUse).sendKeys(data.get(0).get(1));
		driver.findElement(infoScreen.codeText).sendKeys(data.get(0).get(2));
		driver.findElement(infoScreen.addCoupon).click();
//	    throw new PendingException();
	}
	
	@Given("^User is on home Page$")
	public void user_is_on_home_Page() throws Throwable {
    	driver.navigate().to("http://www.phptravels.net/");
    	driver.manage().window().maximize();
	    //throw new PendingException();
	}

	@When("^User enter to login page$")
	public void User_enter_to_login_page() throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(infoScreen.accountButton).click();
	    driver.findElement(infoScreen.singinButton).click();
	    //throw new PendingException();
	}

	@When("^user enters username and password$")
	public void user_enters_username_and_password(DataTable usercredentials) throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<List<String>> data = usercredentials.raw();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,250)", "");
        driver.findElement(infoScreen.usernameUser).sendKeys(data.get(0).get(0));
	    driver.findElement(infoScreen.passwordUser).sendKeys(data.get(0).get(1)); 
	    (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(infoScreen.LogInButtonUser));
	    driver.findElement(infoScreen.LogInButtonUser).click();
	    
//	    throw new PendingException();
	}

	@When("^user book a new hotel with a coupon$")
	public void user_book_a_new_hotel_with_a_coupon(DataTable usercredentials) throws Throwable {
		List<List<String>> data = usercredentials.raw();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.validation1));
		for(int i = 0; i < 2; i++) {
			driver.findElement(infoScreen.HotelsList).click();
	    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.Hotel));
	    	driver.findElement(infoScreen.Hotel).click();
	    	driver.navigate().refresh();
	    	JavascriptExecutor jse = (JavascriptExecutor)driver;
	    	jse.executeScript("window.scrollBy(0,750)", "");
	    	driver.findElement(infoScreen.Room).click();
	    	
	    	JavascriptExecutor jse2 = (JavascriptExecutor)driver;
	    	jse2.executeScript("window.scrollBy(0,850)", "");
	    	driver.findElement(infoScreen.couponcode).sendKeys(data.get(0).get(0));
	    	driver.findElement(infoScreen.couponSubmit).click();
	    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(infoScreen.validation2));
	    	driver.findElement(infoScreen.bookHotel).click();
	    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.validation3));
	    	driver.findElement(infoScreen.payOnArrive).click();
	    	driver.switchTo().alert().accept();
	    	driver.findElement(infoScreen.HotelsList).click();
	    }
//	    throw new PendingException()
	    ;
	}
	
	@When("^user wants to use a coup over of its max limit$")
	public void user_wants_to_use_a_coupon_over_of_its_max_limit(DataTable usercredentials) throws Throwable {
		List<List<String>> data = usercredentials.raw();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(infoScreen.HotelsList).click();
    	driver.findElement(infoScreen.Hotel).click();
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,750)", "");
    	driver.findElement(infoScreen.Room).click();
    	
    	JavascriptExecutor jse2 = (JavascriptExecutor)driver;
    	jse2.executeScript("window.scrollBy(0,850)", "");
    	driver.findElement(infoScreen.couponcode).sendKeys(data.get(0).get(0));
    	driver.findElement(infoScreen.couponSubmit).click();
//	    throw new PendingException();
	}

	@Then("^message of invalid coupon is successufully displayed$")
	public void message_of_invalid_coupon_is_successufully_displayed() throws Throwable {
		
		new WebDriverWait(driver, 60)
        .ignoring(NoAlertPresentException.class)
        .until(ExpectedConditions.alertIsPresent());
			Alert al = driver.switchTo().alert();
			String message = driver.switchTo().alert().getText();
			al.accept();
			System.out.println(message);
			Assert.assertEquals(message, "Invalid Coupon");
			JOptionPane.showMessageDialog(null,"Test Passed"); 
//	    throw new PendingException();
		}
	}
