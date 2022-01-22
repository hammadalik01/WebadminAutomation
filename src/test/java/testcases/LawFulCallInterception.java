package testcases;

import base.Helper;
import base.TestBase;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LawFulCallInterception extends TestBase {


    @Test(priority=36)
    public void VerifyUserLandOnLegalRequest(){
        //Go to Legal Request
        driver.findElement(By.xpath(OR.getProperty("legalRequestLink_xpath"))).click();
        String ActualTitleOfPage= driver.getTitle();
        soft.assertEquals(ActualTitleOfPage, "Police Report", "User Land on a Wrong Page not on Police Report section");
        log.info("Admin Land on Lawful Call Interception section");
    }


    @Test(priority=37)
    public void VerifyDateErrorMessageShowInCallInterception(){
        //Go to Lawful Call Interception
        WebElement LawfulCallInterception =new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("lawfulCallInterceptionLink_xpath"))));
        LawfulCallInterception.click();
        Helper.lawfulCallInterception("2021-12-25 00:00" , "2021-12-23 08:00" ,"Testing One");
        WebElement ErrorMessage = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("add-call-interception-result")));
        soft.assertEquals(ErrorMessage.getText(), "Interception start date can't be before interception end date", "No validation on Date if End Date is higher than Start Date");
        log.info("Admin enter start date before interception end date");

    }


    @Test(priority=38)
    public void VerifyDateValidationShowOnPreviousRecord(){

        Helper.lawfulCallInterception("2021-12-17 00:00" , "2021-12-17 08:00" ,"Testing One");
        WebElement ErrorMessage = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("add-call-interception-result")));
        soft.assertEquals(ErrorMessage.getText(), "Interception start date can't be before current date", "No validation on Date if start date can't be before current date");
        log.info("Admin enter Interception start date before current date");

    }

    @Test(priority=39)
    public void VerifyCallInterceptionRequestAddedSuccessfully(){

        Helper.lawfulCallInterception("2021-12-25 00:00" , "2021-12-25 08:00" ,"Testing One");
        WebElement ErrorMessage = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("add-call-interception-result")));
        soft.assertEquals(ErrorMessage.getText(), "Call interception request was successfully registered with ID 1640435066953-024231b7794f-0001", "Call interception request was not added successfully ");
        log.info("Admin Add Call Interception Request");

    }


    @Test(priority=40)
    public void VerifyActiveCallInterceptionSuccessfully(){

        WebElement ActiveHeading = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("active_xpath"))));
        WebElement ActionBtnTxt = driver.findElement(RelativeLocator.with(By.tagName("button")).below(ActiveHeading));

        if (ActionBtnTxt.equals("Enabled")) {
            driver.findElement(By.xpath(OR.getProperty("active_xpath"))).click();
            log.info("Call Interception Enabled Successfully ");
        }
        else {
            ActionBtnTxt.click();
            log.info("Call Interception Disabled  Successfully ");
        }
    }

    @Test(priority=41)
    public void VerifyInactiveCallInterceptionSuccessfully(){

        WebElement ActiveHeading = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("active_xpath"))));
        WebElement ActionBtnTxt = driver.findElement(RelativeLocator.with(By.tagName("button")).below(ActiveHeading));

        if (ActionBtnTxt.equals("Enabled")) {
            driver.findElement(By.xpath(OR.getProperty("active_xpath"))).click();
            log.info("Call Interception Enabled Successfully ");
        }
        else {
            ActionBtnTxt.click();
            log.info("Call Interception Disabled  Successfully");
        }
    }

}
