package herokuQA.pages;

import herokuQA.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropdownPage extends BasePage {
    public DropdownPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "dropdown")
    WebElement dropdown;

    public DropdownPage selectOptionByText(String optionText) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(optionText);
        return this;
    }

    public DropdownPage verifySelectedOption(String expectedText) {
        Select select = new Select(dropdown);
        WebElement selectedOption = select.getFirstSelectedOption();
        System.out.println(selectedOption.getText());
        //Assert.assertEquals(selectedOption.getText(),expectedText);
        shouldHaveText(selectedOption, expectedText,500);
        return this;
    }

    public DropdownPage selectOptionByValue(String optionValue) {
        Select select = new Select(dropdown);
        select.selectByValue(optionValue);
        return this;
    }

    public DropdownPage selectOptionByIndex(int index) {
        Select select = new Select(dropdown);
        select.selectByIndex(index);
        return this;
    }
}
