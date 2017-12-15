package Hotel;

import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import pageWebElementBox.infoScreen;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class newHotelCoupon_Steps {
	
	static WebDriver driver;
	private List<List<String>> table;
	static String idBook;
	
	@Before("@newHotelCoupon")
	public void StartDiver() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@After("@newHotelCoupon")
	public void StopDriver(){
		driver.quit();
	}
	
	@Given("^user enter to admin page$")
	public void user_enter_to_admin_page(DataTable admincredentials) throws Throwable {
		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<List<String>> data = admincredentials.raw();
		driver.findElement(infoScreen.usernameAdmin).sendKeys(data.get(0).get(0));
		driver.findElement(infoScreen.passwordAdmin).sendKeys(data.get(0).get(1));
		driver.findElement(infoScreen.LogInButtonAdmin).click();
//	    throw new PendingException();
	}

	@Given("^user add a new hotel$")
	public void user_add_a_new_hotel(DataTable arg1) throws Throwable {
		driver.findElement(infoScreen.hotelDrpdwn).click();
		driver.findElement(infoScreen.hotelOption).click();
		driver.findElement(infoScreen.hotelAddButton).click();
		
		//Initialize data table
		table = arg1.raw();
		String status = table.get(1).get(1);
		String name = table.get(2).get(1);
		String description = table.get(3).get(1);
		String stars = table.get(4).get(1);
		String type = table.get(5).get(1);
		String location = table.get(6).get(1);
				
		WebDriverWait wait = new WebDriverWait(driver,5);
		
		Select dropdown = new Select (driver.findElement(infoScreen.hotelStatus));
		dropdown.selectByVisibleText(status);
		
		//Enter name
		driver.findElement(infoScreen.hotelName).sendKeys(name);
		
		//Enter Description
		WebElement desFrame = driver.findElement(infoScreen.hotelDescription);
		driver.switchTo().frame(desFrame);
		driver.findElement(By.cssSelector("body")).sendKeys(description);
		driver.switchTo().defaultContent();
		
		
		dropdown = new Select (driver.findElement(infoScreen.hotelStars));
		dropdown.selectByVisibleText(stars);
		
		dropdown = new Select (driver.findElement(infoScreen.hotelType));
		dropdown.selectByVisibleText(type);
	
		//Enter Location
		driver.findElement(infoScreen.hotelLocation).sendKeys(location);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[9]/ul/li/div/span")));
		driver.findElement(By.xpath("/html/body/div[9]/ul/li/div/span")).click();
		
		//Create
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.CreateButtonHotel));
		driver.findElement(infoScreen.CreateButtonHotel).click();
		
//	    throw new PendingException();
	}
	
	@And("^Create a New Room for the hotel$")
	public void create_a_New_Room_for_the_hotel(DataTable arg1) throws Throwable {
		// throw new PendingException();
		driver.navigate().refresh();
		driver.findElement(infoScreen.hotelDrpdwn).click();
		driver.findElement(infoScreen.roomOption).click();
		driver.findElement(infoScreen.hotelAddButton).click();
		
		//Prepare values from table
		table = arg1.raw();
		String status = table.get(1).get(1);
		String type = table.get(2).get(1);
		String hotel = table.get(3).get(1);
		String description = table.get(4).get(1);
		String price = table.get(5).get(1);
		String quantity = table.get(6).get(1);
		String stay = table.get(7).get(1);
		String adults = table.get(8).get(1);
		String children = table.get(9).get(1);
		String extraBed = table.get(10).get(1);
		String priceBed = table.get(11).get(1);
		
		
		//Select Room Status
		Select dropdown = new Select (driver.findElement(infoScreen.roomStatus));
		dropdown.selectByVisibleText(status);
		
		//Select Room Type
		dropdown = new Select (driver.findElement(infoScreen.roomType));
		dropdown.selectByVisibleText(type);
		//Presidential Suite
		
		//Select hotel
		dropdown = new Select (driver.findElement(infoScreen.roomHotel));
		dropdown.selectByVisibleText(hotel);
		// Rendezvous Hotels 
		
		//Enter Description
		WebElement desFrame = driver.findElement(infoScreen.roomDescription);
		driver.switchTo().frame(desFrame);
		driver.findElement(By.cssSelector("body")).sendKeys(description);
		driver.switchTo().defaultContent();
		
		//Enter Room Price
		driver.findElement(infoScreen.roomPrice).sendKeys(price);
		//Enter Room Quantity
		driver.findElement(infoScreen.roomQuantity).sendKeys(quantity);
		//Enter Room Minimum Stay
		driver.findElement(infoScreen.roomMinStay).sendKeys(stay);
		//Enter Room Max Adults
		driver.findElement(infoScreen.roomMaxAdults).sendKeys(adults);
		//Enter Room Max Children
		driver.findElement(infoScreen.roomMaxChildren).sendKeys(children);
		//Enter Room Max Extra Beds
		driver.findElement(infoScreen.roomExtraBeds).sendKeys(extraBed);
		//Enter Extra Bed Charges
		driver.findElement(infoScreen.roomBedCharges).sendKeys(priceBed);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-500)","" );
		
		//Click on amenities Tab
		driver.findElement(infoScreen.roomAmenitiesTab).click();
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		table = arg1.raw();
		int amenities = table.size();
		int count = 1;
		for (int i=4 ; i<40 ; i++){
			
			String proof = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div["+i+"]/label")).getText();
			if (proof.equals(table.get(count).get(1))){
				driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div["+i+"]/label")).click();
				count++;
				if(count == amenities){
					break;
				}
			}else{
				continue;
			}
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(infoScreen.roomCreateButton));
		driver.findElement(infoScreen.roomCreateButton).click();
	}

	@Then("^a new coupon is added$")
	public void a_new_coupon_is_added(DataTable Coupon) throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
