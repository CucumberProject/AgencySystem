package Coupon;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Car {
	
	Coupon coupon = new Coupon();
	
	public void bookACar(WebDriver driver) {
		//Wait until the Account page is loaded 
	    WebElement carMenuOption = driver.findElement(By.xpath("/html/body/nav[1]/div/div/div/ul/li[5]/a"));	
		//Click on Hotel option from the menu
		carMenuOption.click();
		
		//Wait until the list of cars is loaded
		WebDriverWait wait2 = new WebDriverWait(driver,10);
		wait2.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"body-section\"]/div[7]/div[2]/div/table"))));
		
		
		//Click on details button
		WebElement detailsButton = driver.findElement(By.xpath("//*[@id=\"body-section\"]/div[7]/div[2]/div/table/tbody/tr[1]/td/div[2]/div/div[5]/a/button"));
		detailsButton.click();
		
		
		//Wait until booking page is loaded
		JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,750)", "");
    	WebDriverWait wait3 = new WebDriverWait(driver,10); 
		wait3.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/form/button"))));
		WebElement bookNowButton = driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/form/button"));
		//dropdownlist to select a location
		
    	bookNowButton.click();   	
    	
	}
	
	public void useACoupon(WebDriver driver, String coupon) {
		Coupon c = new Coupon();
		WebElement couponField = driver.findElement(By.xpath("//*[@id=\"bookingdetails\"]/div[5]/div[2]/div[2]/div[2]/input"));
		WebElement bookingTotal = driver.findElement(By.xpath("//*[@id=\"displaytotal\"]")); 
		
		couponField.sendKeys(coupon);
		WebElement applyCouponButton = driver.findElement(By.xpath("//*[@id=\"bookingdetails\"]/div[5]/div[2]/div[2]/div[3]/span"));
		applyCouponButton.click();
	}
	
	public void validateCoupon(WebDriver driver){
		if(driver.findElement(By.xpath("//*[@id=\"bookingdetails\"]/div[5]/div[2]/div[4]")).isDisplayed()){
			System.out.println("Scenario: Failed");
		}else {
			WebDriverWait wait = new WebDriverWait(driver,10); 
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alertMessage = driver.switchTo().alert();
			String message = alertMessage.getText();
			alertMessage.accept();
			Assert.assertEquals("Invalid Coupon", message);
			System.out.println("Scenario: Passed");
		}
		//coupon.deleteCoupon();
	}

}
