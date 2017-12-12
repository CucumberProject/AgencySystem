package Hotel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/ul/ul/li[1]")).click();
	    driver.findElement(By.linkText("Login")).click();
	    
		driver.findElement(By.name("username")).sendKeys("user@phptravels.com");
	    driver.findElement(By.name("password")).sendKeys("demouser");
	    driver.findElement(By.xpath("//*[@id='loginfrm']/div[1]/div[5]/button")).click();
	    
	}

	@When("^The user want to book in a hotel$")
	public void the_user_want_to_book_in_a_hotel() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		//Click on Hotels
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/nav[1]/div/div/div/ul/li[2]/a")));
		driver.findElement(By.xpath("/html/body/nav[1]/div/div/div/ul/li[2]/a")).click();
	}

	@When("^The hotel is enable$")
	public void the_hotel_is_enable(DataTable arg1) throws Throwable {
	    
		board = arg1.raw();
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver2 = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver2,5);
		driver2.navigate().to(" http://www.phptravels.net/admin");
		driver2.manage().window().maximize();
		driver2.findElement(By.name("email")).sendKeys("admin@phptravels.com");
		driver2.findElement(By.name("password")).sendKeys("demoadmin");
		driver2.findElement(By.className("btn-primary")).click();
		
		String xpath = "//*[@id='social-sidebar-menu']/li[8]/a/span";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		driver2.findElement(By.xpath(xpath)).click();
		
		xpath = "//*[@id='Hotels']/li[1]/a";
		driver2.findElement(By.xpath(xpath)).click();
		
		for (int i=1 ; i < board.size() ; i++){
			wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(board.get(i).get(1))));
			driver2.findElement(By.partialLinkText(board.get(i).get(1))).click();
			Select dropdown = new Select (driver2.findElement(By.name("hotelstatus")));
			dropdown.selectByVisibleText("Enabled");
			driver2.findElement(By.id("update")).click();
		}
		
		driver2.quit();

	}

	@Then("^The user should be able to book$")
	public void the_user_should_be_able_to_book(DataTable arg1) throws Throwable {
		

		driver.navigate().refresh();
		board = arg1.raw();
		WebDriverWait wait = new WebDriverWait(driver,5);
		String[] BookingId = new String[board.size()-1];
		
		for (int i=1 ; i<board.size() ; i++) {
			wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(board.get(i).get(1))));
			driver.findElement(By.partialLinkText(board.get(i).get(1))).click();
			JavascriptExecutor jse = (JavascriptExecutor)driver;
	    	jse.executeScript("window.scrollBy(0,750)", "");
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ROOMS']/div/table/tbody/tr[1]/td/div[2]/div/div[5]/div[3]")));
			driver.findElement(By.xpath("//*[@id='ROOMS']/div/table/tbody/tr[1]/td/div[2]/div/div[5]/div[3]")).click();
			driver.findElement(By.name("logged")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='body-section']/div[1]/div/div[7]/div[1]/div[3]/span")));
			BookingId[i-1] = driver.findElement(By.xpath("//*[@id='body-section']/div[1]/div/div[7]/div[1]/div[3]/span")).getText();	
			driver.findElement(By.xpath("/html/body/nav[1]/div/div/div/ul/li[2]/a")).click();
			
		}
		
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/ul/ul/li[1]")).click();
	    driver.findElement(By.linkText("Account")).click();
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
		//System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver2 = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver2,5);
		driver2.navigate().to(" http://www.phptravels.net/admin");
		driver2.manage().window().maximize();
		driver2.findElement(By.name("email")).sendKeys("admin@phptravels.com");
		driver2.findElement(By.name("password")).sendKeys("demoadmin");
		driver2.findElement(By.className("btn-primary")).click();
		
		driver2.navigate().refresh();
		String xpath = "//*[@id='social-sidebar-menu']/li[8]/a/span";
		driver2.findElement(By.xpath(xpath)).click();
		xpath = "//*[@id='Hotels']/li[1]/a";
		driver2.findElement(By.xpath(xpath)).click();
		
		for (int i=1 ; i < board.size() ; i++){
			wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(board.get(i).get(1))));
			driver2.findElement(By.partialLinkText(board.get(i).get(1))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("hotelstatus")));
			Select dropdown = new Select (driver2.findElement(By.name("hotelstatus")));
			dropdown.selectByVisibleText("Disabled");
			driver2.findElement(By.id("update")).click();
		}
		
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
