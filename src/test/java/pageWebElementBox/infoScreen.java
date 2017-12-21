package pageWebElementBox;

import org.openqa.selenium.By;

public class infoScreen {

	// Coupon
		
		// Admin login page
		public static By usernameAdmin = By.name("email"); 
		public static By passwordAdmin = By.name("password");
		public static By LogInButtonAdmin = By.className("btn-primary");
		
		// Admin hotel page
		public static By hotelDrpdwn = By.xpath("//*[@id='social-sidebar-menu']/li[10]/a/span") ;
		public static By hotelOption = By.xpath("//*[@id='Hotels']/li[1]/a");
		public static By hotelAddButton = By.xpath("//*[@id='content']/div/form/button");
		public static By hotelStatus = By.name("hotelstatus");
		public static By hotelName = By.name("hotelname");
		public static By hotelDescription = By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[1]/div[4]/div/div/div/div/iframe");
		public static By hotelStars = By.name("hotelstars");
		public static By hotelType = By.name("hoteltype");
		public static By hotelLocation = By.xpath("//*[@id='s2id_autogen1']");
		public static By hotelFacilitiesTab = By.xpath("//*[@id='content']/form/div/ul/li[2]/a");
		public static By hotelPolicyTab = By.xpath("/html/body/div[2]/div/div/form/div/ul/li[4]/a");
		public static By policyCheckIn = By.name("checkintime");
		public static By policyCheckOut = By.name("checkouttime");
		public static By addPayment = By.id("s2id_autogen4");
		public static By paymentOption = By.name("hotelpayments[]");
		public static By policyText = By.name("hotelpolicy");
		public static By hotelContactTab = By.xpath("/html/body/div[2]/div/div/form/div/ul/li[5]/a");
		public static By hotelEmail = By.name("hotelemail");
		public static By hotelWebPage = By.name("hotelwebsite");
		public static By hotelNumber = By.name("hotelphone");
		public static By CreateButtonHotel = By.id("add");
		public static By UpdateButtonHotel = By.id("update");
		
		//Admin Room page
		public static By roomOption = By.xpath("//*[@id='Hotels']/li[2]/a");
		public static By roomAddButton = By.xpath("/html/body/div[2]/div/div/div/form/button");
		public static By roomStatus = By.name("roomstatus");
		public static By roomType = By.name("roomtype");
		public static By roomHotel = By.name("hotelid");
		public static By roomDescription = By.xpath("/html/body/div[2]/div/div/form/div/div[1]/div/div[1]/div[5]/div/div/div/div/iframe");
		public static By roomPrice = By.name("basicprice");
		public static By roomQuantity = By.name("quantity");
		public static By roomMinStay = By.name("minstay");
		public static By roomMaxAdults = By.name("adults");
		public static By roomMaxChildren = By.name("children");
		public static By roomExtraBeds = By.name("extrabeds");
		public static By roomBedCharges = By.name("bedcharges");
		public static By roomAmenitiesTab = By.xpath("/html/body/div[2]/div/div/form/div/ul/li[2]/a");
		public static By roomCreateButton = By.id("add");
		public static By roomUpdateButton = By.id("update");
		
		// Admin coupon page
		public static By couponpage = By.partialLinkText("Coupons");
		public static By addButton = By.xpath("//*[@id='content']/div[1]/div[2]/div[1]/button");	
		public static By discount = By.xpath("//*[@id='rate']");
		public static By maxUse = By.name("max");
		public static By codeText = By.name("code");
		public static By AllHotels = By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div/div[2]/form/div[2]/div[1]/div[2]/div/div[1]/ins");
		public static By addCoupon = By.xpath("//*[@id='ADD_COUPON']/div[2]/div/div[2]/div[3]/button");
		public static By enableDropdown = By.xpath("/html/body/div[2]/div/div/div[7]/div[2]/div/div[2]/form/div/div/div/div/div[1]/div/div[1]/div/select");
		public static By editButton = By.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/table/tbody/tr[1]/td[11]/span/a[1]");
		public static By submitEditButton = By.xpath("/html/body/div[2]/div/div/div[7]/div[2]/div/div[4]/button");
		public static By startDate = By.xpath("//*[@id='stardate']");
		public static By expDate = By.xpath("//*[@id='expdate']");
		