//		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(infoScreen.couponpage));
		driver.findElement(infoScreen.couponpage).click();
		List<List<String>> data = Coupon.raw();
		driver.findElement(infoScreen.addButton).click();
		driver.findElement(infoScreen.discount).sendKeys(data.get(0).get(0));
		driver.findElement(infoScreen.maxUse).sendKeys(data.get(0).get(1));
		driver.findElement(infoScreen.codeText).sendKeys(data.get(0).get(2));
		driver.findElement(infoScreen.addCoupon).click();
//	    throw new PendingException();
	}

	@Given("^user enter to the home page$")
	public void user_enter_to_the_home_page(DataTable usercredentials) throws Throwable {
		driver.navigate().to("http://www.phptravels.net/");
    	driver.manage().window().maximize();
    	
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(infoScreen.accountButton).click();
	    driver.findElement(infoScreen.singinButton).click();
    	
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<List<String>> data = usercredentials.raw();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,250)", "");
        driver.findElement(infoScreen.usernameUser).sendKeys(data.get(0).get(0));
	    driver.findElement(infoScreen.passwordUser).sendKeys(data.get(0).get(1)); 
	    (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(infoScreen.LogInButtonUser));
	    driver.findElement(infoScreen.LogInButtonUser).click();
//	    throw new PendingException();
	}

	@When("^user select de new hotel and book a room$")
	public void user_select_de_new_hotel_and_book_a_room() throws Throwable {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.validation1));
		driver.findElement(infoScreen.HotelsList).click();
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.Hotel));
    	driver.findElement(infoScreen.Hotel).click();
    	driver.navigate().refresh();
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,500)", ""); 
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.Room));
    	driver.findElement(infoScreen.Room).click();
//	    throw new PendingException();
	}

	@When("^new coupon is used$")
	public void new_coupon_is_used(DataTable coupon) throws Throwable {
		List<List<String>> data = coupon.raw();
		JavascriptExecutor jse2 = (JavascriptExecutor)driver;
    	jse2.executeScript("window.scrollBy(0,500)", "");
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(infoScreen.couponcode2));
    	driver.findElement(infoScreen.couponcode2).sendKeys(data.get(0).get(0));
    	driver.findElement(infoScreen.couponSubmit2).click();
    	driver.findElement(infoScreen.bookHotel).click();
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.idValidation));
    	idBook = driver.findElement(infoScreen.idValidation).getText();
    	(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(infoScreen.validation3));
    	driver.findElement(infoScreen.payOnArrive).click();
    	driver.switchTo().alert().accept();
//	    throw new PendingException();
	}

	@Then("^the book is listed on the account$")
	public void the_book_is_listed_on_the_account() throws Throwable {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBe(infoScreen.reservedValidation, "Reserved"));
		driver.findElement(infoScreen.accountButton).click();
	    driver.findElement(infoScreen.accountPage).click();
	    
	    if (driver.getPageSource().contains(idBook)){
	    	JOptionPane.showMessageDialog(null,"Test Passed"); 
 	    }else{
 	    	System.out.println("Test Failed");
 	    } 
	    //throw new PendingException();
	}


}
