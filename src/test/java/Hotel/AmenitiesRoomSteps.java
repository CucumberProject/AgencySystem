package Hotel;
import java.util.List;
import java.util.concurrent.TimeUnit;

import pageWebElementBox.infoScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver; 

import org.testng.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class AmenitiesRoomSteps {

	WebDriver driver = null;
	WebDriver driver2 = null;
	private List<List<String>> table;
	
	@Given("^I am Admin user$")
	public void i_am_Admin_user() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
		
		//Login
		driver.findElement(infoScreen.usernameAdmin).sendKeys("admin@phptravels.com");
		driver.findElement(infoScreen.passwordAdmin).sendKeys("demoadmin");
		driver.findElement(infoScreen.LogInButtonAdmin).click();
		
	}

	@When("^I Create a New Hotel$")
	public void i_Create_a_New_Hotel(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		//Add New Hotel
		driver.navigate().refresh();
			
		driver.findElement(infoScreen.hotelDrpdwn).click();
		driver.findElement(infoScreen.hotelOption).click();
		driver.findElement(infoScreen.hotelAddButton).click();
		
		//Initialize data table
		table = arg1.raw();
		String status = table.get(1).get(1);
		String name = table.get(2).get(1);
		String description = table.get(3).get(1);
		String stars = table.get(4).get(1);
		String type = table.get(5).get(1);
		String location = table.get(6).get(1);
				
		WebDriverWait wait = new WebDriverWait(driver,5);
		
		Select dropdown = new Select (driver.findElement(infoScreen.hotelStatus));
		dropdown.selectByVisibleText(status);
		
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
		
		//Create
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.CreateButtonHotel));
		driver.findElement(infoScreen.CreateButtonHotel).click();
	}

	@When("^Create a New room for the hotel$")
	public void create_a_New_room_for_the_hotel(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new PendingException();
		driver.navigate().refresh();
		driver.findElement(infoScreen.hotelDrpdwn).click();
		driver.findElement(infoScreen.roomOption).click();
		driver.findElement(infoScreen.hotelAddButton).click();
		
		//Prepare values from table
		table = arg1.raw();
		String status = table.get(1).get(1);
		String type = table.get(2).get(1);
		String hotel = table.get(3).get(1);
		String description = table.get(4).get(1);
		String price = table.get(5).get(1);
		String quantity = table.get(6).get(1);
		String stay = table.get(7).get(1);
		String adults = table.get(8).get(1);
		String children = table.get(9).get(1);
		String extraBed = table.get(10).get(1);
		String priceBed = table.get(11).get(1);
		
		
		//Select Room Status
		Select dropdown = new Select (driver.findElement(infoScreen.roomStatus));
		dropdown.selectByVisibleText(status);
		
		//Select Room Type
		dropdown = new Select (driver.findElement(infoScreen.roomType));
		dropdown.selectByVisibleText(type);
		//Presidential Suite
		
		//Select hotel
		dropdown = new Select (driver.findElement(infoScreen.roomHotel));
		dropdown.selectByVisibleText(hotel);
		// Rendezvous Hotels 
		
		//Enter Description
		WebElement desFrame = driver.findElement(infoScreen.roomDescription);
		driver.switchTo().frame(desFrame);
		driver.findElement(By.cssSelector("body")).sendKeys(description);
		driver.switchTo().defaultContent();
		
		//Enter Room Price
		driver.findElement(infoScreen.roomPrice).sendKeys(price);
		//Enter Room Quantity
		driver.findElement(infoScreen.roomQuantity).sendKeys(quantity);
		//Enter Room Minimum Stay
		driver.findElement(infoScreen.roomMinStay).sendKeys(stay);
		//Enter Room Max Adults
		driver.findElement(infoScreen.roomMaxAdults).sendKeys(adults);
		//Enter Room Max Children
		driver.findElement(infoScreen.roomMaxChildren).sendKeys(children);
		//Enter Room Max Extra Beds
		driver.findElement(infoScreen.roomExtraBeds).sendKeys(extraBed);
		//Enter Extra Bed Charges
		driver.findElement(infoScreen.roomBedCharges).sendKeys(priceBed);
		
	}
	
	@When("^The new room will have the following amenities$")
	public void the_new_room_will_have_the_following_amenities(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		//Scroll up
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-500)","" );
		
		//Click on amenities Tab
		driver.findElement(infoScreen.roomAmenitiesTab).click();
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		table = arg1.raw();
		int amenities = table.size();
		int count = 1;
		for (int i=4 ; i<40 ; i++){
			
			String proof = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div["+i+"]/label")).getText();
			if (proof.equals(table.get(count).get(1))){
				driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div["+i+"]/label")).click();
				count++;
				if(count == amenities){
					break;
				}
			}else{
				continue;
			}
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.roomCreateButton));
		driver.findElement(infoScreen.roomCreateButton).click();
		driver.quit();
	}

	@Then("^the user should be able to see all the amenities$")
	public void the_user_should_be_able_to_see_all_the_amenities(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		table = arg1.raw();
		
		driver2 = new ChromeDriver();
		driver2.navigate().to("http://www.phptravels.net/");
		WebDriverWait wait = new WebDriverWait(driver2,5);
		driver2.manage().window().maximize();
		driver2.findElement(infoScreen.accountButton).click();
	    driver2.findElement(infoScreen.singinButton).click();
	    
		driver2.findElement(infoScreen.usernameUser).sendKeys("user@phptravels.com");
	    driver2.findElement(infoScreen.passwordUser).sendKeys("demouser");
	    driver2.findElement(infoScreen.LogInButtonUser).click();
	    
	  //Click on Hotels
	  	driver2.navigate().refresh();
	  	wait.until(ExpectedConditions.elementToBeClickable(infoScreen.hotelsPage));
	  	driver2.findElement(infoScreen.hotelsPage).click();
	  	
	  	//Click on specific hotel 
	  	wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(table.get(1).get(1))));
		driver2.findElement(By.partialLinkText(table.get(1).get(1))).click();
		JavascriptExecutor jse = (JavascriptExecutor)driver2;
    	jse.executeScript("window.scrollBy(0,600)", "");
	  	
    	//Click on More details
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[5]/section/div/table/tbody/tr/td/div[2]/div/div[5]/div[1]")));
    	driver2.findElement(By.xpath("/html/body/div[5]/section/div/table/tbody/tr/td/div[2]/div/div[5]/div[1]")).click();
    	
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("details53")));
    	
    	////*[@id="details53"]
    	boolean amenities = (driver2.getPageSource().contains(table.get(2).get(1))&&driver2.getPageSource().contains(table.get(3).get(1))&&driver2.getPageSource().contains(table.get(4).get(1)));
	
    	Assert.assertTrue(amenities);
 	    System.out.println("Test Pass all amenities displayed");
 	    driver2.quit();
	}

}
