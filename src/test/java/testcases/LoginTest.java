package testcases;

import base.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.TestBase;

public class LoginTest extends TestBase {

    //public SoftAssert soft= new SoftAssert();

    @Test(priority=1)
    public void EmptyCredentailValidation() {

        log.info("Both Field Empty");
        Helper.login("", "");

        WebElement Error = driver.findElement(By.tagName(OR.getProperty("error_tagName")));
        String Actual_Empty_Mesage = Error.getText();
        String Expected_Message = "Invalid username and password!";
        soft.assertEquals(Actual_Empty_Mesage, Expected_Message, "Empty field Credential Message not Equal");
        log.info("Both Field Empty");
    }


    @Test(priority=2)
    public void verifyErrorMessageShowOnInvalidLoginCredential() {
        log.info("Invalid Credential");
        Helper.login("hammad", "12345");
        WebElement Error = driver.findElement(By.tagName(OR.getProperty("error_tagName")));
        String ActualErrorMessage = Error.getText();
        String ExpectedErrorMessage = "Invalid username and password!";
        soft.assertEquals(ActualErrorMessage, ExpectedErrorMessage, "Invalid Credential Message not Equal");
        log.info("Invalid credential Message show");
    }

    @Test(priority=3)
    public void loginSuccessFully() {
        log.info("insite login");
        Helper.login("", "");
        log.info("Login successfully");
        Assert.assertTrue(isElementPresent(By.id(OR.getProperty("loginBtn_id")), "Login Not Successful"));
    }


}
