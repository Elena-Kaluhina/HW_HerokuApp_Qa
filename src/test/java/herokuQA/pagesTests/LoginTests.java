package herokuQA.pagesTests;

import herokuQA.core.BaseTest;
import herokuQA.pages.HomePage;
import herokuQA.pages.LoginPage;
import herokuQA.utils.DataProviders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).formAuthentication();
    }

    @Test
    public void loginPositiveTest(){
        new LoginPage(app.driver,app.wait)
                .enterLoginData("tomsmith","SuperSecretPassword!")
                .clickOnLoginButton()
                .verifyLogoutButtonVisible();
    }

    @Test(dataProvider = "loginDataPositiveProvider", dataProviderClass = DataProviders.class)
    public void loginProviderPositiveTest (String username, String password) {
        new LoginPage(app.driver, app.wait)
                .enterLoginData(username, password)
                .clickOnLoginButton()
                .verifyLogoutButtonVisible();
    }


    @Test(dataProvider = "loginDataNegativeProvider", dataProviderClass = DataProviders.class)
    public void loginProviderNegativeTest (String username, String password) {
        new LoginPage(app.driver, app.wait)
                .enterLoginData(username, password)
                .clickOnLoginButton()
                .verifyLoginButtonVisible();
    }


}
