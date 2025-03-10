package herokuQA.pages;

import herokuQA.core.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ContextMenuPage extends BasePage {
    public ContextMenuPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id ="hot-spot")
    WebElement contextBox;

    //* Кликаем правой кнопкй мышки (ПКМ) на прямоугольнике
    public ContextMenuPage rightClickOnBox() {
        Actions actions = new Actions(driver);
        actions.contextClick(contextBox).perform();
        return this;
    }

    //* Проверяем тест в алерте
    public ContextMenuPage verifyAlertText(String expectedText) {
        Alert alert = driver.switchTo().alert();
        String actualText = alert.getText();
        System.out.println("actualText: " + actualText);
        Assert.assertTrue(actualText.contains("You"));
        alert.accept();
        return this;
    }


}
