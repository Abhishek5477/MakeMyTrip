package com.makemytrip.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	public static ExtentSparkReporter sparkReporter;//design the html report
	public static ExtentReports extentReport;//extent report is a class used to create the extent report

	public static ExtentReports generateExtentReports() {
		extentReport = new ExtentReports();
		
        String reportName=DateUtils.getTimeStamp()+".html";
		File extentReportFile = new File(System.getProperty("user.dir") + "\\test-output\\ExtentReports\\"+reportName);
		sparkReporter = new ExtentSparkReporter(extentReportFile);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("MakeMyTrip Test AUtomation Result Report");
		sparkReporter.config().setDocumentTitle("MMT Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentReport.attachReporter(sparkReporter);

		extentReport.setSystemInfo("Project Name", "MakeMyTrip");
		extentReport.setSystemInfo("Tester", "Abhishek");
		extentReport.setSystemInfo("Browser", "chrome");
		extentReport.setSystemInfo("os", "win10");

		return extentReport;

	}

}
