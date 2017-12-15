package Hotel;
import java.util.ArrayList;
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

public class HotelFacilities {

	WebDriver driver = null;
	WebDriver driver2 = null;
	private List<List<String>> table;
	
	@Given("^I am Admin User and Log in$")
	public void i_am_Admin_User_and_Log_in() throws Throwable {
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

	@When("^I create a New Hotel$")
	public void i_create_a_New_Hotel(DataTable arg1) throws Throwable {
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
		
		
	}

	@When("^add Facilities information for the New Hotel$")
	public void add_Facilities_information_for_the_New_Hotel(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-500)","" );
		
		//Click on amenities Tab
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.hotelFacilitiesTab));
		driver.findElement(infoScreen.hotelFacilitiesTab).click();
		
		table = arg1.raw();
		int amenities = table.size();
		int count = 1;
		for (int i=4 ; i<23 ; i++){
			

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
		
		//Create
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.CreateButtonHotel));
		driver.findElement(infoScreen.CreateButtonHotel).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div[1]")));
		driver.quit();
	}

	@Then("^the User should be able to see the Facilites information$")
	public void the_User_should_be_able_to_see_the_Facilites_information(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		table = arg1.raw();
		String[] facilities = new String[table.size()-1];
		
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
	  	WebElement element = driver2.findElement(By.partialLinkText(table.get(0).get(1)));
	    JavascriptExecutor js = (JavascriptExecutor) driver2;
	    js.executeScript("arguments[0].scrollIntoView(true);", element); 
	  	wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(table.get(0).get(1))));
		driver2.findElement(By.partialLinkText(table.get(0).get(1))).click();
		
    	//Scroll down until facilities are visible 
    	element = driver2.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]"));
 	    js.executeScript("arguments[0].scrollIntoView(true);", element); 
 	    wait.until(ExpectedConditions.visibilityOf(element));
 	    
 	    for(int i=1 ; i<table.size() ; i++){
 	    	facilities[i-1] = table.get(i).get(1); 
 	    }
 	    int cont = 0;
 	    for(int i=0 ; i<facilities.length ; i++){
 	    	if(driver2.getPageSource().contains(facilities[0])){
 	    		cont++;
 	    	}
 	    }
    	
    	Assert.assertTrue(facilities.length==cont);
 	    System.out.println("Test Pass, All Facilities displayed");
 	    driver2.quit();
		
	}

	@Given("^I am User and Log in the Travel Agency page$")
	public void i_am_User_and_Log_in_the_Travel_Agency_page() throws Throwable {
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

	@When("^I want to see information about this \"([^\"]*)\"$")
	public void i_want_to_see_information_about_this(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		//Click on Hotels
//		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver,5);
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.hotelsPage));
		driver.findElement(infoScreen.hotelsPage).click();
		
		//Select the Hotel
		WebElement element = driver.findElement(By.partialLinkText(arg1));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView(true);", element); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(arg1)));
		driver.findElement(By.partialLinkText(arg1)).click();
		
		//Scroll down until facilities are visible 
    	element = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]"));
 	    js.executeScript("arguments[0].scrollIntoView(true);", element); 
 	    wait.until(ExpectedConditions.visibilityOf(element));
				
	}

	@Then("^I should be able to see the Facilities from the \"([^\"]*)\"$")
	public void i_should_be_able_to_see_the_Facilities_from_the_hotel(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		driver2 = new ChromeDriver();
		driver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver2,5);
		driver2.navigate().to(" http://www.phptravels.net/admin");
		driver2.manage().window().maximize();
		
		//Login
		driver2.findElement(infoScreen.usernameAdmin).sendKeys("admin@phptravels.com");
		driver2.findElement(infoScreen.passwordAdmin).sendKeys("demoadmin");
		driver2.findElement(infoScreen.LogInButtonAdmin).click();
		
		driver2.navigate().refresh();
		
		driver2.findElement(infoScreen.hotelDrpdwn).click();
		driver2.findElement(infoScreen.hotelOption).click();
		
		driver2.findElement(By.partialLinkText(arg1)).click();
		driver2.findElement(infoScreen.hotelFacilitiesTab).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div[1]/label")));
		
		//String[] Facilities;
		List<String> Facilities = new ArrayList<String>();
		
		for (int i=4; i<23 ; i++){
			WebElement element = driver2.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div["+i+"]/label/div"));
			//WebElement element = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div["+i+"]/label"));
			String proof = element.getAttribute("class");
			if(proof.equals("icheckbox_square-grey checked")){
				String option = driver2.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div["+i+"]/label")).getText();
				Facilities.add(option);
			}else{
				continue;
			}
		}
		
		driver2.quit();
		
		 int cont = 0;
	 	    for(int i=0 ; i<Facilities.size() ; i++){
	 	    	if(driver.getPageSource().contains(Facilities.get(i))){
	 	    		cont++;
	 	    	}
	 	    }
	    	
	    	Assert.assertTrue(Facilities.size()==cont);
	 	    System.out.println("Test Pass, All Facilities displayed from "+arg1);
	 	    driver.quit();
		
	}


	
	
}
