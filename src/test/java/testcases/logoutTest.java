package testcases;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class logoutTest extends TestBase {


    @Test(priority=61)
    public void verifyUserlogout(){
        log.info("Click on Logout button ");
        driver.findElement(By.id(OR.getProperty("loginBtn_id"))).click();
        soft.assertTrue(isElementPresent(By.id(OR.getProperty("loginBtn_id")), "Login Not Successful"));
        WebElement successFullMessage = driver.findElement(By.tagName(OR.getProperty("successfullmessage_tagname")));
        soft.assertEquals(successFullMessage.getText(), "You've been logged out successfully!" ,"User not logout from application");
        log.info("Logout from application successfully");
    }




}
