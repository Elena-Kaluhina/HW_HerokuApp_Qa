package herokuQA.pagesTests;

import herokuQA.core.BaseTest;
import herokuQA.pages.HomePage;
import herokuQA.pages.HoversPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HoversTests extends BaseTest {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).clickHoversLink();
    }

    @Test
    public void HoversPositiveTest() {
        new HoversPage(app.driver, app.wait)
                .moveCursorToUser("name: user2");
    }
}
