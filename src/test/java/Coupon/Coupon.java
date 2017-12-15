package Coupon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import pageWebElementBox.infoScreen;

public class Coupon {
	
	static infoScreen infoScreen;
	String couponDiscount;
	Admin admin = new Admin();
	
	/*public void setDiscount(String discount) {
		couponDiscount = discount;
	}
	
	public String getDiscount() {
		return couponDiscount;
	}*/
	
	public void createACoupon(WebDriver driver, String discount, String maxUses, String code) {
		//setDiscount(discount);
		WebElement addButton = driver.findElement(infoScreen.addButton);
		addButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.addCoupon));
		
		WebElement discountField = driver.findElement(infoScreen.discount);
		WebElement maxUsesField = driver.findElement(infoScreen.maxUse);
		WebElement codeTextField = driver.findElement(infoScreen.codeText);
		WebElement submitCouponButton = driver.findElement(infoScreen.addCoupon);
		
		
		
		/*WebDriverWait wait2 = new WebDriverWait(driver,10);
		wait2.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"addcoupon\"]/div[2]/div[1]/div[2]/div/div[1]"))));
		*/
		/*List <WebElement> checkBoxList = driver.findElements(By.name("allmodules[]"));
		for(int i = 0; i < checkBoxList.size(); i++) {
			String value = checkBoxList.get(i).getAttribute("value");
			if(value.equalsIgnoreCase("hotels")) {
				checkBoxList.get(i).click();				
			}
		}*/		
		
		discountField.sendKeys(discount);
		maxUsesField.sendKeys(maxUses);
		codeTextField.sendKeys(code);
		WebElement hotelCheckBox = driver.findElement(By.xpath("//*[@id=\"addcoupon\"]/div[2]/div[1]/div[2]/div/div[1]"));
		hotelCheckBox.click();
		submitCouponButton.click();		
	}
	
	public void checkIfCouponIsCreated(WebDriver driver) {
		String value = "testCoupon_hotel";
		
		WebElement couponTable = driver.findElement(By.xpath("//*[@id='content']/div[1]/div[2]/div[2]/div/div[1]/div[2]/table/tbody"));
		List <WebElement> columns = couponTable.findElements(By.tagName("th"));
		for (int c = 0; c < columns.size(); c++) {
			if(columns.get(c).getText().equalsIgnoreCase("Coupon Code")) {
				List <WebElement> rows = couponTable.findElements(By.tagName("tr"));
				for(int r = 0; r < rows.size(); r++ ) {
					Assert.assertEquals("testCoupon_hotel", rows.get(r).getText());						
				}
		    }
		}
		
	}
	
	public void deleteCoupon() {
		System.out.println("Delete coupon...");
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://www.phptravels.net/admin");
		admin.logIn(driver, "admin@phptravels.com" , "demoadmin");
		admin.openCouponManagerScreen(driver);
		String value = "testCoupon_hotel";
		
		WebElement couponTable = driver.findElement(By.xpath("//*[@id='content']/div[1]/div[2]/div[2]/div/div[1]/div[2]/table/tbody"));
		List <WebElement> columns = couponTable.findElements(By.tagName("th"));
		for (int c = 0; c < columns.size(); c++) {
			if(columns.get(c).getText().equalsIgnoreCase("Coupon Code")) {
				List <WebElement> rows = couponTable.findElements(By.tagName("tr"));
				for(int r = 0; r < rows.size(); r++ ) {
					Assert.assertEquals("testCoupon_hotel", rows.get(r).getText());		
					WebElement deleteButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/table/tbody/tr[1]/td[11]/span/a[3]"));
					WebDriverWait wait = new WebDriverWait(driver,10); 
					wait.until(ExpectedConditions.alertIsPresent());
					Alert alertMessage = driver.switchTo().alert();
					String message = alertMessage.getText();
					Assert.assertEquals("Do you really want remove this entry?", message);
					alertMessage.accept();
					WebDriverWait wait2 = new WebDriverWait(driver,10); 
					wait2.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]/div[1]/button"))));
				}
		    }
		}
		
		
	}

}
