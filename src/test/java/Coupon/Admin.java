package Coupon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import pageWebElementBox.infoScreen;


public class Admin {
	static infoScreen infoScreen;
	
	public void logIn(WebDriver driver, String user, String password) {
		//driver.navigate().to("http://www.phptravels.net/admin");
		WebElement userField = driver.findElement(infoScreen.usernameAdmin);
		WebElement passField = driver.findElement(infoScreen.passwordAdmin);
		WebElement loginButton = driver.findElement(infoScreen.LogInButtonAdmin);
		
		userField.sendKeys(user);
		passField.sendKeys(password);
		loginButton.click();		
	}
	
	public void openCouponManagerScreen(WebDriver driver) {
		WebElement couponOption = driver.findElement(infoScreen.couponpage);
		couponOption.click();
	}
	
	
}
