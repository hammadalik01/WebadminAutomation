package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import utilities.TestUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;
import utilities.MonitoringMail;
import utilities.TestConfig;


public class TestBase {


    /*
     * WebDriver, Properties,Logs - log4j,
     * Excel ,Mail,
     * ReportNG,
     */

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
    public static WebDriverWait wait;
    public SoftAssert soft = new SoftAssert();


    @BeforeSuite
    public void setUp() throws IOException {

        if (driver == null) {


            // First We load Config property.
            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
            config.load(fis);

            PropertyConfigurator.configure("./src/test/resources/properties/log.properties");
            log.info("This is the information log");
            log.info("Config file loaded !!!");
            //log.debug("Config file loaded !!!");
            log.error("Here the error logs will be printed");


            //Second We load OR property file means locators.
            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
            OR.load(fis);

            log.info("OR file loaded !!!");

        }

        if (config.getProperty("browser").equals("chrome")) {

            WebDriverManager.chromiumdriver().setup();


            driver = new ChromeDriver();
            log.info("Chrome Browser launched");
        } else if (config.getProperty("browser").equals("firefox")) {

            WebDriverManager.firefoxdriver().setup();

            driver = new FirefoxDriver();
            log.info("Firefox Browser launched");

        } else if (config.getProperty("browser").equals("IE")) {

            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
            log.info("Internet Explorer Browser launched");

        }
        driver.get(config.getProperty("testsiteurl"));

        log.debug("Navigated to : " + config.getProperty("testsiteurl"));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));





        // object of Explicit wait
        //wait = new WebDriverWait(driver, 20);

        wait = new WebDriverWait(driver,Duration.ofSeconds(30));

    }

    // User own define method for present of element
    public boolean isElementPresent(By by, String string) {

        try {

            driver.findElement(by);
            return true;

        } catch (NoSuchElementException e) {

            return false;

        }

    }

    @AfterSuite
    public void tearDown() throws AddressException, MessagingException {

        //if (driver != null) {
        //driver.quit();
        //MonitoringMail mMail = new MonitoringMail();
        //mMail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.messageBody, TestConfig.attachmentName, TestConfig.attachmentPath, TestConfig.subject);
        //}
        //Reporter.log("test execution completed !!!");
        //log.info("test execution completed !!!");
    }

}