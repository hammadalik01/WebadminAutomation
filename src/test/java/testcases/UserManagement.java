package testcases;

import base.Helper;
import base.TestBase;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class UserManagement extends TestBase {


    @Test(priority=45)
    public void VerifyUserLandOnSupportMessageSection(){
        //Go to User Management
        driver.findElement(By.xpath(OR.getProperty("userManagement_xpath"))).click();
        log.info("User land on a User Management section");

    }


    @Test(priority=46)
    public void VerifyRequiredValidationOnEnglishText() throws InterruptedException {
        //Go to Support Message section
        driver.findElement(By.xpath(OR.getProperty("supportMessage_xpath"))).click();
        Helper.SupportMessageFormValidation("" , "Testing Support Message In French" , "33780909051");
        WebElement EnglishText = driver.findElement(By.id(OR.getProperty("englishTextField_id")));
        //Assertion On english Validation Field
        soft.assertEquals(EnglishText.getAttribute("validationMessage"), "Please fill out this field.", "No validation Error Message show");
        log.info("Validation on English Text Field Empty");

    }


    @Test(priority=47)
    public void VerifyRequiredValidationOnFrenchText() throws InterruptedException {

        Helper.SupportMessageFormValidation("Testing Support Message In English" , "" , "33780909051");
        WebElement FrenchText = driver.findElement(By.id(OR.getProperty("frenchTextField_id")));
        //Assertion On french Validation Field
        soft.assertEquals(FrenchText.getAttribute("validationMessage"), "Please fill out this field.", "No validation Error Message show");
        log.info("Validation on French Text Field Empty");

    }


    @Test(priority=48)
    public void VerifyRequiredValidationOnPhoneNumberField() throws InterruptedException {

        Helper.SupportMessageFormValidation("Testing Support Message In English" , "Testing Support Message In French" , "");
        //Assertion On phone Number Validation Field
        WebElement PhoneNumber= driver.findElement(By.id(OR.getProperty("phones_id")));
        soft.assertEquals(PhoneNumber.getAttribute("validationMessage"), "Please fill out this field.", "No validation Error Message show");
        log.info("Validation on Phones Numbers Field Empty");
    }


    @Test(priority=49)
    public void VerifySupportMessageSubmitSuccessfully(){

        Helper.SupportMessageFormValidation("Testing Support Message In English" , "Testing Support Message In French" , "33780909051");
        WebElement SupportSendSuccessFullMessage = driver.findElement(By.xpath("//div[contains(text(),'onoff messages from support sent out: 1')]"));
        //OnOff messages from support sent out: 1
        soft.assertEquals(SupportSendSuccessFullMessage.getText(),"OnOff messages from support sent out: 1" , "Message not send error occurred");
        log.info("Admin submit message successfully");
    }


    @Test(priority=50)
    public void VerifyUserLandOnBlockedTokensSection(){

        //Go to Block Token
        driver.findElement(By.xpath(OR.getProperty("blockTokenLink_xpath"))).click();
        log.info("User land on a block Token section");


    }

    @Test(priority=51)
    public void VerifyValidationOnEmptyBlockTokenField(){

        Helper.BlockToken("" , "Testing Block Functionality");
        WebElement ErrorMessage = driver.findElement(By.xpath("//div[contains(text(),'No data to save')]"));
        soft.assertEquals(ErrorMessage.getText(), "No data to save", "NO validation When Token Field is Empty");
        log.info("Validation on Empty Block Token field");

    }

    @Test(priority=52)
    public void VerifyTokenBlockedSuccessfully(){

        Helper.BlockToken("10F425BB-AFFE-4D37-898E-4FEC3F331D1E" , "Testing Block Functionality");
        WebElement SuccessMessage = driver.findElement(By.xpath("//div[contains(text(),'Success')]"));
        soft.assertEquals(SuccessMessage.getText(), "Success", "Token Not block Successfully");
        log.info("Admin Blocked Token of user successfully");
    }



    @Test(priority=53)
    public void VerifyBlockedTokenUnblockSuccessfully(){

        //Search Mobile Token
        WebElement SearchField = driver.findElement(By.id(OR.getProperty("name_id")));
        SearchField.sendKeys("10F425BB-AFFE-4D37-898E-4FEC3F331D1E");
        driver.findElement(By.xpath(OR.getProperty("blockToken_xpath"))).click();
        driver.findElement(By.name(OR.getProperty("blockCheckbox_name"))).click();
        driver.findElement(By.id(OR.getProperty("deleteBlockedBtn_id"))).click();
        log.info("Admin Blocked Token of user successfully");
    }

    @Test(priority=54)
    public void VerifyBlockTokenNotFoundInBlockedList(){

        //Search Mobile Token
        WebElement SearchField = driver.findElement(By.id(OR.getProperty("name_id")));
        SearchField.sendKeys("10F425BB-AFFE-4D37-898E-4FEC3F331D1E");
        driver.findElement(By.xpath(OR.getProperty("blockToken_xpath"))).click();
        WebElement NoRecordMessage = new WebDriverWait(driver, Duration.ofSeconds(40)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'No data to show!')]")));
        soft.assertEquals(NoRecordMessage.getText(), "No data to show!", "Record Not Deleted Successfully");
        log.info("Admin search Blocked Token in blockToken List it not found");

    }

    @Test(priority=55)
    public void VerifyValidationOnBlackDomainForm(){

        //Go to Block Token
        driver.findElement(By.xpath(OR.getProperty("blackListDomainLink_xpath"))).click();
        //validation On Domain Name
        WebElement CommentsField = driver.findElement(By.id(OR.getProperty("commentTxtField_id")));
        WebElement DomainName = driver.findElement(RelativeLocator.with(By.tagName("input")).toLeftOf(CommentsField));
        DomainName.sendKeys("");
        driver.findElement(By.xpath(OR.getProperty("blackListSubmitBtn_xpath"))).click();
        soft.assertEquals(DomainName.getText(), "Please fill out this field.", "No validation show on Domain name field");
        DomainName.sendKeys(".Gaditek");
        CommentsField.sendKeys("");
        driver.findElement(By.xpath(OR.getProperty("blackListSubmitBtn_xpath"))).click();
        soft.assertEquals(CommentsField.getText(), "Please fill out this field.", "No validation show on Comment Field");
        log.info("Admin Check validation on Black Domain Form");
    }

    @Test(priority=56)
    public void VerifyDomainBlackSuccessfully(){

        //Block Domain Successfully
        driver.findElement(By.id(OR.getProperty("commentTxtField_id"))).sendKeys("Block it for the testing only");
        driver.findElement(By.xpath("//div[@class='col-xs-5']//button[@id='searchBtn']")).click();
        WebElement SuccesfullMessage = driver.findElement(By.xpath("//div[contains(text(),'Success')]"));
        soft.assertEquals(SuccesfullMessage.getText(), "Success", "Domain is not block");
        log.info("Black Domain Added successfully");

    }

    @Test(priority=57)
    public void VerifyDuplicateMessageShowWhenUserTryToSearchDuplicateText(){


        WebElement CommentsField = driver.findElement(By.id(OR.getProperty("commentTxtField_id")));
        WebElement DomainName = driver.findElement(RelativeLocator.with(By.tagName("input")).toLeftOf(CommentsField));
        DomainName.sendKeys(".Gaditek");
        CommentsField.sendKeys("Block it for the testing only");
        driver.findElement(By.xpath(OR.getProperty("blackListSubmitBtn_xpath"))).click();
        WebElement DuplicateMessage = driver.findElement(By.xpath("//div[contains(text(),'Duplicate name .gaditek')]"));
        soft.assertEquals(DuplicateMessage.getText(), "Duplicate name .gaditek", "Duplicate Message Not show");
        log.info("Admin Search Duplicate Text its not found in list");

    }

    @Test(priority=58)
    public void VerifyDomainDeletedSuccessFully(){
        //Search Domain Name and Delete it
        driver.findElement(By.id(OR.getProperty("name_id"))).sendKeys(".Gaditek");
        driver.findElement(By.id(OR.getProperty("searchBtn_id"))).click();
        WebElement Checkbox = new WebDriverWait(driver, Duration.ofSeconds(40)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("domainCheckbox_xpath"))));
        Checkbox.click();
        driver.findElement(By.id(OR.getProperty("deleteBlockedBtn_id"))).click();
        log.info("Admin Delete Domain Successfully");
    }

}
