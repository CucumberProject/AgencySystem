package Hotel;

import java.util.List;
import pageWebElementBox.infoScreen;
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


public class CheckInSteps {

	WebDriver driver = null;
	WebDriver driver2 = null;
	String CheckIn = "";
	String CheckOut = "";
	String NewCI = "";
	String NewCO = "";
	private List<List<String>> table; 
	
	@Given("^I am Admin and Create a new hotel$")
	public void i_am_Admin_and_Create_a_new_hotel(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		table = arg1.raw();
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
		
		//Login
		driver.findElement(infoScreen.usernameAdmin).sendKeys("admin@phptravels.com");
		driver.findElement(infoScreen.passwordAdmin).sendKeys("demoadmin");
		driver.findElement(infoScreen.LogInButtonAdmin).click();
		
		//Add New Hotel
		driver.navigate().refresh();
			
		driver.findElement(infoScreen.hotelDrpdwn).click();
		driver.findElement(infoScreen.hotelOption).click();
		driver.findElement(infoScreen.hotelAddButton).click();
		
		//Enter Hotel information
		
		driver.navigate().refresh();
			
		Select dropdown = new Select (driver.findElement(infoScreen.hotelStatus));
		dropdown.selectByVisibleText("Enabled");
		
		//Enter Name	
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.hotelName));
		driver.findElement(infoScreen.hotelName).click();
		driver.findElement(infoScreen.hotelName).sendKeys(table.get(1).get(1));
		
		//Enter Description
		WebElement desFrame = driver.findElement(infoScreen.hotelDescription);
		driver.switchTo().frame(desFrame);
		driver.findElement(By.cssSelector("body")).sendKeys(table.get(2).get(1));
		driver.switchTo().defaultContent();
		
		
		dropdown = new Select (driver.findElement(infoScreen.hotelStars));
		dropdown.selectByVisibleText(table.get(4).get(1));
				
		dropdown = new Select (driver.findElement(infoScreen.hotelType));
		dropdown.selectByVisibleText(table.get(5).get(1));
		
		//Enter Location
		driver.findElement(infoScreen.hotelLocation).sendKeys(table.get(3).get(1));
		//Select a prediction 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[9]/ul/li/div/span")));
		driver.findElement(By.xpath("/html/body/div[9]/ul/li/div/span")).click();
		
		//Scroll up
		JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,-500)", "");
		
		//Select Policy tab
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/form/div/ul/li[4]/a")));
		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/ul/li[4]/a")).click();
		//Set Check in time
		CheckIn = table.get(6).get(1);
		driver.findElement(infoScreen.policyCheckIn).click();
		driver.findElement(infoScreen.policyCheckIn).clear();
		driver.findElement(infoScreen.policyCheckIn).sendKeys(CheckIn);
		//Set Check out time
		CheckOut = table.get(7).get(1);
		driver.findElement(infoScreen.policyCheckOut).click();
		driver.findElement(infoScreen.policyCheckOut).clear();
		driver.findElement(infoScreen.policyCheckOut).sendKeys(CheckOut);
		//Select Payment options
		driver.findElement(infoScreen.addPayment).click();
		dropdown = new Select (driver.findElement(infoScreen.paymentOption));
		dropdown.selectByVisibleText("Credit Card");
		driver.findElement(infoScreen.addPayment).click();
		dropdown = new Select (driver.findElement(infoScreen.paymentOption));
		dropdown.selectByVisibleText("Master/ Visa Card");
		//Enter Policy Text
		driver.findElement(infoScreen.policyText).sendKeys(table.get(8).get(1));
		
		driver.findElement(infoScreen.CreateButtonHotel).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div[1]")));
		driver.quit();
	}

	@When("^The user enter to the New hotel information$")
	public void the_user_enter_to_the_New_hotel_information(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver2 = new ChromeDriver();
		table = arg1.raw();
		WebDriverWait wait = new WebDriverWait(driver2,5);
		
		//Enter to the main page
		driver2.navigate().to("http://www.phptravels.net/");
		driver2.manage().window().maximize();
		driver2.findElement(infoScreen.accountButton).click();
	    driver2.findElement(infoScreen.singinButton).click();
	    
	    //Login
		driver2.findElement(infoScreen.usernameUser).sendKeys("user@phptravels.com");
	    driver2.findElement(infoScreen.passwordUser).sendKeys("demouser");
	    driver2.findElement(infoScreen.LogInButtonUser).click();
	    
	    
	    //Enter to hotels and select one
	    driver2.navigate().refresh();
	    wait.until(ExpectedConditions.elementToBeClickable(infoScreen.hotelsPage));
	    driver2.findElement(infoScreen.hotelsPage).click();
	    WebElement element = driver2.findElement(By.partialLinkText(table.get(1).get(1)));
	    JavascriptExecutor js = (JavascriptExecutor) driver2;
	    js.executeScript("arguments[0].scrollIntoView(true);", element); 
	    wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(table.get(1).get(1))));
	    driver2.findElement(By.partialLinkText(table.get(1).get(1))).click();
	  
	}

	@Then("^User should be able to see Check In Information$")
	public void user_should_be_able_to_see_Check_In_Information() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		WebDriverWait wait = new WebDriverWait(driver2,5);
		
		//the xpath is where check in information is located when a user enter so check hotel information
	    WebElement element = driver2.findElement(By.xpath("/html/body/div[4]/div[5]/div[3]/div/div/div[2]/div/div[1]/div[9]/div[2]"));
	    JavascriptExecutor js = (JavascriptExecutor) driver2;
	    js.executeScript("arguments[0].scrollIntoView(true);", element); 
	    wait.until(ExpectedConditions.visibilityOf(element));
	  //  Thread.sleep(1000);
	    
	    Assert.assertTrue((driver2.getPageSource().contains(CheckIn) && driver2.getPageSource().contains(CheckOut)));
	    System.out.println("Test Pass,Check In information is available");
	    
	    driver2.quit();
	}

	@Given("^I made a modification in the Check in Information$")
	public void i_made_a_modification_in_the_Check_in_Information(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,5);
		table = arg1.raw();
		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
		
		//Login
		driver.findElement(infoScreen.usernameAdmin).sendKeys("admin@phptravels.com");
		driver.findElement(infoScreen.passwordAdmin).sendKeys("demoadmin");
		driver.findElement(infoScreen.LogInButtonAdmin).click();
		
		
		//Change Check In information

		driver.navigate().refresh();
		
		driver.findElement(infoScreen.hotelDrpdwn).click();
		driver.findElement(infoScreen.hotelOption).click();
		driver.findElement(By.partialLinkText(table.get(1).get(1))).click();
		
		//Select Policy tab
		driver.findElement(infoScreen.hotelPolicyTab).click();
		//Set Check in time
		NewCI = table.get(2).get(1);
		driver.findElement(infoScreen.policyCheckIn).click();
		driver.findElement(infoScreen.policyCheckIn).clear();
		driver.findElement(infoScreen.policyCheckIn).sendKeys(NewCI);
		//Set Check out time
		NewCO = table.get(3).get(1);
		driver.findElement(infoScreen.policyCheckOut).click();
		driver.findElement(infoScreen.policyCheckOut).clear();
		driver.findElement(infoScreen.policyCheckOut).sendKeys(NewCO);
		
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.UpdateButtonHotel));
		driver.findElement(infoScreen.UpdateButtonHotel).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div[1]")));
		driver.quit();
		
	}

	@When("^The User Log in and Enter to the hotel information$")
	public void the_User_Log_in_and_Enter_to_the_hotel_information(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver2 = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver2,5);
		table = arg1.raw();
		
		//Enter to the main page
		driver2.navigate().to("http://www.phptravels.net/");
		driver2.manage().window().maximize();
		driver2.findElement(infoScreen.accountButton).click();
		driver2.findElement(infoScreen.singinButton).click();
			    
		//Login
		driver2.findElement(infoScreen.usernameUser).sendKeys("user@phptravels.com");
		driver2.findElement(infoScreen.passwordUser).sendKeys("demouser");
		wait.until(ExpectedConditions.visibilityOfElementLocated(infoScreen.LogInButtonUser));
		driver2.findElement(infoScreen.LogInButtonUser).click();
		//Thread.sleep(500);
			    
		//Enter to hotels and select one
		driver2.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.hotelsPage));
		driver2.findElement(infoScreen.hotelsPage).click();
		WebElement element = driver2.findElement(By.partialLinkText(table.get(1).get(1)));
	    JavascriptExecutor js = (JavascriptExecutor) driver2;
	    js.executeScript("arguments[0].scrollIntoView(true);", element); 
		wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(table.get(1).get(1))));
		driver2.findElement(By.partialLinkText(table.get(1).get(1))).click();
		
	}
	
	@Then("^User should be able to see new Check In Information$")
	public void user_should_be_able_to_see_new_Check_In_Information() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		WebDriverWait wait = new WebDriverWait(driver2,5);
		
		//Scroll down until find Check In information
		//the xpath is where check in information is located when a user enter so check hotel information
	    WebElement element = driver2.findElement(By.xpath("/html/body/div[4]/div[5]/div[3]/div/div/div[2]/div/div[1]/div[9]/div[2]"));
	    JavascriptExecutor js = (JavascriptExecutor) driver2;
	    wait.until(ExpectedConditions.visibilityOf(element));
	    js.executeScript("arguments[0].scrollIntoView(true);", element); 
	    //wait.until(ExpectedConditions.visibilityOf(element));
	    
	    Assert.assertTrue((driver2.getPageSource().contains(NewCI) && driver2.getPageSource().contains(NewCO)));
	    System.out.println("Test Pass, Updated Check In information is available");
	    
	  
	    driver2.quit();
	}
	
}

