package Hotel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.chrome.ChromeDriver; 

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageWebElementBox.infoScreen;


public class NewHotelSteps {

	WebDriver driver = null;
	private List<List<String>> table;
	
	@Given("^I am on Admin Login Page$")
	public void i_am_on_Admin_Login_Page() throws Throwable {
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
	}

	@Then("^I enter username as \"([^\"]*)\" And I enter password as \"([^\"]*)\" to login$")
	public void i_enter_username_as_And_I_enter_password_as_to_login(String arg1, String arg2) throws Throwable {
	    
		//Login
			driver.findElement(infoScreen.usernameAdmin).sendKeys(arg1);
			driver.findElement(infoScreen.passwordAdmin).sendKeys(arg2);
			driver.findElement(infoScreen.LogInButtonAdmin).click();
	}

	@Given("^The User want to create a New Hotel$")
	public void the_User_want_to_create_a_New_Hotel() throws Throwable {
	    
		//Add New Hotel
			driver.navigate().refresh();
				
			driver.findElement(infoScreen.hotelDrpdwn).click();
			driver.findElement(infoScreen.hotelOption).click();
			driver.findElement(infoScreen.hotelAddButton).click();
		
	}

	@When("^The user will enter valid data on General tab$")
	public void the_user_will_enter_valid_data_on_General_tab(DataTable arg1) throws Throwable {
	    

		//Initialize data table
		table = arg1.raw();
		String name = table.get(1).get(1);
		String description = table.get(2).get(1);
		String stars = table.get(3).get(1);
		String type = table.get(4).get(1);
		String location = table.get(5).get(1);
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		
		Select dropdown = new Select (driver.findElement(infoScreen.hotelStatus));
		dropdown.selectByVisibleText("Enabled");
		
		//Enter name
		driver.findElement(infoScreen.hotelName).sendKeys(name);
		
		
		
		//Enter Description
		WebElement desFrame = driver.findElement(infoScreen.hotelDescription);
		driver.switchTo().frame(desFrame);
		driver.findElement(By.cssSelector("body")).sendKeys(description);
		driver.switchTo().defaultContent();
		
		
		dropdown = new Select (driver.findElement(infoScreen.hotelStars));
		dropdown.selectByVisibleText(stars);
		
		dropdown = new Select (driver.findElement(infoScreen.hotelType));
		dropdown.selectByVisibleText(type);
	
		//Enter Location
		driver.findElement(infoScreen.hotelLocation).sendKeys(location);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[9]/ul/li/div/span")));
		driver.findElement(By.xpath("/html/body/div[9]/ul/li/div/span")).click();
	   
		
	}

	@When("^Select Differents options on Facilities tab$")
	public void select_Differents_options_on_Facilities_tab() throws Throwable {
	    
		//Scroll up
		JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,-500)", "");
		
		//Select Facilities tab
		driver.findElement(infoScreen.hotelFacilitiesTab).click();
		//Select Facilities 
		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div[4]/label")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div[7]/label")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div[9]/label")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div[10]/label")).click();
	}

	@When("^enter valid data on Policy tab$")
	public void enter_valid_data_on_Policy_tab(DataTable arg1) throws Throwable {
		
		table = arg1.raw();
		String checkIn = table.get(1).get(1);
		String checkOut = table.get(2).get(1);
		String policyText = table.get(3).get(1);
		//Select Policy tab
		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/ul/li[4]/a")).click();
		//Set Check in time
		driver.findElement(infoScreen.policyCheckIn).click();
		driver.findElement(infoScreen.policyCheckIn).clear();
		driver.findElement(infoScreen.policyCheckIn).sendKeys(checkIn);
		//Set Check out time
		driver.findElement(infoScreen.policyCheckOut).click();
		driver.findElement(infoScreen.policyCheckOut).clear();
		driver.findElement(infoScreen.policyCheckOut).sendKeys(checkOut);
		//Select Payment options
		driver.findElement(infoScreen.addPayment).click();
		Select dropdown = new Select (driver.findElement(infoScreen.paymentOption));
		dropdown.selectByVisibleText("Credit Card");
		driver.findElement(infoScreen.addPayment).click();
		dropdown = new Select (driver.findElement(infoScreen.paymentOption));
		dropdown.selectByVisibleText("Master/ Visa Card");
		//Enter Policy Text
		driver.findElement(infoScreen.policyText).sendKeys(policyText);
	}

	@When("^Enter valida information in Contact tab$")
	public void enter_valida_information_in_Contact_tab(DataTable arg1) throws Throwable {
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		//Select Contact Tab
		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/ul/li[5]/a")).click();
		
		table = arg1.raw();
		String email = table.get(1).get(1);
		String web = table.get(2).get(1);
		String number = table.get(3).get(1);
		
		//Enter Hotel Email
		driver.findElement(infoScreen.hotelEmail).click();
		driver.findElement(infoScreen.hotelEmail).sendKeys(email);
		//Enter Hotel Website
		driver.findElement(infoScreen.hotelWebPage).click();
		driver.findElement(infoScreen.hotelWebPage).sendKeys(web);
		//Enter Hotel Phone
		driver.findElement(infoScreen.hotelNumber).click();
		driver.findElement(infoScreen.hotelNumber).sendKeys(number);
		//Submit
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.CreateButtonHotel));
		driver.findElement(infoScreen.CreateButtonHotel).click();
	}

	@Then("^The New Hotel should be created$")
	public void the_New_Hotel_should_be_created(DataTable arg1) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver,5);
		
		
		//Thread.sleep(1000);
		
		table = arg1.raw();
		
		//Verify if the hotel is created and displayed in the menu
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(table.get(1).get(1))));
		String comp = driver.findElement(By.partialLinkText(table.get(1).get(1))).getText();
		
		
		Assert.assertEquals(comp,table.get(1).get(1));
		System.out.println("Test Pass, new hotel was created");
		
		driver.quit();
	}


}
