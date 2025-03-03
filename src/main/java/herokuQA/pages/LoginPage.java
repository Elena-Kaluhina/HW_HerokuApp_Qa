package herokuQA.pages;

import herokuQA.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "username")
    WebElement userName;

    @FindBy(id = "password")
    WebElement userPassword;

    public LoginPage enterLoginData(String user, String password) {
        type(userName, user);
        type(userPassword, password);
        return new LoginPage(driver, wait);
    }

    @FindBy(xpath = "//i[contains(text(),'Login')]")
    WebElement loginButton;


    public SecureArea clickOnLoginButton() {
        click(loginButton);
        return new SecureArea(driver, wait);

    }
}
