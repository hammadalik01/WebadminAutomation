package testcases;

import base.Helper;
import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class DataSearch extends TestBase{

    @Test(priority=42)
    public void VerifyDataSearchForBothMessageAndCallLogs(){
        //Go to Data Search
        Helper.DataSearchForUsersActivity("CALL LOGS AND MESSAGES");
        log.info("admin get CSV data file of Call Logs and Message ");
    }

    @Test(priority=43)
    public void VerifyDataSearchForMessage(){
        //Go to Data Search
        Helper.DataSearchForUsersActivity("MESSAGES");
        log.info("admin get CSV data file of Message ");
    }

    @Test(priority=44)
    public void VerifyDataSearchForCalls(){
        //Go to Data Search
        Helper.DataSearchForUsersActivity("CALL LOGS");
        log.info("admin get CSV data file of Call Logs");
    }

}
