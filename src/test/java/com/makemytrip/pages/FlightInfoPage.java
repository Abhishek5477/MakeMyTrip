package com.makemytrip.pages;

import java.util.Iterator;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.Base.Base;
import com.aventstack.extentreports.Status;
import com.makemytrip.utils.Utility;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class FlightInfoPage extends Base {
    WebDriver driver;
	WebDriverWait wait;
	Integer minPrice;

	By closePopup = By.xpath("//span[contains(@class,'overlayCrossIcon')]");
	By flightFrom=By.xpath("//p[contains(text(),'Flights from ')]");
	By flightPrice = By.xpath("(//p[contains(@class,'blackText fontSize18 blackFont white-space-no-wrap')])[1]");
	By viewAllBtn = By.xpath("(//button[contains(text(),'VIEW ALL')])[1]");
	By viewPricesBtn = By.xpath("(//span[contains(text(),'View Prices')])[1]");
	By bookNowPrice = By.xpath("(//p[@class='blackText fontSize16 blackFont appendBottom5  '])[1]");
	By bookBtn = By.xpath("(//button[contains(text(),'Book Now')])[1]");

	public FlightInfoPage(WebDriver driver) {
		this.driver = driver;
	}


	public void closePopUp() {
		try {
			Assert.assertTrue(driver.findElement(flightFrom).isDisplayed());
			logger.log(Status.PASS,"Successfully navigated to flightinfopage");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		try {
			driver.findElement(closePopup).click();
			logger.log(Status.INFO, "closed the popup on flightinfopage");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	public  Integer getLowestCharges() {
	    minPrice=Integer.valueOf(driver.findElement(flightPrice).getText().replaceAll("[^0-9]", ""));
		logger.log(Status.INFO, "the minimun flight charge is:" + minPrice + "");
		return minPrice;
		}
	
	public void clickOnViewPriceBtn() {
		try {
			WebElement viewAll=driver.findElement(viewAllBtn);
			if(viewAll.isDisplayed()) {
				viewAll.click();
				logger.log(Status.PASS,"clicked on viewAll button");
			}
		}catch(NoSuchElementException e) {
	     e.printStackTrace();
		}
		
		WebElement viewPrice=driver.findElement(viewPricesBtn);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", viewPrice);
		 //viewPrice.click();
		logger.log(Status.PASS,"clicked on view price button");
	}
		
	
	
	public FlightBookingPage clickOnBookNow() {
		Integer bookPrice=Integer.valueOf( driver.findElement(bookNowPrice).getText().replaceAll("[^0-9]", ""));
		try {
			Assert.assertEquals(minPrice, bookPrice);
			reportPass("mininum flight price: " + minPrice + " equals the price at the time of booking: " + bookPrice
					+ " ");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		String parentWinID = driver.getWindowHandle();
		driver.findElement(bookBtn).click();
		logger.log(Status.PASS, "successfully selected the lowest price flight");
		
		Set<String> allWinID = driver.getWindowHandles();
		Iterator<String> itr = allWinID.iterator();
		while (itr.hasNext()) {
			String childWinID = itr.next();

			if (!parentWinID.equalsIgnoreCase(childWinID)) {
				driver.switchTo().window(childWinID);
			}
		}
		return new FlightBookingPage(driver);

	}

}