		// User login page
		public static By accountButton = By.xpath("/html/body/div[3]/div/div/div[2]/ul/ul/li[1]");
		public static By singinButton = By.linkText("Login");
		public static By usernameUser = By.name("username");
		public static By passwordUser = By.name("password");
		public static By LogInButtonUser = By.xpath("//*[@id='loginfrm']/div[1]/div[5]/button");
		public static By accountPage = By.linkText("Account");
		public static By hotelsPage = By.xpath("/html/body/nav[1]/div/div/div/ul/li[2]/a");
		
		// Asserts
		public static By coupon1 = By.xpath("//*[@id='content']/div[1]/div[2]/div[2]/div/div[1]/div[2]/table/tbody/tr[1]/td[4]\r\n");
		public static By coupon2 = By.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/table/tbody/tr[2]/td[4]");
		
		public static By idValidation = By.xpath("//*[@id=\"body-section\"]/div[1]/div/div[7]/div[1]/div[3]/span");
		public static By reservedValidation = By.xpath("/html/body/div[4]/div[1]/div/div[1]/div/center/b");
		
		// Use coupon
		public static By validation1 = By.xpath("/html/body/div[4]/div/div[1]/div/div[1]/h3");
		public static By HotelsList = By.xpath("/html/body/nav[1]/div/div/div/ul/li[2]/a");
		public static By Hotel = By.xpath("/html/body/div[4]/div[7]/div[2]/div/table/tbody/tr[1]/td/div[2]/div/h4/a/b");
		public static By Room = By.xpath("//*[@id='ROOMS']/div/table/tbody/tr[1]/td/div[2]/div/div[5]/div[3]");
		public static By couponcode = By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[2]/input");
		public static By couponcode2 = By.xpath("/html/body/div[4]/div[2]/div[1]/div/div/form/div[3]/div[2]/div[2]/div[2]/input");
		public static By couponSubmit = By.xpath("//*[@id='bookingdetails']/div[5]/div[2]/div[2]/div[3]/span");
		public static By couponSubmit2 = By.xpath("/html/body/div[4]/div[2]/div[1]/div/div/form/div[3]/div[2]/div[2]/div[3]/span");
		public static By validation2 = By.xpath("/html/body/div[4]/div[2]/div[1]/div/div/form/div[5]/div[2]/div[4]/div");
		public static By bookHotel = By.name("logged");
		public static By bookHotel2 = By.xpath("/html/body/div[4]/div[2]/div[1]/div/div/div[3]/button");
		public static By validation3 = By.xpath("//*[@id='body-section']/div[1]/div/div[3]/center/button[1]");
		public static By payOnArrive = By.xpath("//*[@id='body-section']/div[1]/div/div[3]/center/button[1]");

		public static By bill = By.xpath("/html/body/div[4]/div[1]/div/div[9]/table/tbody/tr[1]/td[4]");
		public static By tax = By.xpath("/html/body/div[4]/div[1]/div/div[9]/table/tbody/tr[2]/td[4]");
		public static By total = By.xpath("//*[@id='body-section']/div[1]/div/div[7]/table/tbody/tr[3]/td[4]");
		
		//Supplier login page
		
		public static By usernameSupplier = By.name("email"); 
		public static By passwordSupplier = By.name("password");
		public static By LogInButtonSupplier = By.className("btn-primary");
		
		//Supplier Quick Booking
		public static By quickBooking = By.xpath("//*[@id='content']/div[1]/div[1]/button");
		public static By taxDrpdwn = By.name("applytax");
		public static By serviceDrpdwn = By.name("service");
		public static By quickNextButton = By.xpath("//*[@id='quickbook']/div[2]/div/form/div[3]/button[2]");
		public static By customerType = By.name("usertype");
		public static By customerName = By.xpath("/html/body/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[2]/div[2]/div/select");
		public static By quickCheckIn = By.xpath("/html/body/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div");
		public static By quickCheckOut = By.xpath("/html/body/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[2]/div");
		public static By quickHotelName = By.xpath("/html/body/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[4]/div/select");
		public static By quickRooms = By.id("poprooms");
		public static By bookNowButton = By.xpath("/html/body/div[2]/div/div/div/div[2]/div[1]/form/div[5]/div");
		
}
