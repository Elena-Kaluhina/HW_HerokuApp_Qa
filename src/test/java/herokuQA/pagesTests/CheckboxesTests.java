package herokuQA.pagesTests;

import herokuQA.core.BaseTest;
import herokuQA.pages.CheckBoxesPage;
import herokuQA.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckboxesTests extends BaseTest {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).checkboxes();
    }

    @Test
    public void printCheckboxPositiveTest() {
        new CheckBoxesPage(app.driver, app.wait)
                .printCheckBox();
    }

    @Test
    public void selectCheckboxByTextPositiveTest() {
        String checkBoxName = "checkbox 1";
        new CheckBoxesPage(app.driver, app.wait)
                .selectCheckboxByText(checkBoxName)
                .verifyCheckbox(checkBoxName);
    }

    @Test
    public void selectCheckboxByTextWithActionPositiveTest() {
        String checkBoxName = "checkbox 1";
        new CheckBoxesPage(app.driver, app.wait)
                .selectCheckboxByTextWithActions(checkBoxName)
                .verifyCheckbox(checkBoxName);
    }

    @Test
    public void selectCheckboxByTextWithRobotPositiveTest(){
        String checkBoxName = "checkbox 1";
        new CheckBoxesPage(app.driver,app.wait)
                .selectCheckboxByTextWithRobot(checkBoxName)
                .verifyCheckbox(checkBoxName);
    }
}
