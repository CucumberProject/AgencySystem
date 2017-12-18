package Coupon;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageWebElementBox.infoScreen;

public class Hotel {
	static String total;
	static String discount;
	
	Coupon coupon = new Coupon();
	
	
	public void bookAHotel(WebDriver driver) {
		//Wait until the Account page is loaded
	    WebElement hotelMenuOption = driver.findElement(By.xpath("html/body/nav[1]/div/div/div/ul/li[2]/a"));	
		//Click on Hotel option from the menu
		hotelMenuOption.click();
		
		//Wait until the list of hotels is loaded
		WebDriverWait wait2 = new WebDriverWait(driver,10);
		wait2.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"body-section\"]/div[7]/div[2]/div/table"))));
		
		
		//Click on details button
		WebElement detailsButton = driver.findElement(By.xpath("//*[@id=\"body-section\"]/div[7]/div[2]/div/table/tbody/tr[1]/td/div[2]/div/div[5]/a/button"));
		detailsButton.click();
		
		
		//Wait until booking page is loaded
		JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,850)", "");
    	WebDriverWait wait3 = new WebDriverWait(driver,10);  
		wait3.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"ROOMS\"]/div/table/tbody/tr/td/div[2]/div/div[5]/div[3]"))));
		WebElement bookNowButton = driver.findElement(By.xpath("//*[@id=\"ROOMS\"]/div/table/tbody/tr/td/div[2]/div/div[5]/div[3]"));
			
		
    	bookNowButton.click();   	
    	
	}
	
	public void useACoupon(WebDriver driver, String coupon) {
		Coupon c = new Coupon();
		WebElement couponField = driver.findElement(By.xpath("//*[@id=\"bookingdetails\"]/div[5]/div[2]/div[2]/div[2]/input"));
		WebElement bookingTotal = driver.findElement(By.xpath("//*[@id=\"displaytotal\"]"));
		
		total = bookingTotal.getText();
		//discount =  c.getDiscount();
		
		couponField.sendKeys(coupon);
		WebElement applyCouponButton = driver.findElement(By.xpath("//*[@id=\"bookingdetails\"]/div[5]/div[2]/div[2]/div[3]/span"));
		applyCouponButton.click();
	}
	
	public void checkDiscount(WebDriver driver, String couponDiscount){
		WebElement confirmBookingButton = driver.findElement(By.name("logged"));
		confirmBookingButton.click();
		WebDriverWait wait = new WebDriverWait(driver,10); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"body-section\"]/div[1]/div/div[3]/center/button[2]")));
		discount = couponDiscount;
		float total1 = (Float.parseFloat(total) * (Float.parseFloat(discount)/100));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,850)", "");
		WebDriverWait wait2 = new WebDriverWait(driver,10); 
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"body-section\"]/div[1]/div")));
		WebElement invoice = driver.findElement(By.xpath("//*[@id=\"body-section\"]/div[1]/div/div[9]/table/tbody/tr[3]/td[4]"));
		String totalInvoice = invoice.getText();
		//System.out.println("Total Invoice: " + totalInvoice);
		String[] divideString = totalInvoice.split("\\$");
		String symbol = divideString[0];
		//System.out.println(symbol);
		float amount = Float.parseFloat(divideString[1]);
		Assert.assertEquals(total1, amount, 0.1);
		System.out.println("Scenario: Passed");
			
		
	}
	
	public void validateCoupon(WebDriver driver){
		try{
			WebDriverWait wait = new WebDriverWait(driver,10); 
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alertMessage = driver.switchTo().alert();
			String message = alertMessage.getText();
			alertMessage.accept();
			Assert.assertEquals("Invalid Coupon", message);
			System.out.println("Scenario: Passed");
		}catch (Exception e) {
			System.out.println("Scenario: failed");
		}
		
	}
		
		

}
