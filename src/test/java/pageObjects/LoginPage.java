package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public WebDriver webDriver;

    public LoginPage (WebDriver driver) {

        webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Email")
    @CacheLookup
    WebElement txtEmail;

    @FindBy(id = "Password")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(xpath = "//input[@value='Log in']")
    @CacheLookup
    WebElement btnLogin;

    @FindBy(linkText = "Logout")
    @CacheLookup
    WebElement linkLogout;

    public void setTxtEmail (String username) {

        txtEmail.clear();
        txtEmail.sendKeys(username);
    }

    public void setTxtPassword (String password) {

        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    public void clickLogin () {

        btnLogin.click();
    }

    public void clickLogout () {

        linkLogout.click();
    }
}
