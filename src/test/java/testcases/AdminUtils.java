package testcases;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AdminUtils extends TestBase {


    //Verify status Of phone Number Which is Deleted.
    @Test(priority=59)
    public void VerifyUserLandOnViewPhoneNumberInformation(){

        //Go to Utils
        driver.findElement(By.xpath(OR.getProperty("utilLink_xpath"))).click();
        //Verify User land On Utils Section
        WebElement Utils = driver.findElement(By.xpath(OR.getProperty("viewNumberInformation_xpath")));
        soft.assertEquals(Utils.getText(), "View number information" ,"User land on the Wrong Section not Utils Section");
        log.info("User land on Phone Number Information");
    }

    @Test(priority=60)
    public void VerifyQuarantineNumberDeletedSuccessfully(){

        //Go to User Management
        driver.findElement(By.xpath(OR.getProperty("quarantineLink_xpath"))).click();

        //search French Number
        driver.findElement(By.cssSelector("span.multiselect-selected-text")).click();
        driver.findElement(By.cssSelector("input.form-control.multiselect-search")).click();
        driver.findElement(By.cssSelector("input.form-control.multiselect-search")).clear();
        driver.findElement(By.cssSelector("input.form-control.multiselect-search")).sendKeys("Fr");
        driver.findElement(By.xpath("//input[@value='FR']")).click();
        driver.findElement(By.cssSelector("div.container.col-sm-12.col-md-12")).click();
        driver.findElement(By.id(OR.getProperty("submitBtn_id"))).click();


        if (!driver.findElements(By.id(OR.getProperty("releaseNumberNotes_id"))).isEmpty()){

            driver.findElement(By.cssSelector(OR.getProperty("release_cssSelector"))).click();
            driver.findElement(By.id(OR.getProperty("releaseNumberNotes_id"))).click();
            driver.findElement(By.id(OR.getProperty("releaseNumberNotes_id"))).clear();
            driver.findElement(By.id(OR.getProperty("releaseNumberNotes_id"))).sendKeys("ok");
            WebElement GoConfirmButton = new WebDriverWait(driver, Duration.ofSeconds(40)).until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty("GoConfirmButton_id"))));
            GoConfirmButton.click();
            log.info("Admin release Phone number which are in quarantine");

        }

        else {
            driver.findElement(By.xpath("//a[contains(text(),'User Management')]")).click();

             }
    }





}
