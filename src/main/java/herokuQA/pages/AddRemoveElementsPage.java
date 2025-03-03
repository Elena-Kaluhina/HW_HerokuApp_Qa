package herokuQA.pages;

import herokuQA.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddRemoveElementsPage extends BasePage {
    public AddRemoveElementsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(css = "button[onclick='addElement()']")
    WebElement addButton;

    @FindBy(css = "button[onclick='deleteElement()']")
    WebElement deleteButtons;

    public AddRemoveElementsPage clickAddElementButtonAndCheckAddability(int count) {
        for (int i = 0; i < count; i++) {
            click(addButton); // кликаем на "Add Element" столько раз, сколько передаем в метод
        }
        System.out.println("Number of clicks on the <Add Element> button: " + count);
        System.out.println("Number of added elements: " + numberOfElements());
        Assert.assertEquals(numberOfElements(), count, "Incorrect number of elements added!"); //сравнение числа кликов по кнопке Add и число добавленных эл-тов, возвращенное методом numberOfElements
        return this;
    }

    public AddRemoveElementsPage clickDeleteButtonsAndCheckDeletability() {

        int before = numberOfElements();
        Assert.assertTrue(before > 0, "No items to remove!");
        System.out.println("Number elements on the page before deletion: " + before);

        // удаляем элемент
        //driver.findElement(By.cssSelector("button[onclick='deleteElement()']")).click();
        click(deleteButtons);

        int after = numberOfElements();
        Assert.assertEquals(after, before - 1, "Failed to remove element!");
        System.out.println("Number elements on the page after deletion: " + after);
        return this;
    }
}
