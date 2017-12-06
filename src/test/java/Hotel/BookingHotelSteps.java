package Hotel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver; 

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
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/nav[1]/div/div/div/ul/li[2]/a")).click();
	}

	@When("^The hotel is enable$")
	public void the_hotel_is_enable(DataTable arg1) throws Throwable {
	    
		board = arg1.raw();
		//System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver2 = new ChromeDriver();
		driver2.navigate().to(" http://www.phptravels.net/admin");
		driver2.manage().window().maximize();
		driver2.findElement(By.name("email")).sendKeys("admin@phptravels.com");
		driver2.findElement(By.name("password")).sendKeys("demoadmin");
		driver2.findElement(By.className("btn-primary")).click();
		
		driver2.navigate().refresh();
		Thread.sleep(500);
		String xpath = "//*[@id='social-sidebar-menu']/li[8]/a/span";
		driver2.findElement(By.xpath(xpath)).click();
		xpath = "//*[@id='Hotels']/li[1]/a";
		driver2.findElement(By.xpath(xpath)).click();
		
		for (int i=1 ; i < board.size() ; i++){
			driver2.findElement(By.partialLinkText(board.get(i).get(1))).click();
			Select dropdown = new Select (driver2.findElement(By.name("hotelstatus")));
			dropdown.selectByVisibleText("Enabled");
			driver2.findElement(By.id("update")).click();
			Thread.sleep(750);
		}
		
		driver2.quit();
		
		
		//throw new PendingException();

	}

	@Then("^The user should be able to book$")
	public void the_user_should_be_able_to_book(DataTable arg1) throws Throwable {
	    
		driver.navigate().refresh();
		Thread.sleep(500);
		board = arg1.raw();
		//driver.navigate().refresh();
		String[] BookingId = new String[board.size()-1];
		//String[] BookingCode = new String[board.size()-1];
		for (int i=1 ; i<board.size() ; i++) {
			Thread.sleep(1500);
			driver.findElement(By.partialLinkText(board.get(i).get(1))).click();
			JavascriptExecutor jse = (JavascriptExecutor)driver;
	    	jse.executeScript("window.scrollBy(0,750)", "");
			Thread.sleep(500);
	    	driver.findElement(By.xpath("//*[@id='ROOMS']/div/table/tbody/tr[1]/td/div[2]/div/div[5]/div[3]")).click();
			driver.findElement(By.name("logged")).click();
			Thread.sleep(6000);
			BookingId[i-1] = driver.findElement(By.xpath("//*[@id='body-section']/div[1]/div/div[7]/div[1]/div[3]/span")).getText();	
			//WebElement pru = driver.findElement(By.xpath("//*[@id='body-section']/div[1]/div/div[7]/div[1]/div[3]/text()[2]"));
			//String cleanStr = pru.getText();
			//String cleanStr = driver.findElement(By.xpath("//*[@id='body-section']/div[1]/div/div[7]/div[1]/div[3]/text()[2]")).getText();
			//BookingCode[i-1] = cleanStr.replaceAll("\\s","");
			driver.findElement(By.xpath("/html/body/nav[1]/div/div/div/ul/li[2]/a")).click();
			
		}
		
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/ul/ul/li[1]")).click();
	    driver.findElement(By.linkText("Account")).click();
	    Thread.sleep(1000);
	
	    for(int i=0 ; i<BookingId.length; i++){
	    	 if (driver.getPageSource().contains(BookingId[i])){
	 	    	System.out.println("Test Pass for "+board.get(i+1).get(1)+" Booking");
	 	    }else{
	 	    	System.out.println("Test Fail for "+board.get(i+1).get(1)+" Booking");
	 	    }
	    }
	    
	    driver.quit();
		
		//throw new PendingException();
	}

	@When("^The hotel is disable$")
	public void the_hotel_is_disable(DataTable arg1) throws Throwable {
	   
	    //throw new PendingException();
		
		board = arg1.raw();
		//System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver2 = new ChromeDriver();
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
			driver2.findElement(By.partialLinkText(board.get(i).get(1))).click();
			Select dropdown = new Select (driver2.findElement(By.name("hotelstatus")));
			dropdown.selectByVisibleText("Disabled");
			driver2.findElement(By.id("update")).click();
			Thread.sleep(750);
		}
		
		driver2.quit();
		
		
		//throw new PendingException();
		
	}

	@Then("^The user shouldnt be able to book$")
	public void the_user_shouldnt_be_able_to_book(DataTable arg1) throws Throwable {
	    
		board = arg1.raw();
		driver.navigate().refresh();
	    for(int i=1 ; i<board.size(); i++){
	    	 if (driver.getPageSource().contains(board.get(i).get(1))){
	    		System.out.println("Test Fail");
		 	    System.out.println("User shouldnt be able to booking on "+board.get(i).get(1));
	 	    }else{
	 	    	System.out.println("Test Pass");
	 	    	System.out.println("User shouldnt be able to booking on "+board.get(i).get(1));
	 	    }
	    }
	    
	    driver.quit();
		
		//throw new PendingException()
		
	}

	
	
}
