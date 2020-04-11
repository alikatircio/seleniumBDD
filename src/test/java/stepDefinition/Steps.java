package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends TestBase {

    @Given("user launch chrome browser")
    public void user_Launch_Chrome_browser() {

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers//chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        lp = new LoginPage(driver);
    }

    @When("user open url {string}")
    public void user_open_URL(String url) {

        driver.get(url);
    }

    @When("user enters email as {string} and password as {string}")
    public void user_enters_Email_as_and_Password_as(String email, String password) {

        lp.setTxtEmail(email);
        lp.setTxtPassword(password);
    }

    @When("click on login")
    public void click_on_Login()  throws InterruptedException  {

        lp.clickLogin();
        Thread.sleep(3000);
    }

    @Then("Page Title should be {string}")
    public void page_Title_should_be(String title) {

        if(driver.getPageSource().contains("Login was unsuccessful.")){

            driver.close();
            Assert.assertTrue(false);
        }else
            Assert.assertEquals(title, driver.getTitle());
    }

    @When("User click on Log out link")
    public void user_click_on_Log_out_link() throws InterruptedException {

        lp.clickLogout();
        Thread.sleep(3000);
    }

    @Then("close browser")
    public void close_browser() {

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

        String email = randomString()+"@gmail.com";
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
