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
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RoomBooking {
	
	private WebDriver driver = null;
	private WebDriver driver2 = null;
	private List<List<String>> table;
	
	@Given("^Im user and Login in the Travel Agency Page$")
	public void im_user_and_Login_in_the_Travel_Agency_Page() throws Throwable {
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

	@When("^When I want to book a room in the Hotels page$")
	public void when_I_want_to_book_a_room_in_the_Hotels_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		//Click on Hotels
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.hotelsPage));
		driver.findElement(infoScreen.hotelsPage).click();
		
		
	}

	@When("^the room capacity is available$")
	public void the_room_capacity_is_available(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		table = arg1.raw();
		String hotel = table.get(0).get(1);
		String room = table.get(1).get(1);
		driver2 = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver2,10);
		driver2.navigate().to(" http://www.phptravels.net/admin");
		driver2.manage().window().maximize();
		driver2.findElement(infoScreen.usernameAdmin).sendKeys("admin@phptravels.com");
		driver2.findElement(infoScreen.passwordAdmin).sendKeys("demoadmin");
		driver2.findElement(infoScreen.LogInButtonAdmin).click();
		
		
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.hotelDrpdwn));
		driver2.findElement(infoScreen.hotelDrpdwn).click();
		driver2.findElement(infoScreen.roomOption).click();
		
		for (int i=1 ; i<50; i++) {
			
			String hotelLink = driver2.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/div/div[1]/div[2]/table/tbody/tr["+i+"]/td[4]")).getText();
			String roomLink = driver2.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/div/div[1]/div[2]/table/tbody/tr["+i+"]/td[3]/a")).getText();
			if (hotelLink.equals(hotel) && roomLink.equals(room)){
				driver2.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/div/div[1]/div[2]/table/tbody/tr["+i+"]/td[3]/a")).click();
				driver2.findElement(infoScreen.roomQuantity).click();
				driver2.findElement(infoScreen.roomQuantity).clear();
				driver2.findElement(infoScreen.roomQuantity).sendKeys("3");
				driver2.findElement(infoScreen.roomUpdateButton).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div[1]")));
				break;
			}else{ continue; }
		}
		driver2.quit();
		
	}

	@Then("^I should be able to book a room$")
	public void i_should_be_able_to_book_a_room(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		table = arg1.raw();
		String hotel = table.get(0).get(1);
		String room = table.get(1).get(1);
		
		WebElement element;
		WebDriverWait wait = new WebDriverWait(driver,10);
		element = driver.findElement(By.partialLinkText(hotel));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", element); 
	    element.click();
	    
		element = driver.findElement(By.xpath("/html/body/div[5]/section/div/div[1]"));
	    js.executeScript("arguments[0].scrollIntoView(true);", element); 
		
		for(int i=1 ; i<5 ; i++){
			element = driver.findElement(By.xpath("/html/body/div[5]/section/div/table/tbody/tr["+i+"]/td/div[2]/div/h4/a/b"));							  
			String roomLink = element.getText();
			if(roomLink.equals(room)){
				driver.findElement(By.xpath("/html/body/div[5]/section/div/table/tbody/tr["+i+"]/td/div[2]/div/div[5]/div[3]")).click();
				break;
			}else{ continue; }
		}
		
		driver.findElement(infoScreen.bookHotel).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.className("arrivalpay")));
		String BookingId = driver.findElement(By.xpath("//*[@id='body-section']/div[1]/div/div[7]/div[1]/div[3]/span")).getText();	
		driver.findElement(infoScreen.payOnArrive).click();
    	driver.switchTo().alert().accept();
    	driver.findElement(infoScreen.HotelsList).click();
		
		driver.findElement(infoScreen.accountButton).click();
	    driver.findElement(infoScreen.accountPage).click();
	    //Thread.sleep(1000);
	    	 
	    Assert.assertTrue(driver.getPageSource().contains(BookingId));
	 	System.out.println("Test Pass for Booking");
	 	 
	}

	@When("^the room capacity is unavailable$")
	public void the_room_capacity_is_unavailable(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		table = arg1.raw();
		String hotel = table.get(0).get(1);
		String room = table.get(1).get(1);
		driver2 = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver2,10);
		driver2.navigate().to(" http://www.phptravels.net/admin");
		driver2.manage().window().maximize();
		driver2.findElement(infoScreen.usernameAdmin).sendKeys("admin@phptravels.com");
		driver2.findElement(infoScreen.passwordAdmin).sendKeys("demoadmin");
		driver2.findElement(infoScreen.LogInButtonAdmin).click();
		
		
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.hotelDrpdwn));
		driver2.findElement(infoScreen.hotelDrpdwn).click();
		driver2.findElement(infoScreen.roomOption).click();
		
		for (int i=1 ; i<50; i++) {
			
			String hotelLink = driver2.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/div/div[1]/div[2]/table/tbody/tr["+i+"]/td[4]")).getText();
			String roomLink = driver2.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/div/div[1]/div[2]/table/tbody/tr["+i+"]/td[3]/a")).getText();
			if (hotelLink.equals(hotel) && roomLink.equals(room)){
				driver2.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/div/div[1]/div[2]/table/tbody/tr["+i+"]/td[3]/a")).click();
				driver2.findElement(infoScreen.roomQuantity).click();
				driver2.findElement(infoScreen.roomQuantity).clear();
				driver2.findElement(infoScreen.roomQuantity).sendKeys("2");
				driver2.findElement(infoScreen.roomUpdateButton).click();
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div[1]")));
				break;
			}else{ continue; }
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div[1]")));
		driver2.quit();
		
	}

	@Then("^I shouldnt be able to book a room$")
	public void i_shouldnt_be_able_to_book_a_room(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		
		table = arg1.raw();
		String hotel = table.get(0).get(1);
		String room = table.get(1).get(1);
		
		WebElement element;
		WebDriverWait wait = new WebDriverWait(driver,10);
		////////////////////////////////////////////////////////////////////
		for (int j=0 ; j<2 ; j++) {
			wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(hotel)));
			element = driver.findElement(By.partialLinkText(hotel));
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].scrollIntoView(true);", element); 
		    element.click();
			
		    element = driver.findElement(By.xpath("/html/body/div[5]/section/div/div[1]"));
		    js.executeScript("arguments[0].scrollIntoView(true);", element); 
			
			for(int i=1 ; i<5 ; i++){
				element = driver.findElement(By.xpath("/html/body/div[5]/section/div/table/tbody/tr["+i+"]/td/div[2]/div/h4/a/b"));							  
				String roomLink = element.getText();
				if(roomLink.equals(room)){
					driver.findElement(By.xpath("/html/body/div[5]/section/div/table/tbody/tr["+i+"]/td/div[2]/div/div[5]/div[3]")).click();
					break;
				}else{ continue; }
			}
			
			driver.findElement(infoScreen.bookHotel).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.className("arrivalpay")));
			String BookingId = driver.findElement(By.xpath("//*[@id='body-section']/div[1]/div/div[7]/div[1]/div[3]/span")).getText();	
			driver.findElement(infoScreen.payOnArrive).click();
	    	driver.switchTo().alert().accept();
	    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[1]/div/div[1]/div/center/b")));
	    	wait.until(ExpectedConditions.elementToBeClickable(infoScreen.HotelsList));
	    	driver.findElement(infoScreen.HotelsList).click();	
	    	//Thread.sleep(5000);
			
		}
		
		
		element = driver.findElement(By.partialLinkText(hotel));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", element); 
	    element.click();
		
	    element = driver.findElement(By.xpath("/html/body/div[5]/section/div/div[1]"));
	    js.executeScript("arguments[0].scrollIntoView(true);", element); 
	    
		
		//////////////////////////////////////////////////////
	    
		//driver.navigate().refresh();
		Assert.assertFalse(driver.getPageSource().contains(room),"Element is not located");
		
		driver.quit();
	}

}
