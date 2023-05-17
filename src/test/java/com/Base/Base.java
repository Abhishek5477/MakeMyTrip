package com.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.makemytrip.utils.DateUtils;
import com.makemytrip.utils.ExtentReport;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public static  WebDriver driver;
	public  Properties prop;
	public static ExtentReports report=ExtentReport.generateExtentReports();
	public static ExtentTest logger;

	public Base() {

		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Config\\config.properties");
		FileInputStream fis;
		try {                                                      
			fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

	public void invokeBrowser(String browserName) {
		if (browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.contains("edge")) {
			//WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

	}

	public void openURL(String websiteURL) {
		try {
			logger.log(Status.INFO,"opening the website");
			driver.get(websiteURL);
			logger.log(Status.PASS,"successfully opened the : "+websiteURL+"");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public  void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
		takeScreenShot();
		
	}
	
	public  void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShot();
		Assert.fail(reportString);	
	}
	@AfterMethod
	public void flushReport() {
		report.flush();
		//driver.quit();
	}
	
	public  void takeScreenShot() {
		TakesScreenshot takeScreenShot = (TakesScreenshot)driver;
		File source = takeScreenShot.getScreenshotAs(OutputType.FILE);
		String fileName=DateUtils.getTimeStamp();
		File destination = new File(System.getProperty("user.dir") + "\\ScreenShot\\"+fileName+".png");
		try {
			FileUtils.copyFile(source, destination);
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") + "\\ScreenShot\\"+fileName+".png");
		} catch (Exception e) {
			e.getMessage();
		}	
	}
}
