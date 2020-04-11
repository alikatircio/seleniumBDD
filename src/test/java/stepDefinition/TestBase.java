package stepDefinition;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;




public class TestBase {

    public WebDriver driver;
    public LoginPage lp;
    public AddCustomerPage addCustomerPage;
    public SearchCustomerPage searchCustomerPage;
    public Logger logger;

    //Created for generating random string dor unique string
    public static String randomString () {

        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }
}
