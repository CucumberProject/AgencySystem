package Coupon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageWebElementBox.infoScreen;

public class User {
	static infoScreen infoScreen;
	
	public void logIn(WebDriver driver, String user, String password) {
		WebElement userField = driver.findElement(infoScreen.usernameUser);
		WebElement passField = driver.findElement(infoScreen.passwordUser);
		WebElement loginButton = driver.findElement(infoScreen.LogInButtonUser);
		
		userField.sendKeys(user);
		passField.sendKeys(password);
		loginButton.click();		
	}

}
