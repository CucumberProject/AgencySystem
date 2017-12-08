package Hotel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver; 

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class CheckInSteps {

	WebDriver driver = null;
	WebDriver driver2 = null;
	String CheckIn = "10:00 AM";
	String CheckOut = "05:00 PM";
	String NewCI = "08:00 AM";
	String NewCO = "08:00 PM";
	  
	@Given("^I am Admin and Create a new hotel$")
	public void i_am_Admin_and_Create_a_new_hotel() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
		
		//Login
		driver.findElement(By.name("email")).sendKeys("admin@phptravels.com");
		driver.findElement(By.name("password")).sendKeys("demoadmin");
		driver.findElement(By.className("btn-primary")).click();
		
		//Add New Hotel
		driver.navigate().refresh();
		String xpath = "//*[@id='social-sidebar-menu']/li[8]/a/span";
			
		driver.findElement(By.xpath(xpath)).click();
		
		xpath = "//*[@id='Hotels']/li[1]/a";
		driver.findElement(By.xpath(xpath)).click();
			
		xpath = "//*[@id='content']/div/form/button";
		driver.findElement(By.xpath(xpath)).click();
		
		//Initialize data table
			
		Select dropdown = new Select (driver.findElement(By.name("hotelstatus")));
		dropdown.selectByVisibleText("Enabled");
				
		//Enter Description
		driver.switchTo().frame(0);
		driver.findElement(By.cssSelector("body")).sendKeys("Cozumel Resort is located in the principal pier of cozumel");
		driver.switchTo().defaultContent();
				
		//
		driver.findElement(By.xpath("//*[@id='s2id_autogen1']")).sendKeys("Cozumel");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li")).click();
				
		dropdown = new Select (driver.findElement(By.name("hotelstars")));
		dropdown.selectByVisibleText("4");
				
		dropdown = new Select (driver.findElement(By.name("hoteltype")));
		dropdown.selectByVisibleText("Hotel");
			
		driver.findElement(By.name("hotelname")).sendKeys("Cozumel Resort");
		
		//Select Policy tab
		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/ul/li[4]/a")).click();
		//Set Check in time
		driver.findElement(By.name("checkintime")).click();
		driver.findElement(By.name("checkintime")).clear();
		driver.findElement(By.name("checkintime")).sendKeys(CheckIn);
		//Set Check out time
		driver.findElement(By.name("checkouttime")).click();
		driver.findElement(By.name("checkouttime")).clear();
		driver.findElement(By.name("checkouttime")).sendKeys(CheckOut);
		//Select Payment options
		driver.findElement(By.id("s2id_autogen4")).click();
		dropdown = new Select (driver.findElement(By.name("hotelpayments[]")));
		dropdown.selectByVisibleText("Credit Card");
		driver.findElement(By.id("s2id_autogen4")).click();
		dropdown = new Select (driver.findElement(By.name("hotelpayments[]")));
		dropdown.selectByVisibleText("Master/ Visa Card");
		//Enter Policy Text
		driver.findElement(By.name("hotelpolicy")).sendKeys("You cant bring pets");
		
		driver.findElement(By.id("add")).click();
		driver.quit();
	}

	@When("^The user enter to the New hotel information$")
	public void the_user_enter_to_the_New_hotel_information() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver2 = new ChromeDriver();
		
		//Enter to the main page
		driver2.navigate().to("http://www.phptravels.net/");
		driver2.manage().window().maximize();
		driver2.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/ul/ul/li[1]")).click();
	    driver2.findElement(By.linkText("Login")).click();
	    
	    //Login
		driver2.findElement(By.name("username")).sendKeys("user@phptravels.com");
	    driver2.findElement(By.name("password")).sendKeys("demouser");
	    driver2.findElement(By.xpath("//*[@id='loginfrm']/div[1]/div[5]/button")).click();
	    Thread.sleep(500);
	    
	    //Enter to hotels and select one
	    driver2.findElement(By.xpath("/html/body/nav[1]/div/div/div/ul/li[2]/a")).click();
	    Thread.sleep(500);
	    driver2.findElement(By.partialLinkText("Cozumel Resort")).click();
	  
	    //Thread.sleep(2000);
	}

	@Then("^User should be able to see Check In Information$")
	public void user_should_be_able_to_see_Check_In_Information() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		//Scroll down until find Check In information
	    Thread.sleep(500);
	    WebElement element = driver2.findElement(By.xpath("/html/body/div[4]/div[5]/div[3]/div/div/div[2]/div/div[1]/div[9]/div[2]"));
	    JavascriptExecutor js = (JavascriptExecutor) driver2;
	    js.executeScript("arguments[0].scrollIntoView(true);", element); 
	    Thread.sleep(1000);
	    
	    //Verify is the check in information matches
	    if ( driver2.getPageSource().contains(CheckIn) && driver2.getPageSource().contains(CheckOut)){
	    	System.out.println("Test Pass");
	    	System.out.println("Check In information is available");
	    }else{
	    	System.out.println("Test Fail");
	    }
	  //  driver.getPageSource().contains("");
	    driver2.quit();
	}

	@Given("^I made a modification in the Check in Information$")
	public void i_made_a_modification_in_the_Check_in_Information() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
		
		//Login
		driver.findElement(By.name("email")).sendKeys("admin@phptravels.com");
		driver.findElement(By.name("password")).sendKeys("demoadmin");
		driver.findElement(By.className("btn-primary")).click();
		
		
		//Change Check In information

		driver.navigate().refresh();
		String xpath = "//*[@id='social-sidebar-menu']/li[8]/a/span";
		driver.findElement(By.xpath(xpath)).click();
		xpath = "//*[@id='Hotels']/li[1]/a";
		driver.findElement(By.xpath(xpath)).click();
		driver.findElement(By.partialLinkText("Cozumel Resort")).click();
		
		//Select Policy tab
		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/ul/li[4]/a")).click();
		//Set Check in time
		driver.findElement(By.name("checkintime")).click();
		driver.findElement(By.name("checkintime")).clear();
		driver.findElement(By.name("checkintime")).sendKeys(NewCI);
		//Set Check out time
		driver.findElement(By.name("checkouttime")).click();
		driver.findElement(By.name("checkouttime")).clear();
		driver.findElement(By.name("checkouttime")).sendKeys(NewCO);
		
		Thread.sleep(500);
		driver.findElement(By.id("update")).click();
		driver.quit();
		
	}

	@When("^The User Log in and Enter to the hotel information$")
	public void the_User_Log_in_and_Enter_to_the_hotel_information() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver2 = new ChromeDriver();
		
		//Enter to the main page
		driver2.navigate().to("http://www.phptravels.net/");
		driver2.manage().window().maximize();
		driver2.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/ul/ul/li[1]")).click();
		driver2.findElement(By.linkText("Login")).click();
			    
		//Login
		driver2.findElement(By.name("username")).sendKeys("user@phptravels.com");
		driver2.findElement(By.name("password")).sendKeys("demouser");
		driver2.findElement(By.xpath("//*[@id='loginfrm']/div[1]/div[5]/button")).click();
		Thread.sleep(500);
			    
		//Enter to hotels and select one
		driver2.findElement(By.xpath("/html/body/nav[1]/div/div/div/ul/li[2]/a")).click();
		Thread.sleep(500);
		driver2.findElement(By.partialLinkText("Cozumel Resort")).click();
		
	}
	
	@Then("^User should be able to see new Check In Information$")
	public void user_should_be_able_to_see_new_Check_In_Information() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		//Scroll down until find Check In information
	    Thread.sleep(500);
	    WebElement element = driver2.findElement(By.xpath("/html/body/div[4]/div[5]/div[3]/div/div/div[2]/div/div[1]/div[9]/div[2]"));
	    JavascriptExecutor js = (JavascriptExecutor) driver2;
	    js.executeScript("arguments[0].scrollIntoView(true);", element); 
	    Thread.sleep(1000);
	    
	    //Verify is the check in information matches
	    if ( driver2.getPageSource().contains(NewCI) && driver2.getPageSource().contains(NewCO)){
	    	System.out.println("Test Pass");
	    	System.out.println("Updated Check In information is available");
	    }else{
	    	System.out.println("Test Fail");
	    }
	  //  driver.getPageSource().contains("");
	    driver2.quit();
	}
	
}

