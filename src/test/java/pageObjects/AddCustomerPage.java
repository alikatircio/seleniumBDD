package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

    public WebDriver webDriver;

    public AddCustomerPage(WebDriver driver) {

        webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    By linkCustomersMenu = By.xpath("//li[contains(@class, 'treeview')]//span[text()='Customers' and not(@class)]");
    By getLinkCustomersMenuItem = By.xpath("//li[contains(@class, 'treeview')]//span[text()='Customers' and @class]");
    By btnAddNew = By.xpath("//a[contains(@class, 'bg-blue')]");
    @FindBy(id = "Email")
    @CacheLookup
    WebElement txtEmail;
    By txtPassword = By.id("Password");
    By txtName = By.id("FirstName");
    By txtSurname = By.id("LastName");
    By genderMale = By.id("Gender_Male");
    By genderFemale = By.id("Gender_Female");
    By txtBirthdate = By.id("DateOfBirth");
    By txtCompanyName = By.id("Company");
    By txtCustomerRole = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
    By listItemAdministrator = By.xpath("//ul[@id='SelectedCustomerRoleIds_listbox']//li[@data-idx='0']");
    By listItemRegistered = By.xpath("//li[contains(text(), 'Registered')]");
    By listItemGuests = By.xpath("//li[contains(text(), 'Guests')]");
    By listItemVendors = By.xpath("//li[contains(text(), 'Vendors')]");
    By managerOfVendor = By.id("VendorId");
    By txtAdminContext = By.id("AdminComment");
    By btnSave = By.name("save");

    //Actions Methods

    public String getPageTitle() {

        return webDriver.getTitle();
    }

    public void clickOnCustomersMenu() {

        webDriver.findElement(linkCustomersMenu).click();
    }

    public void clickOnCustomersMenuItem() {

        webDriver.findElement(getLinkCustomersMenuItem).click();
    }

    public void clickOnAddNew() {

        webDriver.findElement(btnAddNew).click();
    }

    public void setTxtEmail(String email) {

        txtEmail.sendKeys(email);
    }

    public void setTxtPassword(String password) {

        webDriver.findElement(txtPassword).sendKeys(password);
    }

    public void setTxtName(String name) {

        webDriver.findElement(txtName).sendKeys(name);
    }

    public void setTxtSurname(String surname) {

        webDriver.findElement(txtSurname).sendKeys(surname);
    }

    public void setTxtCustomerRole(String role) throws InterruptedException {

        if (!role.equals("Registered")) {
            webDriver.findElement(By.xpath("//span[@class='k-icon k-delete']")).click();
        }
        webDriver.findElement(txtCustomerRole).click();
        WebElement listItem;
        Thread.sleep(3000);
        if (role.equals("Administrator"))
            listItem = webDriver.findElement(listItemAdministrator);
        else if (role.equals("Guests"))
            listItem = webDriver.findElement(listItemGuests);
        else if (role.equals("Registered"))
            listItem = webDriver.findElement(listItemRegistered);
        else if (role.equals("Vendors"))
            listItem = webDriver.findElement(listItemVendors);
        else
            listItem = webDriver.findElement(listItemGuests);

        listItem.click();

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click;", listItem);
    }

    public void setManagerOfVendor(String value) {

        Select drp = new Select(webDriver.findElement(managerOfVendor));
        drp.selectByVisibleText(value);
    }

    public void setGender(String gender) {

        if (gender.equalsIgnoreCase("male"))
            webDriver.findElement(genderMale).click();
        else if (gender.equalsIgnoreCase("female"))
            webDriver.findElement(genderFemale).click();
        else
            webDriver.findElement(genderMale).click();
    }

    public void setBirthdate(String birthdate) {

        webDriver.findElement(txtBirthdate).sendKeys(birthdate);
    }

    public void setTxtCompanyName(String companyName) {

        webDriver.findElement(txtCompanyName).sendKeys(companyName);
    }

    public void setTxtAdminContext(String context) {

        webDriver.findElement(txtAdminContext).sendKeys(context);
    }

    public void clickOnSaveButton() {

        webDriver.findElement(btnSave).click();
    }

}
