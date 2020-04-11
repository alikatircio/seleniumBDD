package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.sql.SQLOutput;
import java.util.List;

public class SearchCustomerPage {

    public WebDriver webDriver;
    public WaitHelper waitHelper;

    public SearchCustomerPage (WebDriver driver){

        webDriver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }


    @FindBy(id = "SearchEmail")
    @CacheLookup
    WebElement txtEmail;
    @FindBy(id = "search-customers")
    @CacheLookup
    WebElement btnSearch;
    @FindBy(id = "SearchFirstName")
    @CacheLookup
    WebElement txtFirstName;
    @FindBy(id = "SearchLastName")
    @CacheLookup
    WebElement txtLastName;
    @FindBy(id="SearchMonthOfBirth")
    @CacheLookup
    WebElement txtBirthMonth;
    @FindBy(id="SearchDayOfBirth")
    @CacheLookup
    WebElement txtBirthDay;
    @FindBy(id = "SearchCompany")
    @CacheLookup
    WebElement txtCompany;
    @FindBy(id = "SearchIpAddress")
    @CacheLookup
    WebElement txtIp;
    @FindBy(xpath = "//div[@class='k-multiselect-wrap k-floatwrap']")
    @CacheLookup
    WebElement txtCustomerRole;
    @FindBy(xpath = "//li[contains(text(), 'Administrators')]")
    @CacheLookup
    WebElement itemAdministrator;
    @FindBy(xpath = "//li[contains(text(), 'Registered')]")
    @CacheLookup
    WebElement itemRegistered;
    @FindBy(xpath = "//li[contains(text(), 'Guests')]")
    @CacheLookup
    WebElement itemGuests;
    @FindBy(xpath = "//li[contains(text(), 'Vendors')]")
    @CacheLookup
    WebElement itemVendors;
    @FindBy(xpath = "//table[@role='grid']")
    @CacheLookup
    WebElement tableResults;
    @FindBy(id = "customers-grid")
    @CacheLookup
    WebElement table;
    @FindBy(xpath = "//table[@id='customers-grid']//tbody/tr")
    @CacheLookup
    List<WebElement> tableRows;
    @FindBy(xpath = "//table[@id='customers-grid']//tbody/tr/td")
    @CacheLookup
    List<WebElement> tableColums;



    public void setTxtEmail (String email) {

        waitHelper.waitForElement(txtEmail, 30);
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void setTxtFirstName (String name){

        waitHelper.waitForElement(txtFirstName, 30);
        txtFirstName.clear();
        txtFirstName.sendKeys(name);
    }

    public void setTxtLastName (String lastName) {

        waitHelper.waitForElement(txtLastName, 30);
        txtLastName.clear();
        txtLastName.sendKeys(lastName);
    }

    public void clickOnSearch () {

        btnSearch.click();
        waitHelper.waitForElement(btnSearch, 30);
    }

    public int getNoOfRows () {

        return tableRows.size();
    }

    public int getNoOfColums () {

        return tableColums.size();
    }

    public boolean verifyTableEmail (String value) {

        Boolean flag = false;
        for (int i=1; i<=getNoOfRows(); i++){

            String email = table.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td[2]")).getText();
            System.out.println(email);
            if(email.equalsIgnoreCase(value))
                flag = true;
        }
        return flag;
    }

    public boolean verifyTableName (String value) {

        Boolean flag = false;
        for (int i=1; i<=getNoOfRows(); i++){

            String name = table.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td[3]")).getText();
            System.out.println(name);
            if(name.equalsIgnoreCase(value))
                flag = true;
        }
        return flag;
    }

}
