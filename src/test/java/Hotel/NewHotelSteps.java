package Hotel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver; 

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class NewHotelSteps {

	WebDriver driver = null;
	private List<List<String>> board;
	
	@Given("^I am on Admin Login Page$")
	public void i_am_on_Admin_Login_Page() throws Throwable {
		
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to(" http://www.phptravels.net/admin");
		driver.manage().window().maximize();
	}

	@Then("^I enter username as \"([^\"]*)\" And I enter password as \"([^\"]*)\" to login$")
	public void i_enter_username_as_And_I_enter_password_as_to_login(String arg1, String arg2) throws Throwable {
	    
		//Login
			driver.findElement(By.name("email")).sendKeys(arg1);
			driver.findElement(By.name("password")).sendKeys(arg2);
			driver.findElement(By.className("btn-primary")).click();
	}

	@Given("^The User want to create a New Hotel$")
	public void the_User_want_to_create_a_New_Hotel() throws Throwable {
	    
		//Add New Hotel
			driver.navigate().refresh();
			String xpath = "//*[@id='social-sidebar-menu']/li[8]/a/span";
				
			driver.findElement(By.xpath(xpath)).click();

			xpath = "//*[@id='Hotels']/li[1]/a";
			driver.findElement(By.xpath(xpath)).click();
				
			xpath = "//*[@id='content']/div/form/button";
			driver.findElement(By.xpath(xpath)).click();
		
	}

	@When("^The user will enter valid data on General tab$")
	public void the_user_will_enter_valid_data_on_General_tab(DataTable arg1) throws Throwable {
	    

		//Initialize data table
		board = arg1.raw();
		Select dropdown = new Select (driver.findElement(By.name("hotelstatus")));
		dropdown.selectByVisibleText("Enabled");
		
		//Enter Description
		driver.switchTo().frame(0);
		driver.findElement(By.cssSelector("body")).sendKeys(board.get(2).get(1));
		driver.switchTo().defaultContent();
		
		//
		driver.findElement(By.xpath("//*[@id='s2id_autogen1']")).sendKeys("Cozumel");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li")).click();
		
		dropdown = new Select (driver.findElement(By.name("hotelstars")));
		dropdown.selectByVisibleText("4");
		
		dropdown = new Select (driver.findElement(By.name("hoteltype")));
		dropdown.selectByVisibleText("Hotel");
	
		driver.findElement(By.name("hotelname")).sendKeys(board.get(1).get(1));
	   
	}

	@When("^Select Differents options on Facilities tab$")
	public void select_Differents_options_on_Facilities_tab() throws Throwable {
	    
		//Select Facilities tab
		driver.findElement(By.xpath("//*[@id='content']/form/div/ul/li[2]/a")).click();
		//Select Facilities 
		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div[4]/label")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div[7]/label")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div[9]/label")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[2]/div/div/div[10]/label")).click();
	}

	@When("^enter valid data on Policy tab$")
	public void enter_valid_data_on_Policy_tab() throws Throwable {
	    
		//Select Policy tab
		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/ul/li[4]/a")).click();
		//Set Check in time
		driver.findElement(By.name("checkintime")).click();
		driver.findElement(By.name("checkintime")).clear();
		driver.findElement(By.name("checkintime")).sendKeys("10:00 AM");
		//Set Check out time
		driver.findElement(By.name("checkouttime")).click();
		driver.findElement(By.name("checkouttime")).clear();
		driver.findElement(By.name("checkouttime")).sendKeys("05:00 PM");
		//Select Payment options
		driver.findElement(By.id("s2id_autogen4")).click();
		Select dropdown = new Select (driver.findElement(By.name("hotelpayments[]")));
		dropdown.selectByVisibleText("Credit Card");
		driver.findElement(By.id("s2id_autogen4")).click();
		dropdown = new Select (driver.findElement(By.name("hotelpayments[]")));
		dropdown.selectByVisibleText("Master/ Visa Card");
		//Enter Policy Text
		driver.findElement(By.name("hotelpolicy")).sendKeys("You cant bring pets");
	}

	@When("^Enter valida information in Contact tab$")
	public void enter_valida_information_in_Contact_tab() throws Throwable {
	    
		//Select Contact Tab
		driver.findElement(By.xpath("/html/body/div[2]/div/div/form/div/ul/li[5]/a")).click();
		
		//Enter Hotel Email
		driver.findElement(By.name("hotelemail")).click();
		driver.findElement(By.name("hotelemail")).sendKeys("cozumelresort@hotel.com");
		//Enter Hotel Website
		driver.findElement(By.name("hotelwebsite")).click();
		driver.findElement(By.name("hotelwebsite")).sendKeys("www.cozumelresort.com");
		//Enter Hotel Phone
		driver.findElement(By.name("hotelphone")).click();
		driver.findElement(By.name("hotelphone")).sendKeys("9878007478");
		//Submit
		driver.findElement(By.id("add")).click();
	}

	@Then("^The New Hotel should be created$")
	public void the_New_Hotel_should_be_created() throws Throwable {
	    
		driver.navigate().refresh();
		Thread.sleep(1000);
		//Veify if the hotel is created and displayed in the menu
		String comp = driver.findElement(By.partialLinkText("Cozumel Resort")).getText();
		
		if(comp.equals("Cozumel Resort")){
			System.out.println("Test Pass");
		}else{
			System.out.println("Test Failed");
		}
		driver.close();
	}


}
