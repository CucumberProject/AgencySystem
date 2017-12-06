package Coupon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
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
		driver.findElement(By.xpath("//*[@id='rate']")).sendKeys("45");
		driver.findElement(By.name("max")).sendKeys("3");
		driver.findElement(By.name("code")).sendKeys("Promo1");
		driver.findElement(By.xpath("//*[@id='ADD_COUPON']/div[2]/div/div[2]/div[3]/button")).click();
		Thread.sleep(2000);
		
		//75% of discount
		driver.findElement(By.xpath("//*[@id='content']/div[1]/div[2]/div[1]/button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='rate']")).sendKeys("75");
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
	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
	}

	@Given("^coupon created are used$")
	public void coupon_created_are_used() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
	}

	@Then("^the discount of both coupons are available$")
	public void the_discount_of_both_coupons_are_available() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
	}


}
