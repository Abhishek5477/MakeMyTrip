package com.makemytrip.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.Base.Base;
import com.aventstack.extentreports.Status;

import com.makemytrip.utils.Utility;


public class FlightBookingPage extends Base{
	WebDriver driver;
	
	
	By baseFare=By.xpath("//span[contains(text(),'Base Fare')]/following::span[1]");
	By taxesAndSurcharge=By.xpath("//span[contains(text(),'Taxes ')]/following::span[1]");
	By otherServices=By.xpath("//span[contains(text(),'Other Services')]/following::span[@class='fontSize14 darkText '][2]");
	By totalAmount=By.xpath("//span[contains(text(),'Total Amount')]/following-sibling::span");
	By yesRadioBtn=By.xpath("//span[contains(text(),' Secure my trip')]");
	By travellers=By.xpath("//p[text()='Booking details will be sent to']");
	By adultCheckbox=By.xpath("//button[contains(text(),'ADD NEW ADULT')]");
	By checkbox=By.xpath("//span[text()='ADULT  1']");
	By firstNameTextField=By.xpath("//input[@placeholder='First & Middle Name']");
	By lastNameTextField=By.xpath("//input[@placeholder='Last Name']");
	By maleRadioBtn=By.xpath("//label[@tabindex='0']");
	By mobileNoField=By.xpath("//input[@placeholder='Mobile No']");
	By emailField=By.xpath("//input[@placeholder='Email']");
	By confirmEmail=By.xpath("//div[@class='emailId']");
	By continueBtn=By.xpath("//button[contains(text(),'Continue')]");
	By confirmBtn=By.xpath("//button[contains(text(),'CONFIRM')]");
	By seatConfirmation=By.xpath("//button[contains(@class,' buttonBig')]");
	By yesConfirmBtn=By.xpath("//button[contains(text(),'Yes, Please')]");
	By proceedBtn=By.xpath("//button[contains(text(),'PROCEED')]");
	By reviews=By.xpath("//button[text()='CONTINUE ANYWAY']");
	By proccedToPayBtn=By.xpath("//button[text()='Proceed to pay']");
	By airlineCompany=By.xpath("//div[contains(@class,'dropdown__value-container css-1hwfws3')]");
	By flyerNo=By.xpath("//input[@placeholder='Frequent Flyer No']");
	
	
	

    

	public FlightBookingPage(WebDriver driver) {
		this.driver = driver;	
}
	
	
	public Integer getMinFlightChargeByAddingBaseFareAndTaxes() {
	Integer basePrice=Integer.valueOf(driver.findElement(baseFare).getText().replaceAll("[^0-9]", ""));	
	Integer taxes=Integer.valueOf(driver.findElement(taxesAndSurcharge).getText().replaceAll("[^0-9]", ""));
	Integer calculatedMinFare=basePrice+taxes;
	return calculatedMinFare;	
	}
	
	
	public Integer calculateTotalFare() {
      Integer totalCalculatedFare=0;
		try {
		if(Utility.isElementDisplayed(driver, otherServices)){
			Integer otherCharge=Integer.valueOf(driver.findElement(otherServices).getText().replaceAll("[^0-9]", ""));
			totalCalculatedFare=getMinFlightChargeByAddingBaseFareAndTaxes()+otherCharge;	
		}
		}
		catch(NoSuchElementException e) {
			totalCalculatedFare=getMinFlightChargeByAddingBaseFareAndTaxes();
		}
		
		     return totalCalculatedFare;
	}
	
	
	public Integer getDisplayedTotalAmount() {
		Integer totalAmt=Integer.valueOf(driver.findElement(totalAmount).getText().replaceAll("[^0-9]", ""));
		return totalAmt;
	}
	
	public void verifyTotalFare() {
		Integer totalFareDisplayed=getDisplayedTotalAmount();
		Integer totalFareAfterAdding=calculateTotalFare();
		Boolean isEqual=totalFareDisplayed.equals(totalFareAfterAdding);
		if(isEqual) {
			reportPass("The total fare value "+totalFareDisplayed+" equal "+totalFareAfterAdding+"");
		}else {
			reportFail("Total fares are not equal");
		}
	}
/*	public void fillBookingDetails(String fname,String lname,String mob,String emailid) {
		driver.findElement(yesRadioBtn).click();
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("arguments[0].scrollIntoView;", travellers);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Utility.waitForElementToBeClickable(driver, adultCheckbox);

		driver.findElement(firstNameTextField).sendKeys(fname);
		driver.findElement(lastNameTextField).sendKeys(lname);
		driver.findElement(maleRadioBtn).click();
		driver.findElement(mobileNoField).sendKeys(mob);
		driver.findElement(emailField).sendKeys(emailid);
		driver.findElement(confirmEmail).click();

	}
	

	public void clickOnContinue() {
		driver.findElement(continueBtn).click();
		driver.findElement(confirmBtn).click();
		Utility.waitForElementToBeClickable(driver,seatConfirmation);
		//continueBtn.click();
		Utility.waitForElementToBeVisible(driver, continueBtn);
		driver.findElement(yesConfirmBtn).click();
	    driver.findElement(continueBtn).click();
	    driver.findElement(continueBtn).click();
	    driver.findElement(continueBtn).click();
	    driver.findElement(continueBtn).click();
		//reviews.click();
		driver.findElement(proccedToPayBtn).click();
		
	}*/
    
}

