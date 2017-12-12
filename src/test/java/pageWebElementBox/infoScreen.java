package pageWebElementBox;

import org.openqa.selenium.By;

public class infoScreen {

	// Coupon
		
		// Admin login page
		public static By usernameAdmin = By.name("email"); 
		public static By passwordAdmin = By.name("password");
		public static By LogInButtonAdmin = By.className("btn-primary");
		
		// Admin coupon page
		public static By couponpage = By.partialLinkText("Coupons");
		public static By addButton = By.xpath("//*[@id='content']/div[1]/div[2]/div[1]/button");	
		public static By discount = By.xpath("//*[@id='rate']");
		public static By maxUse = By.name("max");
		public static By codeText = By.name("code");
		public static By addCoupon = By.xpath("//*[@id='ADD_COUPON']/div[2]/div/div[2]/div[3]/button");
		
		// User login page
		public static By accountButton = By.xpath("/html/body/div[3]/div/div/div[2]/ul/ul/li[1]");
		public static By singinButton = By.linkText("Login");
		public static By usernameUser = By.name("username");
		public static By passwordUser = By.name("password");
		public static By LogInButtonUser = By.xpath("//*[@id='loginfrm']/div[1]/div[5]/button");
		
		// Asserts
		public static By coupon1 = By.xpath("//*[@id='content']/div[1]/div[2]/div[2]/div/div[1]/div[2]/table/tbody/tr[1]/td[4]\r\n");
		public static By coupon2 = By.xpath("//*[@id='content']/div[1]/div[2]/div[2]/div/div[1]/div[2]/table/tbody/tr[2]/td[4]\r\\n");
		
		public static By idValidation = By.xpath("//*[@id=\"body-section\"]/div[1]/div/div[7]/div[1]/div[3]/span");
		
		
		// Use coupon
		public static By validation1 = By.xpath("/html/body/div[4]/div/div[1]/div/div[1]/h3");
		public static By HotelsList = By.xpath("/html/body/nav[1]/div/div/div/ul/li[2]/a");
		public static By Hotel = By.xpath("/html/body/div[4]/div[7]/div[2]/div/table/tbody/tr[1]/td/div[2]/div/h4/a/b");
		public static By Room = By.xpath("//*[@id='ROOMS']/div/table/tbody/tr[1]/td/div[2]/div/div[5]/div[3]");
		public static By couponcode = By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[2]/input");
		public static By couponSubmit = By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[3]/span");
		public static By validation2 = By.xpath("/html/body/div[4]/div[2]/div[1]/div/div/form/div[5]/div[2]/div[4]/div");
		public static By bookHotel = By.name("logged");
		public static By validation3 = By.xpath("//*[@id='body-section']/div[1]/div/div[3]/center/button[1]");
		public static By payOnArrive = By.xpath("//*[@id='body-section']/div[1]/div/div[3]/center/button[1]");
}
