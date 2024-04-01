package api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public ExtentReports extent;
	public static ExtentTest test;
	public static Logger logger;

	public ExtentReports extentSetup() {

		String repName = getClass().getSimpleName() + ".html";

		extent = new ExtentReports();
		String reportPath = ".//reports//" + repName;
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("Pet Store");
		reporter.config().setDocumentTitle("Pet Automation");
		reporter.config().setTheme(Theme.DARK);


		extent.attachReporter(reporter);
		extent.setSystemInfo("Automation Engineer", "Shrikrsuhna Sonkar");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("Operating System Version", System.getProperty("os.version"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		return extent;
	}

	public ExtentTest extentCreateTest(String testName) {
		test = extent.createTest(testName);
		return test;
	}

	public void extentReportOpen() throws IOException {
		Desktop.getDesktop().browse(new File(System.getProperty("user.dir") + "//reports//report.html").toURI());
	}
}
