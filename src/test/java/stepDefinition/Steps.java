package stepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Steps extends TestBase {

    @Before
    public void setup() throws IOException {

        config = new Properties();
        FileInputStream configFile = new FileInputStream("config.properties");
        config.load(configFile);


        logger = Logger.getLogger("nopCommerce"); //Added logger
        PropertyConfigurator.configure("log4j.properties");

        String browser = config.getProperty("browser");
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", config.getProperty("chromepath"));
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", config.getProperty("firefoxpath"));
                driver = new FirefoxDriver();
                break;
        }

        driver.manage().window().maximize();
        logger.info("***** Launching Browser *****");
    }

    @Given("user launch chrome browser")
    public void user_Launch_Chrome_browser() {

        lp = new LoginPage(driver);
    }

    @When("user open url {string}")
    public void user_open_URL(String url) {

        logger.info("***** Opening Url *****");
        driver.get(url);
    }

    @When("user enters email as {string} and password as {string}")
    public void user_enters_Email_as_and_Password_as(String email, String password) {

        logger.info("***** Providing login detail *****");
        lp.setTxtEmail(email);
        lp.setTxtPassword(password);
    }

    @When("click on login")
    public void click_on_Login() throws InterruptedException {

        logger.info("***** Started login *****");
        lp.clickLogin();
        Thread.sleep(3000);
    }

    @Then("Page Title should be {string}")
    public void page_Title_should_be(String title) {

        if (driver.getPageSource().contains("Login was unsuccessful.")) {

            driver.close();
            logger.info("***** Login Passed *****");
            Assert.assertTrue(false);
        } else {

            logger.info("***** Login Failed *****");
            Assert.assertEquals(title, driver.getTitle());
        }
    }

    @When("User click on Log out link")
    public void user_click_on_Log_out_link() throws InterruptedException {

        logger.info("***** Click on logout link *****");
        lp.clickLogout();
        Thread.sleep(3000);
    }

    @Then("close browser")
    public void close_browser() {

        logger.info("***** closing Browser *****");
        driver.quit();
    }

    //Customer Feature Step Defintions

    @Then("user can view dashboard")
    public void user_can_view_dashboard() {

        addCustomerPage = new AddCustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration", addCustomerPage.getPageTitle());
    }

    @When("user click on customers menu")
    public void user_click_on_customers_menu() throws InterruptedException {

        Thread.sleep(3000);
        addCustomerPage.clickOnCustomersMenu();
    }

    @When("click on customers menu item")
    public void click_on_customers_menu_item() throws InterruptedException {

        Thread.sleep(2000);
        addCustomerPage.clickOnCustomersMenuItem();
    }

    @When("click on add new button")
    public void click_on_add_new_button() throws InterruptedException {

        addCustomerPage.clickOnAddNew();
        Thread.sleep(3000);
    }

    @Then("user can view add new customer page")
    public void user_can_view_add_new_customer_page() {

        Assert.assertEquals("Add a new customer / nopCommerce administration", addCustomerPage.getPageTitle());

    }

    @When("user enters customer info")
    public void user_enters_customer_info() throws InterruptedException {

        logger.info("***** Adding new customer *****");
        String email = randomString() + "@gmail.com";
        addCustomerPage.setTxtEmail(email);
        addCustomerPage.setTxtPassword("test123");
        addCustomerPage.setTxtName("Ali");
        addCustomerPage.setTxtSurname("Katırcıo");
        addCustomerPage.setGender("male");
        addCustomerPage.setBirthdate("05/26/1993");
        addCustomerPage.setTxtCompanyName("Bilgeadam");
        addCustomerPage.setTxtCustomerRole("Guests");
        Thread.sleep(3000);
        addCustomerPage.setManagerOfVendor("Vendor 2");
        addCustomerPage.setTxtAdminContext("This a test data.");
    }

    @When("click on save button")
    public void click_on_save_button() throws InterruptedException {

        logger.info("***** Saving customer *****");
        addCustomerPage.clickOnSaveButton();
        Thread.sleep(3000);
    }

    @Then("user can view confirmation message {string}")
    public void user_can_view_confirmation_message(String successMessage) {

        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains(successMessage));
    }


    //steps for searching a customer using email id

    @When("enter customer email")
    public void enter_customer_email() {

        logger.info("***** Searching customer by email *****");
        searchCustomerPage = new SearchCustomerPage(driver);
        searchCustomerPage.setTxtEmail("admin@yourStore.com");
    }

    @When("click on search button")
    public void click_on_search_button() throws InterruptedException {

        searchCustomerPage.clickOnSearch();
        Thread.sleep(3000);
    }

    @Then("user should found email in the search table")
    public void user_should_found_email_in_the_search_table() {

        boolean status = searchCustomerPage.verifyTableEmail("admin@yourStore.com");
        Assert.assertEquals(true, status);
    }

    //steps for searching a customer using name

    @When("enter customer firstname")
    public void enter_customer_firstname() {

        logger.info("***** Searching customer by name *****");
        searchCustomerPage = new SearchCustomerPage(driver);
        searchCustomerPage.setTxtFirstName("John");
    }

    @When("enter customer lastname")
    public void enter_customer_lastname() {

        searchCustomerPage.setTxtLastName("Smith");
    }

    @Then("user should found name in the search table")
    public void user_should_found_name_in_the_search_table() {

        searchCustomerPage.verifyTableName("John Smith");
    }
}
