package Coupon;

import org.openqa.selenium.chrome.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class useCoupon_Steps {
	
	static WebDriver driver;

	@Given("^User is on home Page$")
	public void user_is_on_home_Page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
    	driver = new ChromeDriver(); 
    	driver.navigate().to("http://www.phptravels.net/");
	    //throw new PendingException();
	}

	@When("^User enter to login page$")
	public void User_enter_to_login_page() throws Throwable {
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/ul/ul/li[1]")).click();
	    driver.findElement(By.linkText("Login")).click();
	    //throw new PendingException();
	}

	@When("^user enters username and password$")
	public void user_enters_username_and_password() throws Throwable {
		driver.navigate().refresh();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,250)", "");
    	Thread.sleep(1000);
		driver.findElement(By.name("username")).sendKeys("user@phptravels.com");
	    driver.findElement(By.name("password")).sendKeys("demouser");
	    driver.findElement(By.xpath("//*[@id='loginfrm']/div[1]/div[5]/button")).click();
	    Thread.sleep(2000);
//	    throw new PendingException();
	}

	@Given("^user book a new hotel with a coupon$")
	public void user_book_a_new_hotel_with_a_coupon() throws Throwable {
	    for(int i = 0; i < 2; i++) { 
	    	driver.findElement(By.xpath("/html/body/nav[1]/div/div/div/ul/li[2]/a")).click();
	    	Thread.sleep(2000);
	    	driver.findElement(By.partialLinkText("Rendezvous Hotels")).click();
	    	driver.navigate().refresh();
	    	JavascriptExecutor jse = (JavascriptExecutor)driver;
	    	jse.executeScript("window.scrollBy(0,750)", "");
	    	Thread.sleep(1000);
	    	driver.findElement(By.xpath("//*[@id='ROOMS']/div/table/tbody/tr[1]/td/div[2]/div/div[5]/div[3]")).click();
	    	
	    	JavascriptExecutor jse2 = (JavascriptExecutor)driver;
	    	jse2.executeScript("window.scrollBy(0,850)", "");
	    	driver.findElement(By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[2]/input")).sendKeys("Test");
	    	driver.findElement(By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[3]/span")).click();
	    	Thread.sleep(2000);
	    	driver.findElement(By.name("logged")).click();
	    	
	    	Thread.sleep(5000);
	    	driver.findElement(By.xpath("//*[@id='body-section']/div[1]/div/div[3]/center/button[1]")).click();
	    	driver.switchTo().alert().accept();
	    	Thread.sleep(2000);
	    } 
//	    throw new PendingException();
	}
	
	@When("^user wants to use a coup over of its max limit$")
	public void user_wants_to_use_a_coupon_over_of_its_max_limit() throws Throwable {
		driver.findElement(By.xpath("/html/body/nav[1]/div/div/div/ul/li[2]/a")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.partialLinkText("Rendezvous Hotels")).click();
    	driver.navigate().refresh();
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,750)", "");
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//*[@id='ROOMS']/div/table/tbody/tr[1]/td/div[2]/div/div[5]/div[3]")).click();
    	
    	JavascriptExecutor jse2 = (JavascriptExecutor)driver;
    	jse2.executeScript("window.scrollBy(0,850)", "");
    	driver.findElement(By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[2]/input")).sendKeys("Test");
    	driver.findElement(By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[3]/span")).click();
//	    throw new PendingException();
	}

	@Then("^message of invalid coupon is successufully displayed$")
	public void message_of_invalid_coupon_is_successufully_displayed() throws Throwable {
		Thread.sleep(2000);
		String message = driver.switchTo().alert().getText();
		System.out.println(message);
		if(message.equals("Invalid Coupon")) {
			System.out.print("Test passed");
		}
		else {
			System.out.println("Test failed");
		}
		driver.close();
//	    throw new PendingException();
	}
}
