package herokuQA.pagesTests;

import herokuQA.core.BaseTest;
import herokuQA.pages.DropdownPage;
import herokuQA.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DropDownTests extends BaseTest {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).dropDown();
    }

    @Test
    public void selectDropdownOptionByNamePositiveTest(){
        String option = "Option 1";
        new DropdownPage(app.driver, app.wait)
                .selectOptionByText(option)
                .verifySelectedOption(option);
    }

    @Test
    public void selectDropdownOptionByValuePositiveTest(){
        String option = "2";
        new DropdownPage(app.driver, app.wait)
                .selectOptionByValue(option)
                .verifySelectedOption(option);
    }

    @Test
    public void selectDropdownOptionByIndexPositiveTest(){
        int index = 2;
        String option = "Option " + index;
        new DropdownPage(app.driver, app.wait)
                .selectOptionByIndex(index)
                .verifySelectedOption(option);
    }

    @Test
    public void selectDropdownOptionByIndexDefaultPositiveTest(){
        new DropdownPage(app.driver, app.wait)
                //  .selectOptionByIndex(0)
                .verifySelectedOption("Please select an option");
    }

}
