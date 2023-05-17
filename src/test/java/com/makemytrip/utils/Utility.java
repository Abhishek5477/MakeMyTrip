package com.makemytrip.utils;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.Base.Base;
import com.aventstack.extentreports.Status;

public class Utility  {
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public static Boolean isElementDisplayed(WebDriver driver,By obj) {
				try {
					return driver.findElement(obj).isDisplayed();
				} catch (NoSuchElementException e) {
					return false;
				}
	         	 }
	
	

	public static Boolean isElementEnabled(WebDriver driver,By obj) {
		try {
			return  driver.findElement(obj).isEnabled();
		} catch (NoSuchElementException e) {
			return false;
		}
     	 }
	
	
	public static void waitForElementToBeVisible(WebDriver driver,By locator) {
		  wait =new WebDriverWait(driver,Duration.ofSeconds(10));
	       wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
	}
	
	public static void waitForElementToBeClickable(WebDriver driver,By obj) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(obj)).click();
	}
	By monthYear=By.xpath("(//div[contains(@class,'Caption')])[1]/div");
	
	
	public static void selectDateFromCalendar(WebDriver driver,String month, String expectedDate,String page) {
		while (true) {

			String text = driver.findElement(By.xpath("(//div[contains(@class,'Caption')])[1]/div")).getText();
			//System.out.println(text);
              
			if (text.equals(month)) {
				break;
			} else {
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
			}
		}
		String flag="False";

		while(flag=="False") {

			if(page.equalsIgnoreCase("flight")) {
				List<WebElement> allDates = driver.findElements(By.xpath(" (//div[@class='DayPicker-Body'])[1]/child::div/child::div[@aria-disabled='false']/div/p[text()='"+expectedDate+"']"));
				for (WebElement ele : allDates) {
					String date = ele.getText();
					if (date.equals(expectedDate)) {
						ele.click();

					}
				}
				flag="True";
				break;
			}
			else if(page.equalsIgnoreCase("hotel")) {
				List<WebElement> allDates = driver.findElements(By.xpath(" (//div[@class='DayPicker-Body'])[1]/descendant::div[text()='"+expectedDate+"'][@aria-disabled='false']"));
				for (WebElement ele : allDates) {
					String date = ele.getText();
					if (date.equals(expectedDate)) {
						ele.click();

					}
				}


			}


		}

	}
	public static Object[][] getTestDataFromExcel(String sheetName) {
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\makemytrip\\testdata\\MakemytripTestData.xlsx");
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fisExcel = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fisExcel);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=0;i<rows;i++) {
			
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j=0;j<cols;j++) {
				
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;	
		
				}
				
			}
			
			
		}
		
		return data;
		
	}
	

}
