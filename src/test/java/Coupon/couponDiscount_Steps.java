package Coupon;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class couponDiscount_Steps {
	
	WebDriver driver;
	
	@Given("^I am on admin home page$")
	public void i_am_on_admin_home_page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
//	    throw new PendingException();
	}

	@When("^I provide username as \"([^\"]*)\" And I provide password as \"([^\"]*)\" to login$")
	public void i_provide_username_as_And_I_provide_password_as_to_login(String arg1, String arg2) throws Throwable {
		driver.findElement(By.name("email")).sendKeys(arg1);
		driver.findElement(By.name("password")).sendKeys(arg2);
		driver.findElement(By.className("btn-primary")).click();
//	    throw new PendingException();
	}

	@When("^user select coupon page$")
	public void user_select_coupon_page() throws Throwable {
		driver.navigate().refresh();
		driver.findElement(By.partialLinkText("Coupons")).click();
//	    throw new PendingException();
	}

	@When("^user add two new coupons with a discount$")
	public void user_add_two_new_coupons_with_a_discount() throws Throwable {
		//45% of discount
		driver.findElement(By.xpath("//*[@id='content']/div[1]/div[2]/div[1]/button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='rate']")).sendKeys("40");
		driver.findElement(By.name("max")).sendKeys("3");
		driver.findElement(By.name("code")).sendKeys("Promo1");
		driver.findElement(By.xpath("//*[@id='ADD_COUPON']/div[2]/div/div[2]/div[3]/button")).click();
		Thread.sleep(2000);
		
		//75% of discount
		driver.findElement(By.xpath("//*[@id='content']/div[1]/div[2]/div[1]/button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='rate']")).sendKeys("70");
		driver.findElement(By.name("max")).sendKeys("5");
		driver.findElement(By.name("code")).sendKeys("Promo2");
		driver.findElement(By.xpath("//*[@id='ADD_COUPON']/div[2]/div/div[2]/div[3]/button")).click();
		Thread.sleep(2000);
//	    throw new PendingException();
	}

	@Then("^coupons should are visible$")
	public void coupons_should_are_visible() throws Throwable {
		String coupon1 = driver.findElement(By.xpath("//*[@id='content']/div[1]/div[2]/div[2]/div/div[1]/div[2]/table/tbody/tr[1]/td[4]\r\n")).getText();
		String coupon2 = driver.findElement(By.xpath("//*[@id='content']/div[1]/div[2]/div[2]/div/div[1]/div[2]/table/tbody/tr[2]/td[4]\r\n")).getText();
		if(coupon1.equals("Promo2") && coupon2.equals("Promo1")) {
			System.out.println("Coupons created successfully");
		}
		else {
			System.out.println("Coupons were not created");
		}
		driver.quit();	
//	    throw new PendingException();
	}

	@Given("^user enter account initial page$")
	public void user_enter_account_initial_page() throws Throwable {
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

	@Given("^coupon promo1 is used$")
	public void coupon_promo1_is_used() throws Throwable {
		//Coupon with promo 1
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
    	driver.findElement(By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[2]/input")).sendKeys("Promo1");
    	driver.findElement(By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[3]/span")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.name("logged")).click();
    	Thread.sleep(5000);
    	driver.findElement(By.xpath("//*[@id='body-section']/div[1]/div/div[3]/center/button[1]")).click();
    	Thread.sleep(2000);
    	driver.switchTo().alert().accept();

    	String sBill = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[9]/table/tbody/tr[1]/td[4]")).getText();
		String sTax = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[9]/table/tbody/tr[2]/td[4]")).getText();
		JavascriptExecutor jse21 = (JavascriptExecutor)driver;
    	jse21.executeScript("window.scrollBy(0,450)", "");
    	Thread.sleep(1000);
		String sTotal = driver.findElement(By.xpath("//*[@id='body-section']/div[1]/div/div[7]/table/tbody/tr[3]/td[4]")).getText();
		System.out.println(sTotal);
		float iBill = Float.parseFloat(sBill.replaceAll("[$]", ""));
		float iTax = Float.parseFloat(sTax.replaceAll("[USD $]", ""));
		float iTotal = Float.parseFloat(sTotal.replaceAll("[USD $]", ""));
		
		float discount = iBill * 60 / 100;
		float total = discount + iTax;
		
		if (total == iTotal) {
			System.out.println("El descuento de 40% es correcto");
		}
		else {
			System.out.println("El descuento no es correcto");
		}
		Thread.sleep(2000);	
//	    throw new PendingException();
	}

	@Then("^coupon promo2 is used$")
	public void coupon_promo2_is_used() throws Throwable {
		//Coupon with promo2
    	driver.findElement(By.xpath("/html/body/nav[1]/div/div/div/ul/li[2]/a")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.partialLinkText("Rendezvous Hotels")).click();
    	driver.navigate().refresh();
    	JavascriptExecutor jse3 = (JavascriptExecutor)driver;
    	jse3.executeScript("window.scrollBy(0,750)", "");
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//*[@id='ROOMS']/div/table/tbody/tr[1]/td/div[2]/div/div[5]/div[3]")).click();
    	JavascriptExecutor jse4 = (JavascriptExecutor)driver;
    	jse4.executeScript("window.scrollBy(0,850)", "");
    	driver.findElement(By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[2]/input")).sendKeys("Promo2");
    	driver.findElement(By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[3]/span")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.name("logged")).click();
    	Thread.sleep(5000);
    	driver.findElement(By.xpath("//*[@id='body-section']/div[1]/div/div[3]/center/button[1]")).click();
    	driver.switchTo().alert().accept();
    	Thread.sleep(2000);

    	JavascriptExecutor jse21 = (JavascriptExecutor)driver;
    	jse21.executeScript("window.scrollBy(0,450)", "");
    	Thread.sleep(1000);
    	String sBill = driver.findElement(By.xpath("//*[@id='body-section']/div[1]/div/div[7]/table/tbody/tr[1]/td[4]")).getText();
		String sTax = driver.findElement(By.xpath("//*[@id='body-section']/div[1]/div/div[7]/table/tbody/tr[2]/td[4]")).getText();
		String sTotal = driver.findElement(By.xpath("//*[@id='body-section']/div[1]/div/div[7]/table/tbody/tr[3]/td[4]")).getText();
		float iBill = Float.parseFloat(sBill.replaceAll("[$]", ""));
		float iTax = Float.parseFloat(sTax.replaceAll("[USD $]", ""));
		float iTotal = Float.parseFloat(sTotal.replaceAll("[USD $]", ""));
		
		float discount = iBill * 30 / 100;
		float total = discount + iTax;
		
		if (total == iTotal) {
			System.out.println("El descuento de 70% es correcto");
		}
		else {
			System.out.println("El descuento no es correcto");
		}
		Thread.sleep(2000);
		driver.quit();
//	    throw new PendingException();
	}
	


}
