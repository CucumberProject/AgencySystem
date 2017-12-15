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
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageWebElementBox.infoScreen;


public class SupplierBookingSteps {
	//This is a test comment by Ale 
	//This is a test commen by Ale made from the Branch Ale
	WebDriver driver = null;
	String hotel = "";
	String totalAmount="";
	String checkIn ="";
	String checkOut  ="";
	String bookID = "";
	String bookCode = "";
	
	@Given("^I am Supplier User and login in the Supplier login page$")
	public void i_am_Supplier_User_and_login_in_the_Supplier_login_page() throws Throwable {

		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("http://www.phptravels.net/supplier");
		driver.manage().window().maximize();
		
		//Login
		driver.findElement(infoScreen.usernameSupplier).sendKeys("supplier@phptravels.com");
		driver.findElement(infoScreen.passwordSupplier).sendKeys("demosupplier");
		driver.findElement(infoScreen.LogInButtonSupplier).click();
	}

	@When("^Supplier User want to book a hotel in the Supplier page$")
	public void supplier_User_want_to_book_a_hotel_in_the_Supplier_page() throws Throwable {
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		
		//driver.navigate().refresh();
		//Thread.sleep(500);
		
		//Click on "Quick Booking"
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.quickBooking));
		driver.findElement(infoScreen.quickBooking).click();
		
		//First window
		Select dropdwn = new Select (driver.findElement(infoScreen.taxDrpdwn));
		dropdwn.selectByValue("yes");
		dropdwn = new Select (driver.findElement(infoScreen.serviceDrpdwn));
		dropdwn.selectByValue("Hotels");
		driver.findElement(infoScreen.quickNextButton).click();
		
		//Enter Information
		
		//Use a registered user.
		dropdwn = new Select (driver.findElement(infoScreen.customerType));
		dropdwn.selectByValue("registered");
		dropdwn = new Select (driver.findElement(infoScreen.customerName));
		dropdwn.selectByVisibleText("Johny Smith - user@phptravels.com");
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,250)", "");
    	
    	//Enter Check in and Check out date
    	
    	//Check Out
    	driver.findElement(infoScreen.quickCheckOut).click();
    	driver.findElement(By.xpath("/html/body/div[4]/div[1]/table/tbody/tr[5]/td[5]")).click();
    	//Check IN
		driver.findElement(infoScreen.quickCheckIn).click();
    	driver.findElement(By.xpath("/html/body/div[3]/div[1]/table/tbody/tr[5]/td[1]")).click();
    	driver.findElement(infoScreen.quickCheckOut).click();
   	

    	//Stored check in and check out information
    	
    	checkIn = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/input")).getAttribute("value");
    	checkOut = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[2]/div/input")).getAttribute("value");
    	
    	//Select Hotel
		dropdwn = new Select (driver.findElement(infoScreen.quickHotelName));
		dropdwn.selectByVisibleText(" Rendezvous Hotels ");
		
		//Stored hotel
		hotel = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[4]/div/div/a/span[1]")).getText();
    	
		jse.executeScript("window.scrollBy(0,250)", "");
		//Thread.sleep(500);
	
		//Select Room
		wait.until(ExpectedConditions.visibilityOfElementLocated(infoScreen.quickRooms));
    	dropdwn = new Select (driver.findElement(infoScreen.quickRooms));
		dropdwn.selectByVisibleText("Superior Double");
		
		//Thread.sleep(500);
		
		//Stored Total Amount
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='grandtotal']")));
		totalAmount = driver.findElement(By.xpath("//*[@id='grandtotal']")).getText(); 
		
		//Click on Book Now
		wait.until(ExpectedConditions.visibilityOfElementLocated(infoScreen.bookNowButton));
		driver.findElement(infoScreen.bookNowButton).click();
		
		System.out.println(hotel);
		System.out.println(checkIn);
		System.out.println(checkOut);
		
		driver.navigate().refresh();
		//Thread.sleep(1000);
		for (int i=1 ; i < 10 ; i++){
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']/div/div[2]/div/div/div[1]/div[2]/table/tbody/tr["+i+"]/td[12]/span/a[2]")));
			driver.findElement(By.xpath("//*[@id='content']/div/div[2]/div/div/div[1]/div[2]/table/tbody/tr["+i+"]/td[12]/span/a[2]")).click();
			
			//Thread.sleep(500);
			if (driver.getPageSource().contains(hotel)&&driver.getPageSource().contains(checkIn)&&driver.getPageSource().contains(checkOut)){
				//System.out.println("Test Pass");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/header/nav/div[1]/a")));
				driver.findElement(By.xpath("/html/body/div[2]/header/nav/div[1]/a")).click();
				Thread.sleep(500);
				bookID = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/table/tbody/tr["+i+"]/td[3]")).getText();
				bookCode = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/table/tbody/tr["+i+"]/td[4]	")).getText();
				break;
			}
		
		}
		
		System.out.println(bookID);
		System.out.println(bookCode);
		driver.quit();
		
	}

	@Then("^Supplier should be able to book a hotel\\.$")
	public void supplier_should_be_able_to_book_a_hotel() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	
		
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,10);
		driver.navigate().to("http://www.phptravels.net/");
		driver.manage().window().maximize();
		driver.findElement(infoScreen.accountButton).click();
	    driver.findElement(infoScreen.singinButton).click();
	    
		driver.findElement(infoScreen.usernameUser).sendKeys("user@phptravels.com");
	    driver.findElement(infoScreen.passwordUser).sendKeys("demouser");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(infoScreen.LogInButtonUser));
	    driver.findElement(infoScreen.LogInButtonUser).click();
	    
	    //Thread.sleep(1000);
	    driver.navigate().refresh();
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("window.scrollBy(0,500)", "");
	    //Thread.sleep(1000);
	    
	    
	    boolean book = (driver.getPageSource().contains(bookID) && driver.getPageSource().contains(bookCode));
	    System.out.println(book);
		
		 Assert.assertTrue(book);
		 System.out.println("Test Pass, Booking successfully from Supplier Pages");
		
		driver.quit();
	}

	
}
