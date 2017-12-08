package Coupon;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.*;

public class availableCoupon_Steps {
	
	WebDriver driver;
	
	@Given("^user is on admin page$")
	public void user_is_on_admin_page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
//	    throw new PendingException();
	}

	@When("^username as \"([^\"]*)\" And I provide password as \"([^\"]*)\" to login$")
	public void username_as_And_I_provide_password_as_to_login(String arg1, String arg2) throws Throwable {
		driver.findElement(By.name("email")).sendKeys(arg1);
		driver.findElement(By.name("password")).sendKeys(arg2);
		driver.findElement(By.className("btn-primary")).click();
//	    throw new PendingException();
	}
	
	@When("^user enter coupon page$")
	public void user_enter_coupon_page() throws Throwable{
		driver.navigate().refresh();
		driver.findElement(By.partialLinkText("Coupons")).click();
		driver.findElement(By.xpath("//*[@id='content']/div[1]/div[2]/div[1]/button")).click();
		Thread.sleep(2000);
//	    throw new PendingException();
	}
	
	@And("^user add a coupon available$")
	public void user_add_a_coupon_available() throws Throwable {
		driver.findElement(By.xpath("//*[@id='rate']")).sendKeys("50");
		driver.findElement(By.name("max")).sendKeys("2");
		driver.findElement(By.name("code")).sendKeys("Trip");
		driver.findElement(By.xpath("//*[@id='ADD_COUPON']/div[2]/div/div[2]/div[3]/button")).click();
		Thread.sleep(2000);
		driver.quit();
//	    throw new PendingException();
	}
	
	@Given("^user log on initial page$")
	public void user_log_on_initial_page() throws Throwable {
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
    	driver = new ChromeDriver(); 
    	driver.navigate().to("http://www.phptravels.net/");
    	driver.manage().window().maximize();
    	
    	driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/ul/ul/li[1]")).click();
	    driver.findElement(By.linkText("Login")).click();
	    
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,250)", "");
    	Thread.sleep(1000);
		driver.findElement(By.name("username")).sendKeys("user@phptravels.com");
	    driver.findElement(By.name("password")).sendKeys("demouser");
	    driver.findElement(By.xpath("//*[@id='loginfrm']/div[1]/div[5]/button")).click();
//	    throw new PendingException();
	}

	@When("^user book a room$")
	public void user_book_a_room() throws Throwable {
		Thread.sleep(2000);
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
    	driver.findElement(By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[2]/input")).sendKeys("Trip");
    	driver.findElement(By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[3]/span")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.name("logged")).click();
    	Thread.sleep(5000);
    	driver.findElement(By.xpath("//*[@id='body-section']/div[1]/div/div[3]/center/button[1]")).click();
    	Thread.sleep(1000);
    	driver.switchTo().alert().accept();
//	    throw new PendingException();
	}

	@Then("^user see the reserve booked$")
	public void user_see_the_reserve_booked() throws Throwable {
		Thread.sleep(3000);
		String check = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[1]/div/center/b")).getText();
		if(check.equals("Reserved")) {
			System.out.println("Coupon available used");			
		}
		else {
			System.out.println("coupon not used");
		}
		Thread.sleep(2000);
		driver.quit();
//	    throw new PendingException();
	}

	@When("^user disable the coupon used$")
	public void user_disable_the_coupon_used() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
		
		driver.findElement(By.name("email")).sendKeys("admin@phptravels.com");
		driver.findElement(By.name("password")).sendKeys("demoadmin");
		driver.findElement(By.className("btn-primary")).click();
		
		driver.navigate().refresh();
		driver.findElement(By.partialLinkText("Coupons")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/table/tbody/tr[1]/td[11]/span/a[1]")).click();
		Thread.sleep(2000);
		Select dropdown = new Select(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[7]/div[2]/div/div[2]/form/div/div/div/div/div[1]/div/div[1]/div/select")));
		dropdown.selectByVisibleText(" Disable ");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[7]/div[2]/div/div[4]/button")).click();
		Thread.sleep(2000);
		driver.quit();
//	    throw new PendingException();
	}

	@Given("^user book a new room with the coupon disable$")
	public void user_book_a_new_room_with_the_coupon_disable() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
    	driver = new ChromeDriver(); 
    	driver.navigate().to("http://www.phptravels.net/");
    	driver.manage().window().maximize();
    	
    	driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/ul/ul/li[1]")).click();
	    driver.findElement(By.linkText("Login")).click();
	    
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,250)", "");
    	Thread.sleep(1000);
		driver.findElement(By.name("username")).sendKeys("user@phptravels.com");
	    driver.findElement(By.name("password")).sendKeys("demouser");
	    driver.findElement(By.xpath("//*[@id='loginfrm']/div[1]/div[5]/button")).click();
	    
	    Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/nav[1]/div/div/div/ul/li[2]/a")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.partialLinkText("Rendezvous Hotels")).click();
    	driver.navigate().refresh();
    	JavascriptExecutor jse4 = (JavascriptExecutor)driver;
    	jse4.executeScript("window.scrollBy(0,750)", "");
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//*[@id='ROOMS']/div/table/tbody/tr[1]/td/div[2]/div/div[5]/div[3]")).click();
    	
    	JavascriptExecutor jse2 = (JavascriptExecutor)driver;
    	jse2.executeScript("window.scrollBy(0,850)", "");
    	driver.findElement(By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[2]/input")).sendKeys("Trip");
    	driver.findElement(By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[3]/span")).click();
//	    throw new PendingException();
	}

	@Then("^invalid coupon message is displaying$")
	public void invalid_coupon_message_is_displaying() throws Throwable {
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
