package herokuQA.pagesTests;

import herokuQA.core.BaseTest;
import herokuQA.pages.HomePage;
import herokuQA.pages.MultipleWindowsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultipleWindowsTests extends BaseTest {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).multipleWindows();
    }

    @Test
    public void multipleWindowsPositiveTest(){
        new MultipleWindowsPage(app.driver, app.wait)
                .clickByClickHereAndSwitchNewTab()
                .verifySwitching()
                .switchToStartPage();
    }
}
