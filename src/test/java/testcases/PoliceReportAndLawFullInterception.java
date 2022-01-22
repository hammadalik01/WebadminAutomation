package testcases;

import base.Helper;
import base.TestBase;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class PoliceReportAndLawFullInterception extends TestBase {



    @Test(priority=32)
    public void VerifyLegalRequestForInternationalVersionAddedSuccessfully(){

        //start
        //Go to Legal Request
        driver.findElement(By.xpath(OR.getProperty("legalRequestLink_xpath"))).click();
        String ActualTitleOfPage= driver.getTitle();
        soft.assertEquals(ActualTitleOfPage, "Police Report", "User Land on a Wrong Page not on Police Report section");
        //End

        WebElement PoliceReport =new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("policeReportLink_xpath"))));
        PoliceReport.click();

        //Select International Version
        WebElement LegalHeading = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty("legal_classname"))));
        WebElement VersionBtnTxt = driver.findElement(RelativeLocator.with(By.tagName("label")).above(LegalHeading));
        VersionBtnTxt.click();

        //Legal Request For International Version.
        driver.findElement(By.name(OR.getProperty("legalRequestDate_name"))).sendKeys("2021-12-26");
        driver.findElement(By.name(OR.getProperty("legalRequestDate_name"))).sendKeys(Keys.ENTER);
        driver.findElement(By.id(OR.getProperty("legalRequesterEmail_id"))).sendKeys("hammad@onoffapp.com");
        driver.findElement(By.id(OR.getProperty("legalRequesterReply_id"))).sendKeys(Keys.ENTER);
        driver.findElement(By.name(OR.getProperty("phoneNumber_name"))).sendKeys("33780909051");
        driver.findElement(By.name(OR.getProperty("startDate_name"))).sendKeys("2021-12-01 00:00");
        driver.findElement(By.name(OR.getProperty("endDate_name"))).sendKeys("2021-12-20 23:59");
        driver.findElement(By.name(OR.getProperty("endDate_name"))).sendKeys(Keys.ENTER);
        driver.findElement(By.name(OR.getProperty("ipAddress_name"))).click();
        driver.findElement(By.name(OR.getProperty("previousEmail_name"))).click();
        driver.findElement(By.name(OR.getProperty("callLogs_name"))).click();
        driver.findElement(By.name(OR.getProperty("message_name"))).click();
        WebElement GenerateReport = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("generateReportBtn_id"))));
        GenerateReport.click();
        WebElement ReportGeneratedSuccessFullMessage = driver.findElement(By.xpath("//h3[contains(text(),'will be generated soon!')]"));
        soft.assertEquals(ReportGeneratedSuccessFullMessage.getText(), "The police report for will be generated soon!", "The police report for will be generated soon!");
        log.info("Admin Added Legal Request for International Version");

    }


    @Test(priority=33)
    public void VerifyLegalRequestForFrenchVersionAddedSuccessfully(){

        //Legal Request For French Version.
        driver.findElement(By.name(OR.getProperty("legalRequestDate_name"))).sendKeys("2021-12-26");
        driver.findElement(By.name(OR.getProperty("legalRequestDate_name"))).sendKeys(Keys.ENTER);
        driver.findElement(By.id(OR.getProperty("LegalRequestReference_ID"))).sendKeys("1012");

        //Select GIC on Requesting Authority
        WebElement RequestingAuthority = driver.findElement(By.id(OR.getProperty("RequestingAuthority_id")));
        RequestingAuthority.click();
        RequestingAuthority.sendKeys(Keys.ARROW_DOWN);
        RequestingAuthority.sendKeys(Keys.ENTER);

        //Select Cour d'Appel de Colmar on Court / Tribunal
        WebElement Court = driver.findElement(By.id(OR.getProperty("court_id")));
        Court.click();
        Court.sendKeys(Keys.ARROW_DOWN);
        Court.sendKeys(Keys.ENTER);

        //Upload File
        driver.findElement(By.id(OR.getProperty("LegalRequest_id"))).sendKeys("C:\\Users\\onoff\\Desktop\\crimnal\\no_download.PNG");

        //Requesting email
        driver.findElement(By.id(OR.getProperty("legalRequesterEmail_id"))).sendKeys("hammad@onoffapp.com");
        driver.findElement(By.id(OR.getProperty("legalRequesterReply_id"))).sendKeys(Keys.ENTER);

        //Mission codes
        driver.findElement(By.xpath(OR.getProperty("MA02_xpath"))).click();

        //List of Data Search Criteria
        driver.findElement(By.name(OR.getProperty("phoneNumber_name"))).sendKeys("33780909051");
        driver.findElement(By.name(OR.getProperty("startDate_name"))).sendKeys("2021-12-01 00:00");
        driver.findElement(By.name(OR.getProperty("endDate_name"))).sendKeys("2021-12-20 23:59");
        driver.findElement(By.name(OR.getProperty("endDate_name"))).sendKeys(Keys.ENTER);

        //Include the following Data
        driver.findElement(By.name(OR.getProperty("ipAddress_name"))).click();
        driver.findElement(By.name(OR.getProperty("previousEmail_name"))).click();

        //Communication logs
        driver.findElement(By.name(OR.getProperty("callLogs_name"))).click();
        driver.findElement(By.name(OR.getProperty("message_name"))).click();

        WebElement GenerateReport = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("generateReportBtn_id"))));
        GenerateReport.click();
        WebElement ReportGeneratedSuccessFullMessage = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.className("police-report-success")));
        soft.assertEquals(ReportGeneratedSuccessFullMessage.getText(), "The police report for 1012 will be generated soon!", "The French police report is not generated successfully");
        log.info("Admin Added Legal Request for French Version");

    }


    @Test(priority=34)
    public void VerifyLawFullMessageInterceptionAddedSuccessFully(){

        //Go to Legal Request
        driver.findElement(By.xpath(OR.getProperty("legalRequestLink_xpath"))).click();

        //Go to Lawful Message Interception
        WebElement LawFullMessageInterception =new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("lawfulMessageInterception_xpath"))));
        LawFullMessageInterception.click();
        Helper.LawfulMessageInterceptionForm("10235" , "hammad@onoffapp.com" , "33780909051");
        WebElement LawFullMessageGeneratedSuccess = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.className("police-report-success")));
        soft.assertEquals(LawFullMessageGeneratedSuccess.getText(), "The legal message interception request 10235 has been processed successfully!", "lawFull Message for French not processed successfully");
        log.info("Admin successfully submit Lawful Message Interception For french");

    }

    @Test(priority=35)
    public void VerifyLawFullMessageInterceptionProcessedSuccessFullyForInternational(){
        //Select International Version
        WebElement LegalHeading = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.className(OR.getProperty("legal_classname"))));
        WebElement VersionBtnTxt = driver.findElement(RelativeLocator.with(By.tagName("label")).above(LegalHeading));
        VersionBtnTxt.click();
        Helper.LawfulMessageInterceptionForm("10235" , "hammad@onoffapp.com" , "33780909051");
        WebElement LawFullMessageGeneratedSuccess = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.className("police-report-success")));
        soft.assertEquals(LawFullMessageGeneratedSuccess.getText(), "The legal message interception request 10235 has been processed successfully!", "lawFull Message for International Version not processed successfully");
        log.info("Admin successfully submit Lawful Message Interception For International");


    }


}
