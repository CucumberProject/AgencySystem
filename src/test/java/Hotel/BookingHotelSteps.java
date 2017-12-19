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
import org.openqa.selenium.chrome.ChromeDriver; 

import org.testng.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BookingHotelSteps {

	WebDriver driver = null;
	WebDriver driver2 = null;
	private List<List<String>> board;
	
	@Given("^The user log in the Travel Agency Page$")
	public void the_user_log_in_the_Travel_Agency_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("http://www.phptravels.net/");
		driver.manage().window().maximize();
		driver.findElement(infoScreen.accountButton).click();
	    driver.findElement(infoScreen.singinButton).click();
	    
		driver.findElement(infoScreen.usernameUser).sendKeys("user@phptravels.com");
	    driver.findElement(infoScreen.passwordUser).sendKeys("demouser");
	    driver.findElement(infoScreen.LogInButtonUser).click();
	    
	}

	@When("^The user want to book in a hotel$")
	public void the_user_want_to_book_in_a_hotel() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		//Click on Hotels
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.hotelsPage));
		driver.findElement(infoScreen.hotelsPage).click();
	}

	@When("^The hotel is enable$")
	public void the_hotel_is_enable(DataTable arg1) throws Throwable {
	    
		board = arg1.raw();
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver2 = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver2,10);
		driver2.navigate().to(" http://www.phptravels.net/admin");
		driver2.manage().window().maximize();
		driver2.findElement(infoScreen.usernameAdmin).sendKeys("admin@phptravels.com");
		driver2.findElement(infoScreen.passwordAdmin).sendKeys("demoadmin");
		driver2.findElement(infoScreen.LogInButtonAdmin).click();
		
	
		
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.hotelDrpdwn));
		driver2.findElement(infoScreen.hotelDrpdwn).click();
		driver2.findElement(infoScreen.hotelOption).click();
		
		for (int i=1 ; i < board.size() ; i++){
			driver.navigate().refresh();
			wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(board.get(i).get(1))));
			driver2.findElement(By.partialLinkText(board.get(i).get(1))).click();
			Select dropdown = new Select (driver2.findElement(infoScreen.hotelStatus));
			dropdown.selectByVisibleText("Enabled");
			wait.until(ExpectedConditions.elementToBeClickable(infoScreen.UpdateButtonHotel));
			driver2.findElement(infoScreen.UpdateButtonHotel).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div[1]")));
		driver2.quit();

		}

	@Then("^The user should be able to book$")
	public void the_user_should_be_able_to_book(DataTable arg1) throws Throwable {
		

		driver.navigate().refresh();
		board = arg1.raw();
		WebDriverWait wait = new WebDriverWait(driver,10);
		String[] BookingId = new String[board.size()-1];
		
		for (int i=1 ; i<board.size() ; i++) {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(board.get(i).get(1))));
			WebElement element = driver.findElement(By.partialLinkText(board.get(i).get(1)));
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].scrollIntoView(true);", element); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(board.get(i).get(1))));
			driver.findElement(By.partialLinkText(board.get(i).get(1))).click();
	    	
	    	//the xpath will change depending the hotel room selection
			element = driver.findElement(By.xpath("//*[@id='ROOMS']/div/table/tbody/tr[1]/td/div[2]/div/div[5]/div[3]"));
			js.executeScript("arguments[0].scrollIntoView(true);", element); 
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ROOMS']/div/table/tbody/tr[1]/td/div[2]/div/div[5]/div[3]")));
			driver.findElement(By.xpath("//*[@id='ROOMS']/div/table/tbody/tr[1]/td/div[2]/div/div[5]/div[3]")).click();
			driver.findElement(infoScreen.bookHotel).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='body-section']/div[1]/div/div[7]/div[1]/div[3]/span")));
			BookingId[i-1] = driver.findElement(By.xpath("//*[@id='body-section']/div[1]/div/div[7]/div[1]/div[3]/span")).getText();	
			wait.until(ExpectedConditions.elementToBeClickable(infoScreen.hotelsPage));
			driver.findElement(infoScreen.hotelsPage).click();
			
		}
		
		driver.findElement(infoScreen.accountButton).click();
	    driver.findElement(infoScreen.accountPage).click();
	    //Thread.sleep(1000);
	    
	    for(int i=0 ; i<BookingId.length; i++){
	    	 
	    	Assert.assertTrue(driver.getPageSource().contains(BookingId[i]));
	 	    System.out.println("Test Pass for "+board.get(i+1).get(1)+" Booking");
	 	
	    } 
	    
	    driver.quit();
		
	}

	@When("^The hotel is disable$")
	public void the_hotel_is_disable(DataTable arg1) throws Throwable {
	   
	    //throw new PendingException();
		
		board = arg1.raw();
		driver2 = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver2,10);
		driver2.navigate().to(" http://www.phptravels.net/admin");
		driver2.manage().window().maximize();
		driver2.findElement(infoScreen.usernameAdmin).sendKeys("admin@phptravels.com");
		driver2.findElement(infoScreen.passwordAdmin).sendKeys("demoadmin");
		driver2.findElement(infoScreen.LogInButtonAdmin).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.hotelDrpdwn));
		driver2.findElement(infoScreen.hotelDrpdwn).click();
		driver2.findElement(infoScreen.hotelOption).click();
		
		for (int i=1 ; i < board.size() ; i++){
			driver2.navigate().refresh();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div/div[1]")));
			wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(board.get(i).get(1))));
			driver2.findElement(By.partialLinkText(board.get(i).get(1))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(infoScreen.hotelStatus));
			Select dropdown = new Select (driver2.findElement(infoScreen.hotelStatus));
			dropdown.selectByVisibleText("Disabled");
			wait.until(ExpectedConditions.elementToBeClickable(infoScreen.UpdateButtonHotel));
			driver2.findElement(infoScreen.UpdateButtonHotel).click();
		
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div[1]")));
		driver2.quit();
		
	}

	@Then("^The user shouldnt be able to book$")
	public void the_user_shouldnt_be_able_to_book(DataTable arg1) throws Throwable {
	    
		board = arg1.raw();
		driver.navigate().refresh();
	    
	    for(int i=1 ; i<board.size(); i++){
	    	
	 	    Assert.assertFalse(driver.getPageSource().contains(board.get(i).get(1)), "Hotel is available");
	 	    System.out.println("Test Pass, User shouldnt be able to booking on "+board.get(i).get(1));
	 	    
	    }
	    
	    
	    driver.quit();
				
	}

	
	
}
