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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class generatedCoupon_Steps {
	
	WebDriver driver;
	static String idBook1, idBook2;
	
	@Before("@GeneratedCoupon")
	public void StartDiver() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@After("@GeneratedCoupon")
	public void StopDriver(){
		driver.quit();
	}
	
	@Given("^I am on admin Login home page$")
	public void i_am_on_admin_Login_home_page() throws Throwable {
		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
//	    throw new PendingException();
	}
	
	@When("^I provide username as \"([^\"]*)\" And I enter password as \"([^\"]*)\" to login$")
	public void i_provide_username_as_And_I_enter_password_as_to_login(String arg1, String arg2) throws Throwable {
		//Login
		driver.findElement(infoScreen.usernameAdmin).sendKeys(arg1);
		driver.findElement(infoScreen.passwordAdmin).sendKeys(arg2);
		driver.findElement(infoScreen.LogInButtonAdmin).click();
//	    throw new PendingException();
	}
		
	@And("^user go to the coupon page$")
	public void user_go_to_the_coupon_page() throws Throwable {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(infoScreen.couponpage));
		driver.findElement(infoScreen.couponpage).click();
//		    throw new PendingException();
	}
	
	@When("^user add a new coupon with a manual code$")
	public void user_add_a_new_coupon_with_a_manual_code(DataTable coupon1) throws Throwable {
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

	@When("^a new coupon is added with a autogenerated code$")
	public void a_new_coupon_is_added_with_a_autogenerated_code(DataTable coupon2) throws Throwable {
		List<List<String>> data = coupon2.raw();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBe(infoScreen.coupon1, "Manual"));
		driver.findElement(infoScreen.addButton).click();
		driver.findElement(infoScreen.discount).sendKeys(data.get(0).get(0));
		driver.findElement(infoScreen.maxUse).sendKeys(data.get(0).get(1));
		driver.findElement(infoScreen.codeText).sendKeys(data.get(0).get(2));
		driver.findElement(infoScreen.AllHotels).click();
		driver.findElement(infoScreen.addCoupon).click();
//	    throw new PendingException();
	}

	@Then("^both coupon should be visible$")
	public void both_coupon_should_be_visible() throws Throwable {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBe(infoScreen.coupon1, "Auto"));
		String coupon1 = driver.findElement(infoScreen.coupon1).getText();
		String coupon2 = driver.findElement(infoScreen.coupon2).getText();
		if(coupon1.equals("Auto") && coupon2.equals("Manual")) {
			System.out.println("Coupons created successfully");
		}
		else {
			System.out.println("Coupons were not created");
		}
	}

	@Given("^user enter to home page$")
	public void user_enter_to_home_page(DataTable usercredentials) throws Throwable {
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

	@When("^coupon created automatacally and manually are used$")
	public void coupon_created_automatacally_and_manually_are_used(DataTable coupon1) throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<List<String>> data = coupon1.raw();
		
		//Manual
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
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.idValidation));
    	idBook1 = driver.findElement(infoScreen.idValidation).getText();
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.validation3));
    	driver.findElement(infoScreen.payOnArrive).click();
    	driver.switchTo().alert().accept();
    	
    	//Automatic
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBe(infoScreen.reservedValidation, "Reserved"));
    	driver.findElement(infoScreen.HotelsList).click();
    	driver.findElement(infoScreen.Hotel).click();
    	JavascriptExecutor jse3 = (JavascriptExecutor)driver;
    	jse3.executeScript("window.scrollBy(0,750)", "");
    	driver.findElement(infoScreen.Room).click();
    	
    	JavascriptExecutor jse4 = (JavascriptExecutor)driver;
    	jse4.executeScript("window.scrollBy(0,850)", "");
    	driver.findElement(infoScreen.couponcode).sendKeys(data.get(0).get(1));
    	driver.findElement(infoScreen.couponSubmit).click();
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.validation2));
    	driver.findElement(infoScreen.bookHotel).click();
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.idValidation));
    	idBook2 = driver.findElement(infoScreen.idValidation).getText();
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.validation3));
    	driver.findElement(infoScreen.payOnArrive).click();
    	driver.switchTo().alert().accept();
//	    throw new PendingException();
	}

	@Then("^both books can be visible on the account$")
	public void both_books_can_be_visible_on_the_account() throws Throwable {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBe(infoScreen.reservedValidation, "Reserved"));
		driver.findElement(infoScreen.accountButton).click();
	    driver.findElement(infoScreen.accountPage).click();
	    
	    if (driver.getPageSource().contains(idBook1) && driver.getPageSource().contains(idBook2)){
	    	JOptionPane.showMessageDialog(null,"Test Passed"); 
 	    }else{
 	    	System.out.println("Test Failed");
 	    } 
//	    throw new PendingException();
	}
}
	
		
