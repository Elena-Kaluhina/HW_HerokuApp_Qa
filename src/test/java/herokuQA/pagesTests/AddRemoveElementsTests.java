package herokuQA.pagesTests;

import herokuQA.core.BaseTest;
import herokuQA.pages.AddRemoveElementsPage;
import herokuQA.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddRemoveElementsTests extends BaseTest {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).addRemoveElements();
    }

    @Test
    public void addRemoveElementsPositiveTest() {
        new AddRemoveElementsPage(app.driver, app.wait)
                .clickAddElementButtonAndCheckAddability(2)
                .clickDeleteButtonsAndCheckDeletability();
    }
}
