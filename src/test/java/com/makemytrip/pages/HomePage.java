
package com.makemytrip.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.Base.Base;
import com.aventstack.extentreports.Status;
import com.makemytrip.pages.FlightInfoPage;
import com.makemytrip.utils.Utility;

public class HomePage extends Base {

	By flights = By.xpath("//li[contains(@class,'menu_Flights')]");
	By hotels = By.xpath("//li[contains(@class,'Hotels')]");
	By homeStays = By.xpath("//li[contains(@class,'Homestays')]");
	By holidayPackages = By.xpath("//li[contains(@class,'Holidays')]");
	By trains = By.xpath("//li[contains(@class,'Trains')]");
	By buses = By.xpath("//li[contains(@class,'Buses')]");
	By cabs = By.xpath("//li[contains(@class,'Cabs')]");
	By forex = By.xpath("//li[contains(@class,'Forex')]");
	By charterFlights = By.xpath("//li[contains(@class,'Charters')]");
	By activities = By.xpath("//li[contains(@class,'Activities')]");
	By oneWay = By.xpath("//li[contains(text(),'One Way')]");
	By roundTrip = By.xpath("//li[contains(text(),'Round Trip')]");
	By multiCity = By.xpath("//li[contains(text(),'Multi City')]");

	By popup = By.xpath("//span[@class='ic_circularclose_grey']");
	By popup1 = By.xpath("//i[@class='wewidgeticon we_close']");

	By fromCity = By.xpath("//label[@for='fromCity']");
	By fromTextBox = By.xpath("//input[@placeholder='From']");
	// By fromSearch = By.xpath("//p[contains(text(),'" + boardingCity + "')]");

	By toCity = By.xpath("//label[@for='toCity']");
	By toTextBox = By.xpath("//input[@placeholder='To']");

	By departure = By.xpath("//span[contains(text(),'Departure')]");
	By searchBtn = By.xpath("//a[contains(text(),'Search')]");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void closePopup() {
		 //driver.switchTo().frame("notification-frame-3177563c");
		// Utility.waitForElementToBeVisible(driver, popup1);
		// driver.switchTo().defaultContent();
		Utility.waitForElementToBeVisible(driver, popup);
	}

	public void isElementDisplayedAndEnabled() {
		SoftAssert softAssert=new SoftAssert();

		try {
			softAssert.assertTrue(Utility.isElementDisplayed(driver, flights));
			logger.log(Status.INFO, "flights is displayed");
			softAssert.assertTrue(Utility.isElementEnabled(driver, flights));
			logger.log(Status.INFO, "flights is enabled");
			softAssert.assertTrue(Utility.isElementDisplayed(driver, hotels));
			logger.log(Status.INFO, "hotels is displayed");
			softAssert.assertTrue(Utility.isElementEnabled(driver, hotels));
			logger.log(Status.INFO, "hotels is enabled");
			softAssert.assertTrue(Utility.isElementDisplayed(driver, homeStays));
			logger.log(Status.INFO, "homeStays is displayed");
			softAssert.assertTrue(Utility.isElementEnabled(driver, homeStays));
			logger.log(Status.INFO, "homeStays is enabled");
			softAssert.assertTrue(Utility.isElementDisplayed(driver, holidayPackages));
			logger.log(Status.INFO, "holidayPackages is displayed");
			softAssert.assertTrue(Utility.isElementEnabled(driver, holidayPackages));
			logger.log(Status.INFO, "holidayPackages is enabled");
			softAssert.assertTrue(Utility.isElementDisplayed(driver, trains));
			logger.log(Status.INFO, "trains is displayed");
			softAssert.assertTrue(Utility.isElementEnabled(driver, trains));
			logger.log(Status.INFO, "trains is enabled");
			softAssert.assertTrue(Utility.isElementDisplayed(driver, buses));
			logger.log(Status.INFO, "buses is displayed");
			softAssert.assertTrue(Utility.isElementEnabled(driver, buses));
			logger.log(Status.INFO, "buses is enabled");
			softAssert.assertTrue(Utility.isElementDisplayed(driver, cabs));
			logger.log(Status.INFO, "cabs is displayed");
			softAssert.assertTrue(Utility.isElementEnabled(driver, cabs));
			logger.log(Status.INFO, "cabs is enabled");
			softAssert.assertTrue(Utility.isElementDisplayed(driver, forex));
			logger.log(Status.INFO, "forex is displayed");
			softAssert.assertTrue(Utility.isElementEnabled(driver, forex));
			logger.log(Status.INFO, "forex is enabled");
			softAssert.assertTrue(Utility.isElementDisplayed(driver, charterFlights));
			logger.log(Status.INFO, "charterFlights is displayed");
			softAssert.assertTrue(Utility.isElementEnabled(driver, charterFlights));
			logger.log(Status.INFO, "charterFlights is enabled");
			softAssert.assertTrue(Utility.isElementDisplayed(driver, oneWay));
			logger.log(Status.INFO, "oneWay is displayed");
			softAssert.assertTrue(Utility.isElementEnabled(driver, oneWay));
			logger.log(Status.INFO, "oneWay is enabled");
			softAssert.assertTrue(Utility.isElementDisplayed(driver, roundTrip));
			logger.log(Status.INFO, "roundTrip is displayed");
			softAssert.assertTrue(Utility.isElementEnabled(driver, roundTrip));
			logger.log(Status.INFO, "roundTrip is enabled");
			softAssert.assertTrue(Utility.isElementDisplayed(driver, multiCity));
			logger.log(Status.INFO, "multiCity is displayed");
			softAssert.assertTrue(Utility.isElementEnabled(driver, multiCity));
			logger.log(Status.INFO, "multiCity is enabled");
			softAssert.assertTrue(Utility.isElementDisplayed(driver, fromCity));
			logger.log(Status.INFO, "fromCity is displayed");
			softAssert.assertTrue(Utility.isElementEnabled(driver, fromCity));
			logger.log(Status.INFO, "fromCity is enabled");
			softAssert.assertTrue(Utility.isElementDisplayed(driver, toCity));
			logger.log(Status.INFO, "toCity is displayed");
			softAssert.assertTrue(Utility.isElementEnabled(driver, toCity));
			logger.log(Status.INFO, "toCity is enabled");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}finally {
			softAssert.assertAll();
		}

	}

	public void fillFromField(String boardingCity) {

		try {
			driver.findElement(fromCity).click();
			driver.findElement(fromTextBox).sendKeys(boardingCity);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.log(Status.INFO, "Entered fromcity : " + boardingCity + "");
			By fromSearch = By.xpath("//p[contains(text(),'" + boardingCity + "')]");
			Utility.waitForElementToBeClickable(driver, fromSearch);
			reportPass("Successfully filled fromcity field");
			
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	public void setToField(String destinationCity) {

		try {
			driver.findElement(toCity).click();
			driver.findElement(toTextBox).sendKeys(destinationCity);
			logger.log(Status.INFO, "Entered tocit : " + destinationCity + "");
			By toSearch = By.xpath("//p[contains(text(),'" + destinationCity + "')]");
			Utility.waitForElementToBeVisible(driver, toSearch);
			reportPass("Successfully filled ToCity field");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	public void selectDepartureDate(String departureMonth, String departureDate, String pagename) {
		//driver.findElement(departure).click();
		try {
			Utility.selectDateFromCalendar(driver, departureMonth, departureDate, pagename);
			reportPass("successfully selected the date");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public FlightInfoPage submit() {
		try {
			driver.findElement(searchBtn).click();
			reportPass("successfully clicked submit button");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		return new FlightInfoPage(driver);

	}

}
