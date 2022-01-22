package testcases;

import base.Helper;
import base.TestBase;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class UserDetailTest extends TestBase {



   public void goToUserDetailSection(){
        //driver.findElement(By.id("searchText")).sendKeys("1637850552522-0242f6547081-0001");
        //driver.findElement(By.id("searchBtn")).click();
        //driver.findElement(By.className("text-primary")).click();
        //driver.findElement(By.linkText("User")).click();
    }

    @Test(priority=9)
    public void verifyValidationOnAddCategory(){

        //Validation on Category name
        driver.findElement(By.xpath(OR.getProperty("categorySubmitButton_xpath"))).click();
        WebElement categoryName = driver.findElement(By.name(OR.getProperty("category_name")));
        soft.assertEquals(categoryName.getAttribute("validationMessage"), "Please fill out this field", "No validation on category Name Filed");

        //Validation on Expiration
        driver.findElement(By.name(OR.getProperty("category_name"))).sendKeys("National testing Number");
        WebElement expiration = driver.findElement(By.name(OR.getProperty("expirationMonth_name")));
        driver.findElement(By.xpath(OR.getProperty("categorySubmitButton_xpath"))).click();
        soft.assertEquals(expiration.getAttribute("validationMessage"), "Please fill out this field", "No validation on expiration Months Filed");


        //Validation on Select Country dropdown
        WebElement Expiration  = driver.findElement(By.name(OR.getProperty("expirationMonth_name")));
        Expiration.sendKeys(Keys.UP);
        WebElement SelectCountry = driver.findElement(By.name(OR.getProperty("countries_name")));
        driver.findElement(By.xpath(OR.getProperty("categorySubmitButton_xpath"))).click();
        soft.assertEquals(SelectCountry.getAttribute("validationMessage"), "Please select an item in the list", "No validation on country dropdown field");

        //Validation on select planType
        driver.findElement(By.cssSelector(OR.getProperty("selectCountryText_CssSelector"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("multiSelectSearch_CssSelector"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("multiSelectSearch_CssSelector"))).clear();
        driver.findElement(By.cssSelector(OR.getProperty("multiSelectSearch_CssSelector"))).sendKeys("Fr");
        driver.findElement(By.xpath("//input[@value = 'FR']")).click();


        WebElement selectPlan = driver.findElement(By.name(OR.getProperty("planType_name")));
        driver.findElement(By.xpath(OR.getProperty("categorySubmitButton_xpath"))).click();
        soft.assertEquals(selectPlan.getAttribute("validationMessage"), "Please select an item in the list", "No validation on Select plan dropdown");


        //Validation on Number type
        WebElement planType = wait.until(ExpectedConditions.elementToBeClickable(By.name(OR.getProperty("planType_name"))));
        Select plans = new Select(planType);
        plans.selectByIndex(1);
        WebElement SelectNumberType = driver.findElement(By.name(OR.getProperty("numberType_name")));
        driver.findElement(By.xpath(OR.getProperty("categorySubmitButton_xpath"))).click();
        soft.assertEquals(SelectNumberType.getAttribute("validationMessage"), "Please select an item in the list", "No validation on Select Number dropdown");

        //Validation on Number
        Select Numbertype = new Select(driver.findElement(By.name(OR.getProperty("numberType_name"))));
        Numbertype.selectByVisibleText("REGULAR");
        WebElement numberValidation = driver.findElement(By.id(OR.getProperty("virtualNumber_id")));
        driver.findElement(By.xpath(OR.getProperty("categorySubmitButton_xpath"))).click();
        soft.assertEquals(numberValidation.getAttribute("validationMessage"), "Please fill out this field", "No validation on Number field");
        log.info("check validation while user add category in user details section");

    }

    @Test(priority=9)
    public void VerifyAddNationalCategorySuccessfully(){
        Helper.addCategory("National Testing Number" ,"Fr","FR", "LARGE" ,"+33");
        //Assertion on Category added successfully
        String actualResult = driver.findElement(By.xpath("//div[contains(text(),'Success')]")).getText();
        soft.assertEquals(actualResult, "Success", "National Category Added successfully");
        log.info("Admin add national Number Successfully");
    }

    @Test(priority=10)
    public void VerifyAddInternationalCategorySuccessfully() {
        Helper.addCategory("International Testing Number", "US" , "US" ,"MEDIUM" ,"+1");
        //Assertion on category added
        String actualResult = driver.findElement(By.xpath("//div[contains(text(),'Success')]")).getText();
        soft.assertEquals(actualResult, "Success", "International Category Added successfully");
        log.info("Admin add International Number Successfully");
    }

    @Test(priority=11)
    public  void verifyValidationOnEmptyFieldOfKycManualSimNumberField(){
        //validationOnKycField
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.name(OR.getProperty("simNumber_name")))).sendKeys("");
        driver.findElement(By.id(OR.getProperty("validateSimNumberBtn_id"))).click();
        WebElement TooltipMessage= driver.findElement(By.cssSelector(OR.getProperty("validationMessage_byCssSelector")));
        soft.assertEquals(TooltipMessage.getText(), "Please enter a valid phone number", "Validation Message not show");
        log.info("check validation on KYC manual SIM validation");
    }

    @Test(priority=12)
    public void VerifyValidationOnInvalidDataOnKycManualSimNumberField(){

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.name(OR.getProperty("simNumber_name")))).sendKeys("sggfjsdgjfs");
        driver.findElement(By.id(OR.getProperty("validateSimNumberBtn_id"))).click();
        WebElement cancelButton =new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("cancelButton_xpath"))));
        cancelButton.click();
        WebElement simValidationButton =new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("validateSimNumberBtn_id"))));
        simValidationButton.sendKeys(Keys.ENTER);
        WebElement yesButton =new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("yesNumberValidationButton"))));
        yesButton.click();
        WebElement simValidationResult =new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("wrongErrorMessage_xpath"))));
        soft.assertEquals(simValidationResult.getText(), "Cannot perform SIM number validation: phone number is not valid due to incorrect format.", "Valid required validation message not show");
        log.info("check you enter invalid data On Kyc Manual SimNumber Field ");
   }

    @Test(priority=13)
    public void verifyKycManualSimNumberValidationSuccessfully(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.name(OR.getProperty("simNumber_name")))).clear();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.name(OR.getProperty("simNumber_name")))).sendKeys("33762710101");
        driver.findElement(By.id(OR.getProperty("validateSimNumberBtn_id"))).click();
        //Click on OK button
        WebElement OKButton =new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("yesNumberValidationButton"))));
        OKButton.click();
        //SuccessFullValidationMessage
        WebElement simValidationMessage =new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("simValidationMessage_id"))));
        simValidationMessage.getText();
        Assert.assertEquals(simValidationMessage.getText(), "SIM number validation was successfully performed!", "Sim not validated Successfully");
        log.info("check Sim Number validate successfully On Kyc Manual SimNumber Field ");
    }

    @Test(priority=14)
    public void verifyKycPersonalFullNameAddedSuccessfully(){

        driver.findElement(By.xpath(OR.getProperty("fullNameEditBtn_xpath"))).click();
        //FirstName
        WebElement FirstName = driver.findElement(By.xpath(OR.getProperty("fullNamePartA_xpath")));
        FirstName.clear();
        FirstName.sendKeys("Hammad");
        //LastName
        WebElement LastName =driver.findElement(By.xpath(OR.getProperty("fullNamePartB_xpath")));
        LastName.clear();
        LastName.sendKeys("Test");
        driver.findElement(By.xpath(OR.getProperty("fullNameSaveBtn_xpath"))).click();
        WebElement SuccessText = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("successText_xpath"))));
        Assert.assertEquals(SuccessText.getText(), "SUCCESS!", "Personal Full Name not added successfully");
        driver.navigate().refresh();
        WebElement kycValidationInfo =driver.findElement(By.className(OR.getProperty("validationInfo_className")));
        Assert.assertEquals(kycValidationInfo.getText(), "VALIDATED", "Sim validated Successfully");
        log.info("Admin add Full name in KYC personal details");
    }

    @Test(priority=15)
    public void verifyValidationOnKycDocumentValidationFieldEmpty(){

        //Verified Personal Data Validation.
        driver.findElement(By.xpath(OR.getProperty("kycValidationToggleBar_xpath"))).click();

        //Validation On FirstName
        driver.findElement(By.id(OR.getProperty("kycSaveBtn_id"))).click();
        WebElement KycFirstName = driver.findElement(By.name(OR.getProperty("firstName_name")));
        soft.assertEquals(KycFirstName.getAttribute("validationMessage"), "Please fill out this field.", "No validation on first field of Kyc form");
        KycFirstName.clear();
        KycFirstName.sendKeys("Test First Name");

        //Validation On lastName
        driver.findElement(By.id(OR.getProperty("kycSaveBtn_id"))).click();
        WebElement KycLastName = driver.findElement(By.name(OR.getProperty("lastName_name")));
        soft.assertEquals(KycLastName.getAttribute("validationMessage"), "Please fill out this field.", "No validation on Last field of Kyc form");
        KycLastName.clear();
        KycLastName.sendKeys("Test Last Name");


        //Validation On Birth Date
        driver.findElement(By.id(OR.getProperty("kycSaveBtn_id"))).click();
        WebElement KycBirthDate = driver.findElement(By.name(OR.getProperty("birthDate_name")));
        soft.assertEquals(KycBirthDate.getAttribute("validationMessage"), "Please fill out this field.", "No validation on Date Of Birth Filed of Kyc form");
        KycBirthDate.clear();
        KycBirthDate.sendKeys("16.04.1992");


        //Validation On BirthCity
        WebElement KycBirthCity = driver.findElement(By.name(OR.getProperty("birthPlace_name")));
        KycBirthCity.clear();
        KycBirthCity.sendKeys("karachi");

        //Validation On Birth Country
        driver.findElement(By.id(OR.getProperty("kycSaveBtn_id"))).click();
        WebElement KycBirthCountry = driver.findElement(By.name(OR.getProperty("birthCountryIsoCode_name")));
        soft.assertEquals(KycBirthCountry.getAttribute("validationMessage"), "Please select an item in the list.", "No validation on country Filed of Kyc form");
        log.info("Validation verification on KYC Documents validations form");

    }


    @Test(priority=16)
    public void VerifyPersonalDataAddedSuccessfullyInKycDocumentValidationForm() throws InterruptedException {
        //driver.findElement(By.xpath("//button[@onclick='toggleKYC()']")).click();

        WebElement KycFirstName = driver.findElement(By.name(OR.getProperty("firstName_name")));
        KycFirstName.clear();
        KycFirstName.sendKeys("Test First Name");
        WebElement KycLastName = driver.findElement(By.name(OR.getProperty("lastName_name")));
        KycLastName.clear();
        KycLastName.sendKeys("Test Last Name");
        WebElement KycBirthDate = driver.findElement(By.name(OR.getProperty("birthDate_name")));
        KycBirthDate.clear();
        KycBirthDate.sendKeys("16.04.1992");
        WebElement KycBirthCity = driver.findElement(By.name(OR.getProperty("birthPlace_name")));
        KycBirthCity.clear();
        KycBirthCity.sendKeys("karachi");
        Select SelectBirthCountry = new Select(driver.findElement(By.name(OR.getProperty("birthCountryIsoCode_name"))));
        SelectBirthCountry.selectByVisibleText("Pakistan");
        driver.findElement(By.id(OR.getProperty("kycSaveBtn_id"))).click();
        Thread.sleep(200);
        WebElement SuccessFullDataSaved = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.id("kyc-data-update-result")));
        Assert.assertEquals(SuccessFullDataSaved.getText() , "KYC data successfully updated!" , "Personal data not added successfully in KYC form" );
        log.info("Personal data added successfully in KYC document validation");
    }


    @Test(priority=17)
    public void VerifyPersonalDataEditSuccessfullyInKycDocumentValidationForm() throws InterruptedException {
        driver.findElement(By.name(OR.getProperty("firstName_name"))).sendKeys(" Edit");
        driver.findElement(By.name(OR.getProperty("lastName_name"))).sendKeys("Edit");
        WebElement KycBirthDate = driver.findElement(By.name(OR.getProperty("birthPlace_name")));
        KycBirthDate.clear();
        KycBirthDate.sendKeys("20.05.1992");
        WebElement KycBirthCity = driver.findElement(By.name(OR.getProperty("birthPlace_name")));
        KycBirthCity.clear();
        KycBirthCity.sendKeys("kabul");
        Select SelectBirthCountry = new Select(driver.findElement(By.name(OR.getProperty("birthCountryIsoCode_name"))));
        SelectBirthCountry.selectByVisibleText("Afghanistan");
        WebElement SaveButton = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(OR.getProperty("kycSaveBtn_id")))));
        SaveButton.click();
        Thread.sleep(200);
        WebElement SuccessFullDataSaved = new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.visibilityOfElementLocated(By.id("kyc-data-update-result")));
        Assert.assertEquals(SuccessFullDataSaved.getText() , "KYC data successfully updated!" , "Personal data not Edited successfully in KYC form" );
        log.info("Personal data edit successfully in KYC document validation");
    }

    //User Block Script


    @Test(priority=18)
    public  void VerifyUserBlockSuccessFully(){

        //Value of button either its block or unblock
        WebElement UnblockUserBtn = driver.findElement(By.xpath(OR.getProperty("unBlockUser_xpath")));
        WebElement BlockBtn = driver.findElement(RelativeLocator.with(By.tagName("form")).above(UnblockUserBtn));
        String BlockBtnTxt = BlockBtn.getText();

        if (BlockBtnTxt.equals("Block")) {
            //Click on cancel button for disappear the Popup
            driver.findElement(By.xpath(OR.getProperty("block_xpath"))).click();
            WebElement GO = new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("goBtnConfirmModel_id"))));
            WebElement cancelButton= driver.findElement(RelativeLocator.with(By.tagName("button")).toLeftOf(GO));
            cancelButton.click();

            //click on Go button after enter some text
            driver.findElement(By.xpath(OR.getProperty("block_xpath"))).click();
            driver.findElement(By.id(OR.getProperty("blockMessageBox_id"))).sendKeys("Automation Testing For Block the User");
            WebElement GoBtn = new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty("goBtnConfirmModel_id"))));
            GoBtn.click();

            //Get block status of User
            WebElement BlockButton = driver.findElement(By.cssSelector(OR.getProperty("unblockButton_CssSelector")));
            WebElement UserBlockStatus= driver.findElement(RelativeLocator.with(By.tagName("td")).toLeftOf(BlockButton));
            Assert.assertEquals(UserBlockStatus.getText() , "true" , "User not block successfully" );
            log.info("Admin Unblock user");
        }

        else {
            //click on Go button after enter some text
            driver.findElement(By.cssSelector(OR.getProperty("unblockButton_CssSelector"))).click();
            driver.findElement(By.id(OR.getProperty("unblockMessageBox_id"))).sendKeys("Automation Testing For Unblock the User");
            WebElement GoBtn = new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty("unblockGoBtnConfirmModel_id"))));
            GoBtn.click();

            //Get block status of User
            WebElement BlockButton = driver.findElement(By.xpath(OR.getProperty("block_xpath")));
            WebElement UserStatus= driver.findElement(RelativeLocator.with(By.tagName("td")).toLeftOf(BlockButton));
            Assert.assertEquals(UserStatus.getText() , "false" , "User not Unblock successfully" );
            log.info("Admin Block user");
        }


    }


    //User Unblock Successfully

    @Test(priority=19)
    public  void VerifyUserUnblockSuccessfully(){
        //Value of button either its block or unblock
        WebElement UnblockUserBtn = driver.findElement(By.xpath(OR.getProperty("unBlockUser_xpath")));
        WebElement BlockBtn = driver.findElement(RelativeLocator.with(By.tagName("form")).above(UnblockUserBtn));
        String BlockBtnTxt = BlockBtn.getText();

        if (BlockBtnTxt.equals("Block")) {
            //Click on cancel button for disappear the Popup
            driver.findElement(By.xpath(OR.getProperty("block_xpath"))).click();
            WebElement GO = new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("goBtnConfirmModel_id"))));
            WebElement cancelButton= driver.findElement(RelativeLocator.with(By.tagName("button")).toLeftOf(GO));
            cancelButton.click();

            //click on Go button after enter some text
            driver.findElement(By.xpath(OR.getProperty("block_xpath"))).click();
            driver.findElement(By.id(OR.getProperty("blockMessageBox_id"))).sendKeys("Automation Testing For Block the User");
            WebElement GoBtn = new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty("goBtnConfirmModel_id"))));
            GoBtn.click();

            //Get block status of User
            WebElement BlockButton = driver.findElement(By.cssSelector(OR.getProperty("unblockButton_CssSelector")));
            WebElement UserBlockStatus= driver.findElement(RelativeLocator.with(By.tagName("td")).toLeftOf(BlockButton));
            Assert.assertEquals(UserBlockStatus.getText() , "true" , "User not block successfully" );
            log.info("Admin Unblock user");
        }

        else {
            //click on Go button after enter some text
            driver.findElement(By.cssSelector(OR.getProperty("unblockButton_CssSelector"))).click();
            driver.findElement(By.id(OR.getProperty("unblockMessageBox_id"))).sendKeys("Automation Testing For Unblock the User");
            WebElement GoBtn = new WebDriverWait(driver,Duration.ofSeconds(40)).until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty("unblockGoBtnConfirmModel_id"))));
            GoBtn.click();

            //Get block status of User
            WebElement BlockButton = driver.findElement(By.xpath(OR.getProperty("block_xpath")));
            WebElement UserStatus= driver.findElement(RelativeLocator.with(By.tagName("td")).toLeftOf(BlockButton));
            Assert.assertEquals(UserStatus.getText() , "false" , "User not Unblock successfully" );
            log.info("Admin Block user");
        }
    }


    @Test(priority=20)
    public void verifySuccessfullyAnswerMethodSwitchToCallback(){

        //Value of button either its block or unblock
        WebElement answerMethod = driver.findElement(By.id(OR.getProperty("switchAnswerMethod_id")));
        String answerMethodBtn =answerMethod.getText();

        if (answerMethodBtn.equals("Switch answer method to VOIP")) {
            WebElement methodToVoipBtn= driver.findElement(By.xpath(OR.getProperty("methodToVoip_xpath")));
            methodToVoipBtn.click();
            WebElement ExpectedVoipResult = driver.findElement(By.xpath(OR.getProperty("answerMethodVoip_xpath")));
            Assert.assertEquals(ExpectedVoipResult.getText(), "VOIP", "Answer Method not switch to VOIP");
            log.info("Admin switch Answer method to Voip");

        }
        else {
            WebElement methodToCallBackBtn= driver.findElement(By.xpath(OR.getProperty("methodToCallBack_xpath")));
            methodToCallBackBtn.click();
            WebElement ExpectedCallBackResult = driver.findElement(By.xpath(OR.getProperty("answerMethodCallBack_xpath")));
            Assert.assertEquals(ExpectedCallBackResult.getText(), "CALLBACK", "Answer Method not switch to callback");
            log.info("Admin switch Answer method to callback");
        }
    }


    @Test(priority=21)
    public void verifySuccessfullyAnswerMethodSwitchToVoip(){

        //Value of button either its block or unblock
        WebElement answerMethod = driver.findElement(By.id(OR.getProperty("switchAnswerMethod_id")));
        String answerMethodBtn =answerMethod.getText();

        if (answerMethodBtn.equals("Switch answer method to VOIP")) {
            WebElement methodToVoipBtn= driver.findElement(By.xpath(OR.getProperty("methodToVoip_xpath")));
            methodToVoipBtn.click();
            WebElement ExpectedVoipResult = driver.findElement(By.xpath(OR.getProperty("answerMethodVoip_xpath")));
            Assert.assertEquals(ExpectedVoipResult.getText(), "VOIP", "Answer Method not switch to VOIP");
            log.info("Admin switch Answer method to Voip");
        }
        else {
            WebElement methodToCallBackBtn= driver.findElement(By.xpath(OR.getProperty("methodToCallBack_xpath")));
            methodToCallBackBtn.click();
            WebElement ExpectedCallBackResult = driver.findElement(By.xpath(OR.getProperty("answerMethodCallBack_xpath")));
            Assert.assertEquals(ExpectedCallBackResult.getText(), "CALLBACK", "Answer Method not switch to callback");
            log.info("Admin switch Answer method to callback");
        }

    }

    @Test(priority=22)
    public void VerifyOfferAutoRenewableDisableSuccessFully() throws InterruptedException {

        //Value of button either its block or unblock
        WebElement AutoRenewableOffer = driver.findElement(By.id(OR.getProperty("autoRenewableSwitch_id")));
        String AutoRenewableMethodBtn =AutoRenewableOffer.getText();


        if (AutoRenewableMethodBtn.equals("Disable Offer Auto-Renewable")) {

            WebElement AutoRenewableOfferButton = driver.findElement(By.xpath(OR.getProperty("disableAutoRenewable_xpath")));
            AutoRenewableOfferButton.click();

            //Click on Close Button
            WebElement GoConfirmButton = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("goBtnForRemoveOfferAutoRenewabl_id"))));
            WebElement CloseBtn = driver.findElement(RelativeLocator.with(By.tagName("button")).toLeftOf(GoConfirmButton));
            CloseBtn.click();

            //Click on Go Confirmation Button
            WebElement AutoRenewableOfferMainButton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("disableAutoRenewable_xpath"))));
            AutoRenewableOfferMainButton.click();
            WebElement ConfirmAutoRenewableBtn = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button' and text()='Go']")));
            ConfirmAutoRenewableBtn.click();
            log.info("Admin Disable Offer Auto-Renewable");
        }

        else {
            WebElement AutoRenewableOfferButton = driver.findElement(By.xpath(OR.getProperty("enableAutoRenewable_xpath")));
            AutoRenewableOfferButton.click();

            //Click on Close Button
            WebElement GoConfirmButton = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("goBtnForGrantOfferAutoRenewable_id"))));
            WebElement CloseBtn = driver.findElement(RelativeLocator.with(By.tagName("button")).toLeftOf(GoConfirmButton));
            CloseBtn.click();

            //Click on Go Confirmation Button
            WebElement AutoRenewableOfferMainButton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("enableAutoRenewable_xpath"))));
            AutoRenewableOfferMainButton.click();
            WebElement ConfirmAutoRenewableBtn = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button' and text()='Go']")));
            ConfirmAutoRenewableBtn.click();
            log.info("Admin Enable Offer Auto-Renewable");
        }
    }

    @Test(priority=23)
    public void VerifyOfferAutoRenewableEnableSuccessFully() throws InterruptedException {

        //Value of button either its block or unblock
        WebElement AutoRenewableOffer = driver.findElement(By.id(OR.getProperty("autoRenewableSwitch_id")));
        String AutoRenewableMethodBtn =AutoRenewableOffer.getText();


        if (AutoRenewableMethodBtn.equals("Disable Offer Auto-Renewable")) {

            WebElement AutoRenewableOfferButton = driver.findElement(By.xpath(OR.getProperty("disableAutoRenewable_xpath")));
            AutoRenewableOfferButton.click();

            //Click on Close Button
            WebElement GoConfirmButton = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("goBtnForRemoveOfferAutoRenewabl_id"))));
            WebElement CloseBtn = driver.findElement(RelativeLocator.with(By.tagName("button")).toLeftOf(GoConfirmButton));
            CloseBtn.click();

            //Click on Go Confirmation Button
            WebElement AutoRenewableOfferMainButton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("disableAutoRenewable_xpath"))));
            AutoRenewableOfferMainButton.click();
            WebElement ConfirmAutoRenewableBtn = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button' and text()='Go']")));
            ConfirmAutoRenewableBtn.click();
            log.info("Admin Disable Offer Auto-Renewable");
        }

        else {
            WebElement AutoRenewableOfferButton = driver.findElement(By.xpath(OR.getProperty("enableAutoRenewable_xpath")));
            AutoRenewableOfferButton.click();

            //Click on Close Button
            WebElement GoConfirmButton = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("goBtnForGrantOfferAutoRenewable_id"))));
            WebElement CloseBtn = driver.findElement(RelativeLocator.with(By.tagName("button")).toLeftOf(GoConfirmButton));
            CloseBtn.click();

            //Click on Go Confirmation Button
            WebElement AutoRenewableOfferMainButton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("enableAutoRenewable_xpath"))));
            AutoRenewableOfferMainButton.click();
            WebElement ConfirmAutoRenewableBtn = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button' and text()='Go']")));
            ConfirmAutoRenewableBtn.click();
            log.info("Admin Enable Offer Auto-Renewable");
        }
    }


    @Test(priority=24)
    public void selectSimCountryIsoSuccessfully(){
        //SIM Country
        driver.findElement(By.xpath(OR.getProperty("simCountryBtn_xpath"))).click();
        Select simCountryIso = new Select(driver.findElement(By.name(OR.getProperty("simCountryDropDown_name"))));
        simCountryIso.selectByVisibleText("France");
        driver.findElement(By.xpath(OR.getProperty("simCountrySaveBtn_xpath"))).click();
        WebElement SuccessText = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("successText_xpath"))));
        Assert.assertEquals(SuccessText.getText(), "SUCCESS!", "Country Sim not added successfully");
        log.info("Admin Add SIM Country");
    }

    @Test(priority=25)
    public void VerifyUserChangeNationalCountry() throws InterruptedException {
        driver.findElement(By.name(OR.getProperty("nationalCountryIsoCode_name"))).sendKeys("FR");
        driver.findElement(By.id(OR.getProperty("submitNationalIso_id"))).click();
        WebElement GoBtnChangeNationalCountry = new WebDriverWait(driver, Duration.ofSeconds(2000)).until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("confirmationModalGoBtnChangeNationalCountry_id"))));
        WebElement CloseBtn = driver.findElement(RelativeLocator.with(By.tagName("button")).toLeftOf(GoBtnChangeNationalCountry));
        CloseBtn.click();
        driver.findElement(By.id(OR.getProperty("submitNationalIso_id"))).click();
        driver.findElement(By.id(OR.getProperty("changeNationalCountryNote_id"))).sendKeys("Testing");
        Thread.sleep(1000);
        WebElement GoYesBtnChangeNationalCountry = new WebDriverWait(driver, Duration.ofSeconds(3000)).until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("confirmationModalGoBtnChangeNationalCountry_id"))));
        GoYesBtnChangeNationalCountry.click();
        WebElement ChangeNationalCountrySuccessText= driver.findElement(By.xpath(OR.getProperty("SuccessTextForNationalCountry_xpath")));
        Assert.assertEquals(ChangeNationalCountrySuccessText.getText(), "Success", "Country Sim not added successfully");
        log.info("Admin change user national country");

    }


    @Test(priority=26)
    public void VerifyRefillPlanForNationalNumber(){

       Helper.RefillPlanForNumbers(1);
       log.info("Admin Refill National plan");
    }


    @Test(priority=27)
    public void VerifyRefillPlanForInterNationalNumber(){
       Helper.RefillPlanForNumbers(2);
        log.info("Admin Refill International plan");
    }


    @Test(priority=28)
    public void VerifyDateOfNationalNumberExtendsSuccessfully(){

       Helper.ExtendNumberExpirationDate(1 , "LARGE");
       log.info("Admin Extend National Number Successfully");

    }

    @Test(priority=29)
    public void VerifyDataOfInternationalNumberExtendsSuccessfully(){

       Helper.ExtendNumberExpirationDate(2 , "MEDIUM");
       log.info("Admin Extend International Number Successfully");

    }

    @Test(priority=30)
    public void VerifyAddUserNotesSuccessfully(){
        driver.findElement(By.id(OR.getProperty("noteUserField_id"))).sendKeys("Add User Notes");
        driver.findElement(By.id(OR.getProperty("addNote_id"))).click();
        WebElement NoteAdded_SuccessMessage = driver.findElement(By.xpath(OR.getProperty("addNotesSuccessFullMessage")));
        Assert.assertEquals(NoteAdded_SuccessMessage.getText(), "Note added", "Country Sim not added successfully");
        log.info("User Add notes Successfully");
    }

    @Test(priority=31)
    public void DeleteUserWithReleaseANumber() throws InterruptedException {

        //Delete Number
        driver.findElement(By.id("releaseNumbers")).click();
        driver.findElement(By.id("delete")).click();
        driver.findElement(By.id("confirmationModalDeleteUser")).sendKeys("User Delete For Testing with Release a Number");
        Thread.sleep(3000);
        //Click On Go Button
        WebElement GoBtn = new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmationModalGoBtn")));
        GoBtn.click();
        log.info("Admin Delete User with release Number");

    }


}

