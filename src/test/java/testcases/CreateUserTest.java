package testcases;

import base.Helper;
import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Random;

public class CreateUserTest extends TestBase{

    @Test(priority=4)
    public void verifyUserLandToCreateUserSection(){
        //Click on UserManagement
        WebElement UserManagement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("userManagementMenu_xpath"))));
        UserManagement.click();

        //Click on Create User
        WebElement CreateUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("createUser_xpath"))));
        CreateUser.click();
        String CurrentUrl=driver.getCurrentUrl();
        String ExpectedUrl= "https://staging-admin.onoffapp.net/create-user";
        System.out.println(driver.getCurrentUrl());
        Assert.assertEquals(CurrentUrl , ExpectedUrl , "user Not land on Create User Section" );
        log.info("User is on create a user Section");
    }

    @Test(priority=5)
    public void verifyRequiredValidationOnCreateUser(){

        driver.findElement(By.xpath(OR.getProperty("submittedButton_xpath"))).click();
        WebElement firstname = driver.findElement(By.name(OR.getProperty("firstname_name")));

        Helper.assertValidationMessage(firstname, "Please fill out this field", "No validation on FirstName Filed");

        //Last Name validation
        driver.findElement(By.name(OR.getProperty("firstname_name"))).sendKeys("hammad");
        WebElement lastname = driver.findElement(By.name(OR.getProperty("lastname_name")));
        Helper.assertValidationMessage(lastname, "Please fill out this field", "No validation on Last name Filed");
        driver.findElement(By.xpath(OR.getProperty("submittedButton_xpath"))).click();


        //password validation
        driver.findElement(By.name(OR.getProperty("lastname_name"))).sendKeys("QA");
        WebElement password = driver.findElement(By.name(OR.getProperty("password_name")));
        Helper.assertValidationMessage(password, "Please fill out this field", "No validation on password Filed");
        driver.findElement(By.xpath(OR.getProperty("submittedButton_xpath"))).click();


        //Empty Email validation
        driver.findElement(By.name(OR.getProperty("password_name"))).sendKeys("Tester01");
        WebElement email = driver.findElement(By.name(OR.getProperty("emailName_name")));
        Helper.assertValidationMessage(email, "Please fill out this field", "No validation on Empty email Filed");
        driver.findElement(By.xpath(OR.getProperty("submittedButton_xpath"))).click();

        //Validation on Invalid email address
        driver.findElement(By.name(OR.getProperty("password_name"))).sendKeys("Tester01");
        WebElement invalidEmail = driver.findElement(By.name(OR.getProperty("emailName_name")));
        invalidEmail.sendKeys("hammad");
        Helper.assertValidationMessage(invalidEmail, "Please include an '@' in the email address. 'hammad' is missing an '@'.", "No validation on invalid Email");
        driver.findElement(By.xpath(OR.getProperty("submittedButton_xpath"))).click();

        //Validation On National Country
        driver.findElement(By.name(OR.getProperty("emailName_name"))).sendKeys("hammad@onoffapp.com");
        driver.findElement(By.xpath(OR.getProperty("submittedButton_xpath"))).click();
        WebElement nationalCountryValidation = driver.findElement(By.xpath(OR.getProperty("selectNationalCountry_xpath")));
        soft.assertEquals(nationalCountryValidation.getText(), "Validation error occurred at: nationalCountryIsoCode", "No validation on national country");
        driver.findElement(By.xpath(OR.getProperty("submittedButton_xpath"))).click();
        log.info("Validation on creating a user");
  }




    @Test(priority=6)
    public void verifyEmailAlreadyInUse(){
        //Email Already in use
        Helper.creatUser("test first Name", "test","Tester01","hammad@onoffapp.com");
        WebElement Error = driver.findElement(By.xpath("//div[contains(text(),'Email in use: hammad@onoffapp.com')]"));
        Assert.assertEquals(Error.getText() , "Email in use: hammad@onoffapp.com" , "User Created successfully with email address which is already in use" );
        log.info("User Registered with email address which is already in use");

    }




    @Test (priority=7 , dataProvider="getEmployeeData")
    public void verifyEmailIstNotAllow(String Email){
        //Insert data for create a user
        Helper.creatUser("First Test","last Test","Tester01",Email);
        Assert.assertTrue(isElementPresent(By.xpath("//*[contains(text(),'Email is invalid')]"), "Login Not Successful"));
        log.info("User try to registered with those email address which is not allow");
    }



    @DataProvider
    public Object[][] getEmployeeData(){

        String sheetName="Add_EmployeeData";

        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        Object[][] data = new Object[rows-1][cols];

        for(int rowNum = 2 ; rowNum <= rows ; rowNum++){

            for(int colNum=0 ; colNum< cols; colNum++){

                data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum); //-2


            }
        }
        return data;
    }



    @Test(priority=8)
    public void verifySuccessfullyCreateUser(){
        Random random = new Random();
        int randNum = random.nextInt(100000);
        String RandomEmail = "hammad+"+randNum+"@onoffapp.com";
        Helper.creatUser("Test First Name","Test last Name","Tester01",RandomEmail);
        WebElement SuccessfulMessage = driver.findElement(By.partialLinkText("User hammad"));
        String ExpectedSuccessFullMessage = "User" + " " + RandomEmail + " " + "created successfully.";
        Assert.assertEquals(SuccessfulMessage.getText() , ExpectedSuccessFullMessage , "User Not Created successfully with new email address " );
        SuccessfulMessage.click();
        log.info("User Successfully registered into application");

    }

}
