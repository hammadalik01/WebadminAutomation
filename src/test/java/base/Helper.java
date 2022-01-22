package base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Helper extends TestBase{


    public static void login(String user, String password) {
        driver.findElement(By.name(OR.getProperty("loginUsername_name"))).sendKeys(user);
        driver.findElement(By.name(OR.getProperty("loginPassword_name"))).sendKeys(password);
        driver.findElement(By.xpath(OR.getProperty("loginButton_xpath"))).click();
    }

    public static void DataSearchForUsersActivity(String userActivity) {
        driver.findElement(By.xpath(OR.getProperty("dataSearchLink_xpath"))).click();
        driver.findElement(By.id(OR.getProperty("startDate_id"))).clear();
        driver.findElement(By.id(OR.getProperty("startDate_id"))).sendKeys("2021-12-01 00:00");
        driver.findElement(By.id(OR.getProperty("startDate_id"))).sendKeys(Keys.ENTER);
        driver.findElement(By.id(OR.getProperty("phoneNumber_id"))).click();
        driver.findElement(By.id(OR.getProperty("phoneNumber_id"))).clear();
        driver.findElement(By.id(OR.getProperty("phoneNumber_id"))).sendKeys("33780909051");
        driver.findElement(By.id(OR.getProperty("searchBtn_id"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("exportCsv_cssSelector"))).click();

        //Select Activity CSV file
        WebElement SelectActivity = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty("dataType_id"))));
        Select UsersActivity = new Select(SelectActivity);
        UsersActivity.selectByVisibleText(userActivity);
        driver.findElement(By.id(OR.getProperty("goConfirmation_id"))).click();
        driver.findElement(By.cssSelector("html")).sendKeys(Keys.ENTER);
    }


    public static void lawfulCallInterception(String startDate , String endDate , String bucketName) {

        //Insert call interception data for validation of Previous
        Select ObjectType = new Select(driver.findElement(By.id(OR.getProperty("objectType_id"))));
        ObjectType.selectByValue("USER_ID");
        driver.findElement(By.name(OR.getProperty("objectType_name"))).sendKeys("1637850552522-0242f6547081-0001");
        driver.findElement(By.name(OR.getProperty("startDateLawFullCall_name"))).click();
        driver.findElement(By.name(OR.getProperty("startDateLawFullCall_name"))).clear();
        driver.findElement(By.name(OR.getProperty("startDateLawFullCall_name"))).sendKeys(startDate);
        driver.findElement(By.name(OR.getProperty("startDateLawFullCall_name"))).sendKeys(Keys.ENTER);
        driver.findElement(By.name(OR.getProperty("endDateLawFullCall_name"))).click();
        driver.findElement(By.name(OR.getProperty("endDateLawFullCall_name"))).clear();
        driver.findElement(By.name(OR.getProperty("endDateLawFullCall_name"))).sendKeys(endDate);
        driver.findElement(By.name(OR.getProperty("endDateLawFullCall_name"))).sendKeys(Keys.ENTER);
        driver.findElement(By.name(OR.getProperty("bucketName_name"))).sendKeys(bucketName);
        driver.findElement(By.id(OR.getProperty("addCallInterception_id"))).click();
        Actions act = new Actions(driver);
        //DoubleClick on element
        WebElement ele = driver.findElement(By.id(OR.getProperty("addCallInterception_id")));
        act.doubleClick(ele).perform();
    }


    public static void SupportMessageFormValidation(String englishText , String frenchText , String phoneNumber) {
        //Testing Support Message In English
        WebElement EnglishText = driver.findElement(By.id(OR.getProperty("englishTextField_id")));
        EnglishText.clear();
        EnglishText.sendKeys(englishText);
        //Enter French Text
        //Testing Support Message In French
        WebElement FrenchText = driver.findElement(By.id(OR.getProperty("frenchTextField_id")));
        FrenchText.clear();
        FrenchText.sendKeys(frenchText);
        //Enter Phone
        WebElement PhoneNumber= driver.findElement(By.id(OR.getProperty("phones_id")));
        PhoneNumber.clear();
        PhoneNumber.sendKeys(phoneNumber);
        //Click on Submit button
        driver.findElement(By.id(OR.getProperty("submit_id"))).click();
    }

    public static void BlockToken(String BlockMobileTokenID , String BlockUserNotes ) {
        //Check validation on Mobile Token
        driver.findElement(By.id(OR.getProperty("blockMobileToken_id"))).sendKeys(BlockMobileTokenID);
        driver.findElement(By.id(OR.getProperty("blockToken_id"))).click();
        //Confirmation Text
        driver.findElement(By.id(OR.getProperty("blockTokenUserNote_id"))).sendKeys(BlockUserNotes);
        //Click on Go button
        WebElement GoButton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty("confirmationModalGoBlockTokenBtn_id"))));
        GoButton.click();
    }

    //LawfulMessageInterceptionForm
    public static void LawfulMessageInterceptionForm(String referenceId , String RequesterEmail , String phoneNumber ) {
        driver.findElement(By.id(OR.getProperty("LegalRequestReference_ID"))).sendKeys(referenceId);
        driver.findElement(By.id(OR.getProperty("legalRequesterEmail_id"))).sendKeys(RequesterEmail);

        driver.findElement(By.name(OR.getProperty("phoneNumberInterception_name"))).sendKeys(phoneNumber);

        //start date
        driver.findElement(By.name(OR.getProperty("interceptionStartDate_name"))).sendKeys("2021-12-01 00:00");

        //End date
        driver.findElement(By.name(OR.getProperty("interceptionEndDate_name"))).sendKeys("2021-12-20 23:59");
        driver.findElement(By.name(OR.getProperty("interceptionEndDate_name"))).sendKeys(Keys.ENTER);

        //Message Interception
        driver.findElement(By.id(OR.getProperty("addMessageInterception_id"))).click();
    }

    public static void ExtendNumberExpirationDate(int SelectNumber, String planType) {
        Select SelectNationalNumber = new Select(driver.findElement(By.id(OR.getProperty("selectExtendNumber_id"))));
        SelectNationalNumber.selectByIndex(SelectNumber); //2
        driver.findElement(By.id(OR.getProperty("expirationMonthsForExtend_id"))).clear();
        driver.findElement(By.id(OR.getProperty("expirationMonthsForExtend_id"))).sendKeys("1");
        Select PlanTypeLarge = new Select(driver.findElement(By.id(OR.getProperty("planTypeForNumberExtends_id"))));
        PlanTypeLarge.selectByValue(planType);  //Large
        driver.findElement(By.xpath(OR.getProperty("extendNumberSubmitButton_xpath"))).click();
        WebElement SuccessMsgForExtendsNationalNumber  = driver.findElement(By.xpath(OR.getProperty("numberExtendSuccessFullMessage_xpath")));
        Assert.assertEquals(SuccessMsgForExtendsNationalNumber.getText(), "Success", "Country Sim not added successfully");
    }

    public static void RefillPlanForNumbers(int SelectNumber ) {
        Select SelectRefillForInternational = new Select(driver.findElement(By.id(OR.getProperty("selectNationalNumber_id"))));
        SelectRefillForInternational.selectByIndex(SelectNumber);
        driver.findElement(By.id(OR.getProperty("submitRefillPlan_id"))).click();
        WebElement InterNationalNumberRefillPlan = driver.findElement(By.xpath(OR.getProperty("SuccessTextForInternationalCountry_xpath")));
        Assert.assertEquals(InterNationalNumberRefillPlan.getText(), "Success", "Country Sim not added successfully");
    }


    public static void creatUser(String firstname, String lastname, String password, String emailaddress) {
        driver.findElement(By.name(OR.getProperty("firstname_name"))).clear();
        driver.findElement(By.name(OR.getProperty("firstname_name"))).sendKeys(firstname);
        driver.findElement(By.name(OR.getProperty("lastname_name"))).clear();
        driver.findElement(By.name(OR.getProperty("lastname_name"))).sendKeys(lastname);
        WebElement input = driver.findElement(By.xpath(OR.getProperty("nationalCountryIso_xpath")));
        input.click();
        input.sendKeys("FRA");
        WebElement option = driver.findElement(By.xpath(OR.getProperty("franceCode_xpath")));
        String Value = option.getAttribute("value");
        input.clear();
        input.sendKeys(Value);
        driver.findElement(By.name(OR.getProperty("password_name"))).clear();
        driver.findElement(By.name(OR.getProperty("password_name"))).sendKeys(password);
        driver.findElement(By.name(OR.getProperty("emailName_name"))).clear();
        driver.findElement(By.name(OR.getProperty("emailName_name"))).sendKeys(emailaddress);
        driver.findElement(By.xpath(OR.getProperty("submittedButton_xpath"))).click();
    }


    public static void assertValidationMessage(WebElement element, String validationMessage, String errorMessageForFailure) {
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(element.getAttribute("validationMessage"), validationMessage, errorMessageForFailure);
    }


    public static void addCategory(String categoryName ,String nationalCountry,  String nationalXpath ,String planOption , String virtualFirstNumber) {
        //Add International Category For user
        WebElement internationalCategoryName= wait.until(ExpectedConditions.elementToBeClickable(By.name(OR.getProperty("category_name"))));
        internationalCategoryName.clear();
        internationalCategoryName.sendKeys(categoryName);
        WebElement Expiration = driver.findElement(By.name(OR.getProperty("expirationMonth_name")));
        Expiration.sendKeys(Keys.UP);

        //Select france as a National Country
        driver.findElement(By.cssSelector(OR.getProperty("selectCountryText_CssSelector"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("multiSelectSearch_CssSelector"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("multiSelectSearch_CssSelector"))).clear();
        driver.findElement(By.cssSelector(OR.getProperty("multiSelectSearch_CssSelector"))).sendKeys(nationalCountry);
        driver.findElement(By.xpath("//input[@value = '"+nationalXpath+"']")).click();

        //Select plan type
        WebElement planType = wait.until(ExpectedConditions.elementToBeClickable(By.name(OR.getProperty("planType_name"))));
        Select plans = new Select(planType);
        plans.selectByValue(planOption);

        //NumberType
        Select numberType = new Select(driver.findElement(By.name(OR.getProperty("numberType_name"))));
        numberType.selectByVisibleText("REGULAR");

        //Select a Number
        WebElement virtualNumber = wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty("virtualNumber_id"))));
        virtualNumber.sendKeys(virtualFirstNumber);
        WebElement SelectNumber = wait.until(ExpectedConditions.elementToBeClickable(By.id("ui-id-2")));
        //String expectedNumber = SelectNumber.getText();
        //System.out.println(expectedNumber);
        SelectNumber.click();
        driver.findElement(By.xpath(OR.getProperty("categorySubmitButton_xpath"))).click();

    }











}
