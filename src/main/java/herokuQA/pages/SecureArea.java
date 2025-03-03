package herokuQA.pages;

import herokuQA.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SecureArea extends BasePage {

    public SecureArea(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(css = "a[href='/logout']")
    WebElement logoutButton;

    public SecureArea verifyLogoutButtonVisible() {
        Assert.assertTrue(logoutButton.isDisplayed(), "Failed registration...");
        return new SecureArea(driver, wait);
    }

    @FindBy(xpath = "//i[contains(text(),'Login')]")
    WebElement loginButton;

    public LoginPage verifyLoginButtonVisible() {
        Assert.assertTrue(loginButton.isDisplayed(), "Successful registration...");
        return new LoginPage(driver, wait);
    }

}
