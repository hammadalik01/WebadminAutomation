package extentlisteners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.TestBase;


public class ExtentManager extends TestBase{

	private static ExtentReports extent;
	public static String screenshotPath;
	public static String screenshotName;
    
	
	
	

	    public static ExtentReports createInstance(String fileName) {
	        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
	       
	        
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        //htmlReporter.config().setTheme(Theme.DARK);
	        htmlReporter.config().setDocumentTitle(fileName);
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setReportName(fileName);
	        
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	        extent.setSystemInfo("Automation Tester", "Hammad Ali Khan");
	        extent.setSystemInfo("Organization", "OnOff");
	        extent.setSystemInfo("Build no", "Web Admin panel of Onoff");
	        
	        
	        return extent;
	    }
	    
	    

	    public static void captureScreenshot() throws IOException {

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			Date d = new Date();
			screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

			FileUtils.copyFile(scrFile,new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
			
			
		}

	}
