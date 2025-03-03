package herokuQA.pagesTests;

import herokuQA.core.BaseTest;
import herokuQA.pages.ContextMenuPage;
import herokuQA.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContextMenuTests extends BaseTest {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).contextMenu();
    }

    @Test
    public void rightClickOnBoxAndAlertPositiveTest(){
        new ContextMenuPage(app.driver,app.wait)
                .rightClickOnBox()
                .verifyAlertText("You");
    }
}
