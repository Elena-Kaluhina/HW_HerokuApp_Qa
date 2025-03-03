package herokuQA.pages;

import herokuQA.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class CheckBoxesPage extends BasePage {
    public CheckBoxesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    @FindBy(xpath = "//form[@id='checkboxes']/input")
    List<WebElement> checkBoxes;

    public CheckBoxesPage printCheckBox() {

        for (WebElement checkBox : checkBoxes) {
            String labelText = chooseCheckBoxByName(checkBox);
            System.out.println("Check box text is: " + labelText);

        }
        return this;
    }

    private String chooseCheckBoxByName(WebElement checkBox) {

        return (String) js.executeScript("return arguments[0].nextSibling.textContent;", checkBox);
    }

    public CheckBoxesPage selectCheckboxByText(String checkBoxText) {
        String labelText;

        for (WebElement checkBox : checkBoxes) {
            labelText = chooseCheckBoxByName(checkBox);
            if (labelText.trim().equals(checkBoxText)) {
                if (!checkBox.isSelected()) {
                    checkBox.click();
                } else if (checkBox.isSelected()) {
                    System.out.println("Checkbox already selected");
                }
                return this;
            }
        }
        return this;
    }

    public CheckBoxesPage verifyCheckbox(String checkBoxText){
        for (WebElement checkBox : checkBoxes){
        String labelText = checkBox.getText();
        if (labelText.trim().equals(checkBoxText)){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeSelected(checkBox));
                Assert.assertTrue(checkBox.isSelected(), "Checkbox is not selected!");
            }
        }
        return this;
    }

    public CheckBoxesPage selectCheckboxByTextWithActions(String checkBoxText) {
        Actions actions = new Actions(driver);
        String labelText;
        for (WebElement checkBox : checkBoxes) {
            labelText = chooseCheckBoxByName(checkBox);
            if (labelText.trim().equals(checkBoxText)) {
                if (!checkBox.isSelected()) {
                    actions.moveToElement(checkBox).click().perform();
                    System.out.println("Checkbox selected: [" + labelText +"]");
                } else if (checkBox.isSelected()) {
                    System.out.println("Checkbox already selected");
                }
                return this;
            }
        }
        return this;
    }

    public CheckBoxesPage selectCheckboxByTextWithRobot(String checkBoxText) {
        String labelText;
        for (WebElement checkBox : checkBoxes) {
            labelText = chooseCheckBoxByName(checkBox);
            if (labelText.trim().equals(checkBoxText)) {
                if (!checkBox.isSelected()) {
                    try {
                        Robot robot = new Robot();
                        Actions actions = new Actions(driver);
                        actions.moveToElement(checkBox).perform();
                        robot.keyPress(KeyEvent.VK_TAB);
                        robot.keyRelease(KeyEvent.VK_TAB);
                        robot.keyPress(KeyEvent.VK_TAB);
                        robot.keyRelease(KeyEvent.VK_TAB);
                        robot.delay(100); // задержка
                        robot.keyPress(KeyEvent.VK_SPACE);
                        robot.keyRelease(KeyEvent.VK_SPACE);
                        System.out.println("Checkbox selected: [" + labelText.trim() + "]");
                    } catch (AWTException e) {
                        Assert.fail("Ошибка при работе с чекбоксом: " + checkBoxText);
                    }
                } else if (checkBox.isSelected()) {
                    System.out.println("Checkbox already selected");
                }
                return this;
            }
        }
        return this;
    }

}
