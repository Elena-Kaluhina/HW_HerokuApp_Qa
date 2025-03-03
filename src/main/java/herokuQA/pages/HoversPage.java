package herokuQA.pages;

import herokuQA.core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HoversPage extends BasePage {
    public HoversPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//div[@class='figure']//img")
    List<WebElement> users;

    public HoversPage moveCursorToUser(String userName) {
        String userInfo;

        for (WebElement user : users) {
            if (!isUserTooltipVisible(user)){
                System.out.println("Information is not displayed when hovering over a user...");
                break;
            }
            userInfo = GetInfoAboutUser(user);

            if (userInfo.equals(userName)) {
                System.out.println("User name: [" + userInfo + "]");
                break;
            }
        }
        return this;
    }

    public boolean isUserTooltipVisible(WebElement user) {
        Actions actions = new Actions(driver);
        actions.moveToElement(user).perform();  // курсор

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            WebElement tooltip = wait.until(ExpectedConditions.visibilityOf(
                    user.findElement(By.xpath("./following-sibling::div/h5"))
            ));
            System.out.println("Information is displayed when you hover over a user...");
            return tooltip.isDisplayed();
        } catch (TimeoutException e) {
            return false;  // если не появилось, то false
        }
    }

    private String GetInfoAboutUser(WebElement user) {
        Actions actions = new Actions(driver);
        actions.moveToElement(user).perform();  // курсор

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return arguments[0].nextElementSibling.querySelector('h5').innerText;";
        return (String) js.executeScript(script, user);

        // альтернативный вариант
        // WebElement tooltip = user.findElement(By.xpath("./following-sibling::div/h5"));
        // return tooltip.getText();
    }

}
