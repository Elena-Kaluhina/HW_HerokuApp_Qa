package herokuQA.pages;

import herokuQA.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;

public class MultipleWindowsPage extends BasePage {
    public MultipleWindowsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    Logger logger = LoggerFactory.getLogger(MultipleWindowsPage.class);
    private String startPageTitle, currentPageTitle;
    private String startWindow;

    @FindBy(css = "a[href='/windows/new']")
    WebElement clickHereLink;

    @FindBy(css = "h1, h2, h3, h4, h5, h6")
    List<WebElement> headings;

    public MultipleWindowsPage clickByClickHereAndSwitchNewTab() {
        startWindow = driver.getWindowHandle(); // стартовая страница
        startPageTitle = driver.switchTo().window(startWindow).getTitle();
        System.out.println("Headings start page: " + getHeadingPage(headings));
        System.out.println("Start Page Title: " + startPageTitle); // титл
        System.out.println("Start Page URL: " + driver.switchTo().window(startWindow).getCurrentUrl());
        click(clickHereLink); // клик по ссылке Click Here, открытие новой вкладки
        //Set<String> allNewWindows = driver.getWindowHandles(); // новые вкладки

        wait.until(d -> driver.getWindowHandles().size() > 1); // ждем появления новой вкладки

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(startWindow)) {
                driver.switchTo().window(windowHandle);  // переключаемся на новое окно
                break;
            }
        }
        currentPageTitle = driver.getTitle();
        // проверяем заголовки
        System.out.println("Headings current page: " + getHeadingPage(headings));
        System.out.println("Current Page Title: " + currentPageTitle);
        System.out.println("Current URL: " + driver.getCurrentUrl());

        return this;
    }

    public MultipleWindowsPage verifySwitching() {
        try {
            // проверяем, что заголовки страниц не одинаковые
            Assert.assertNotEquals(currentPageTitle, startPageTitle,
                    "Page titles are the same. Expected different titles but got: [" + startPageTitle + "] and [" + currentPageTitle + "]");
        } catch (AssertionError e) {
            // логгируем ошибку из-за несовпадения заголовков
            logger.error("Page title mismatch: Expected different titles but got: [" + startPageTitle + "] and [" + currentPageTitle + "]");
            throw e;  // выбрасываем ошибку дальше
        }
        return this;
    }

    public MultipleWindowsPage switchToStartPage() {

        driver.switchTo().window(startWindow);  // возвращаемся на стартовую страницу

        String currentTitle = driver.getTitle();
        if (currentTitle.equals(startPageTitle)) {
            logger.info("Successfully switched back to the start page.");
        } else {
            logger.error("Failed to switch back to the start page. Current title: " + currentTitle);
        }
        return this;
    }
}
