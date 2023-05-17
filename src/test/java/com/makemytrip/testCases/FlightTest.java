package com.makemytrip.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Base.Base;
import com.aventstack.extentreports.Status;
import com.makemytrip.pages.FlightBookingPage;
import com.makemytrip.pages.FlightInfoPage;
import com.makemytrip.pages.HomePage;
import com.makemytrip.utils.Utility;


public class FlightTest extends Base {
	
	public FlightTest() {
		super();
	}
	
	//@Parameters({ "browser", "url" })
	//@BeforeMethod
	
	// To intialise browser and open url
	/*public void setUp(String browserName, String websiteUrl) {
		logger=report.createTest("lowest price flight booking");
		invokeBrowser(browserName);
		openURL(websiteUrl);
		
	}*/
	
	@Parameters({ "browser", "url" })
	//@Test(dataProvider="bookingDetails"),String firstname,String lastname,String mobilenum,String email
	@Test
	public void flightBooking(String browserName, String websiteUrl) {
		logger=report.createTest("lowest price flight booking");
		invokeBrowser(browserName);
		openURL(websiteUrl);
		HomePage homepage=new HomePage(driver);
		homepage.closePopup();
		homepage.isElementDisplayedAndEnabled();
		homepage.fillFromField(prop.getProperty("fromCity"));
		homepage.setToField(prop.getProperty("toCity"));
		homepage.selectDepartureDate(prop.getProperty("departureMonth"), prop.getProperty("departureDate"),"flight");
		FlightInfoPage flightInfoPage=homepage.submit();
		flightInfoPage.closePopUp();
		Integer expectedFare=flightInfoPage.getLowestCharges();
		flightInfoPage.clickOnViewPriceBtn();
		FlightBookingPage bookingPage=flightInfoPage.clickOnBookNow();
		Integer actualFare=bookingPage.getMinFlightChargeByAddingBaseFareAndTaxes();
		try {
			Assert.assertEquals(actualFare,expectedFare);
			logger.log(Status.PASS,"Flight fare after adding basefare and taxes: "+actualFare+" equals the minFare: "+expectedFare+"");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		bookingPage.verifyTotalFare();
		//bookingPage.fillBookingDetails(firstname,lastname,mobilenum,email);
		//bookingPage.clickOnContinue();
	}
	@DataProvider(name="bookingDetails")
	public Object[][] supplyTestData(){	
	 Object[][] data =Utility.getTestDataFromExcel("Flightbooking");
	return data;
	}
}
