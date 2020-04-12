package stepDefinition;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.util.Properties;


public class TestBase {

    public WebDriver driver;
    public LoginPage lp;
    public AddCustomerPage addCustomerPage;
    public SearchCustomerPage searchCustomerPage;
    public static Logger logger;
    public Properties config;

    //Created for generating random string dor unique string
    public static String randomString () {

        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }
}
