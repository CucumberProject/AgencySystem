package Coupon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

public class Driver {
	//String url;
	//WebDriver driver = null;
	
	/*public Driver() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
	}*/
	
	public WebDriver startDriver() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	
	public void stopDriver(WebDriver driver) {
		driver.quit();
	}

}
