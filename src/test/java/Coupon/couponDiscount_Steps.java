package Coupon;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
//import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageWebElementBox.infoScreen;

public class couponDiscount_Steps {
	
	WebDriver driver;
	static String sBill1, sTax1, sTotal1, sBill2, sTax2, sTotal2;
	static float iBill1, iTax1, iTotal1, discount1, total1, iBill2, iTax2, iTotal2, discount2, total2;
	
	@Before
	public void StartDiver() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@After
	public void StopDriver(){
		driver.quit();
	}
	
	@Given("^I am on admin home page$")
	public void i_am_on_admin_home_page() throws Throwable {
		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
//	    throw new PendingException();
	}

	@When("^I provide username as \"([^\"]*)\" And I provide password as \"([^\"]*)\" to login$")
	public void i_provide_username_as_And_I_provide_password_as_to_login(String arg1, String arg2) throws Throwable {
		//Login
		driver.findElement(infoScreen.usernameAdmin).sendKeys(arg1);
		driver.findElement(infoScreen.passwordAdmin).sendKeys(arg2);
		driver.findElement(infoScreen.LogInButtonAdmin).click();
//	    throw new PendingException();
	}

	@When("^user select coupon page$")
	public void user_select_coupon_page() throws Throwable {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(infoScreen.couponpage));
		driver.findElement(infoScreen.couponpage).click();
//	    throw new PendingException();
	}

	@When("^user add two new coupons with a discount$")
	public void user_add_two_new_coupons_with_a_discount(DataTable promo) throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<List<String>> data = promo.raw();
		
		//45% of discount
		driver.findElement(infoScreen.addButton).click();
		driver.findElement(infoScreen.discount).sendKeys(data.get(0).get(0));
		driver.findElement(infoScreen.maxUse).sendKeys(data.get(0).get(1));
		driver.findElement(infoScreen.codeText).sendKeys(data.get(0).get(2));
		driver.findElement(infoScreen.AllHotels).click();
		driver.findElement(infoScreen.addCoupon).click();
		
		//75% of discount
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBe(infoScreen.coupon1, "Promo1"));
		driver.findElement(infoScreen.addButton).click();
		driver.findElement(infoScreen.discount).sendKeys(data.get(1).get(0));
		driver.findElement(infoScreen.maxUse).sendKeys(data.get(1).get(1));
		driver.findElement(infoScreen.codeText).sendKeys(data.get(1).get(2));
		driver.findElement(infoScreen.AllHotels).click();
		driver.findElement(infoScreen.addCoupon).click();
//	    throw new PendingException();
	}

	@Then("^coupons should are visible$")
	public void coupons_should_are_visible() throws Throwable {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBe(infoScreen.coupon1, "Promo2"));
		String coupon1 = driver.findElement(infoScreen.coupon1).getText();
		String coupon2 = driver.findElement(infoScreen.coupon2).getText();
		if(coupon1.equals("Promo2") && coupon2.equals("Promo1")) {
			System.out.println("Coupons created successfully");
		}
		else {
			System.out.println("Coupons were not created");
		}
//	    throw new PendingException();
	}

	@Given("^user enter account initial page$")
	public void user_enter_account_initial_page(DataTable usercredentials) throws Throwable {
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

	@When("^coupon promo1 is used$")
	public void coupon_promo1_is_used(DataTable promo1) throws Throwable {
		List<List<String>> data = promo1.raw();
		//Coupon with promo1
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
    	
    	sBill1 = driver.findElement(infoScreen.bill).getText();
		sTax1 = driver.findElement(infoScreen.tax).getText();
		JavascriptExecutor jse21 = (JavascriptExecutor)driver;
    	jse21.executeScript("window.scrollBy(0,450)", "");
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.total));
		sTotal1 = driver.findElement(infoScreen.total).getText();
//	    throw new PendingException();
	}

	@When("^coupon promo2 is used$")
	public void coupon_promo2_is_used(DataTable promo2) throws Throwable {
		List<List<String>> data = promo2.raw();
		//Coupon with promo2
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

    	JavascriptExecutor jse3 = (JavascriptExecutor)driver;
    	jse3.executeScript("window.scrollBy(0,450)", "");
    	sBill2 = driver.findElement(infoScreen.bill).getText();
		sTax2 = driver.findElement(infoScreen.tax).getText();
		JavascriptExecutor jse4 = (JavascriptExecutor)driver;
    	jse4.executeScript("window.scrollBy(0,450)", "");
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.total));
		sTotal2 = driver.findElement(infoScreen.total).getText();
//	    throw new PendingException();
	}
	
	@Then("^both coupon were used correctly$")
	public void both_coupon_were_used_correctly() throws Throwable {
		iBill1 = Float.parseFloat(sBill1.replaceAll("[$]", ""));
		iTax1 = Float.parseFloat(sTax1.replaceAll("[USD $]", ""));
		iTotal1 = Float.parseFloat(sTotal1.replaceAll("[USD $]", ""));
		
		discount1 = iBill1 * 60 / 100;
		total1 = discount1 + iTax1;
		
		if (total1 == iTotal1) {
			System.out.println("El descuento de 40% es correcto");
		}
		else {
			System.out.println("El descuento no es correcto");
		}
		
		iBill2 = Float.parseFloat(sBill2.replaceAll("[$]", ""));
		iTax2 = Float.parseFloat(sTax2.replaceAll("[USD $]", ""));
		iTotal2 = Float.parseFloat(sTotal2.replaceAll("[USD $]", ""));
		
		discount2 = iBill2 * 30 / 100;
		total2 = discount2 + iTax2;
		
		if (total2 == iTotal2) {
			System.out.println("El descuento de 70% es correcto");
		}
		else {
			System.out.println("El descuento no es correcto");
		}
	}

}
