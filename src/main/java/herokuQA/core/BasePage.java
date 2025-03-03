package herokuQA.core;


import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {

        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void typeWithJs(WebElement element, String text, int x, int y) {
        if (text != null) {
            js.executeScript("window.scrollBy(" + x + "," + y + ")");
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    // метод для того чтобы наш робот опустился по странице и нажал на нужный нам элемент
    public void clickWithJS(WebElement element, int x, int y) {
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
        click(element);
    }

    public void hideAds() {
        js.executeScript("document.getElementById('adplus-anchor').style.display='none'");
        js.executeScript("document.querySelector('footer').style.display='none';");
    }

    public String takeScreenshot() {
        // Capture screenshot
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("src/test_screenshots/screen-" + System.currentTimeMillis() + ".png");
        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Screenshot saved to: [" + screenshot.getAbsolutePath() + "]");
        return screenshot.getAbsolutePath();
    }

    public void shouldHaveText(WebElement element, String exp_text, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeout));
        String actualText = element.getText();

        try {
            boolean isTextPresent = wait.until(ExpectedConditions.textToBePresentInElement(element, exp_text));
            Assert.assertTrue(isTextPresent, "Expected text: [" + exp_text + "], actual text in element: [" + actualText + "] in element: [" + element + "]");
        } catch (TimeoutException e) {
            throw new AssertionError("Text not found in element: [" + element + "], expected text: [" + exp_text + "] was text:[" + actualText + "]", e);
        }
    }

    public List<String> getHeadingPage(List<WebElement> headings){
        List<String> headingTexts = headings
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        return  headingTexts;
    }

    protected int numberOfElements() {
        return driver.findElements(By.cssSelector("button[onclick='deleteElement()']")).size();
    }
}
